/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.filtering.tools.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryLabelProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ui.actions.AbstractTigAction;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringModel;
import org.polarsys.capella.filtering.tools.FilteringToolsPlugin;
import org.polarsys.capella.filtering.tools.dialogs.FilteringCriteriaSelectionDialog;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;
import org.polarsys.capella.filtering.tools.utils.ui.CriteriaContentProvider;

/**
 * Action to assign features to a selection of model elements.
 */
public class FilteringMultipleCriteriaAssigningAction extends AbstractTigAction implements IActionDelegate {

	protected FilteringCriteriaSelectionDialog checkedTreeSelectionDialog;

	public void run(IAction action) {

		// Get the selected elements
		final List<ModelElement> elements = getSelectedElements();

		// Remove elements that we were not able to adapt to melodyElements, for
		// example Notes in diagrams
		elements.removeAll(Collections.singletonList(null));

		// Display message there were no valid diagram element selected
		if (elements.isEmpty()) {
			MessageDialog.openInformation(getActiveShell(), Messages.FilteringMultipleCriteriaAssigningAction_0, // $NON-NLS-1$
					Messages.FilteringMultipleCriteriaAssigningAction_1); // $NON-NLS-1$
			return;
		}

		// Calculate intersection of feature model for all selected elements
		Collection<FilteringModel> filteringModels = FilteringUtils.getCommonFilteringModels(elements, true);
		if (filteringModels.isEmpty()) {
			MessageDialog.openInformation(getActiveShell(), Messages.FilteringMultipleCriteriaAssigningAction_0, // $NON-NLS-1$
					Messages.FilteringMultipleCriteriaAssigningAction_1); // $NON-NLS-1$
			return;

		}
		Collection<FilteringCriterion> availableFeatures = FilteringUtils.getOwnedFilteringCriteria(filteringModels);
		Collection<Object> globalFeatures = new ArrayList<>();
		List<Object> checkedFeatures = new ArrayList<>();

		HashMap<Object, Integer> infoFeatures = new HashMap<>();

		// Fill checkedFeatures list and prepare the counter to check if a
		// feature is contained by all selected elements
		int elementCounter = 0;
		for (ModelElement element : elements) {
			if (element instanceof CapellaElement) {
				elementCounter++;
				List<FilteringCriterion> elementFeatures = FilteringUtils.getExplicitAssociatedCriteria((element));
				for (EObject feature : elementFeatures) {
					if (!infoFeatures.containsKey(feature)) {
						infoFeatures.put(feature, 1);
						if (!checkedFeatures.contains(feature)) {
							checkedFeatures.add(feature);
						}
					} else {
						infoFeatures.put(feature, infoFeatures.get(feature) + 1);
					}
				}
			}
		}

		// Search for global features
		for (Object concreteFeature : availableFeatures) {
			Integer counter = infoFeatures.get(concreteFeature);
			if ((counter != null) && (counter == elementCounter)) {
				globalFeatures.add(concreteFeature);
			}
		}

		// Create the dialog
		setupDialog(filteringModels, globalFeatures, checkedFeatures);
		// Open the dialog
		if (checkedTreeSelectionDialog.open() == Window.OK) {
			// Execute a command with the user modifications

			AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
				public void run() {
					List<FilteringCriterion> featuresToAdd = new ArrayList<>();
					List<FilteringCriterion> featuresToRemove = new ArrayList<>();

					Collection<Object> toCheck = checkedTreeSelectionDialog.getCheckedElements();
					Collection<Object> toUnCheck = checkedTreeSelectionDialog.getUnCheckedElements();

					// Filter the undefined ones
					Collection<Object> undefined = checkedTreeSelectionDialog.getUndefinedElements();
					if (!undefined.isEmpty()) {
						toCheck.removeAll(undefined);
						toUnCheck.removeAll(undefined);
					}

					for (ModelElement element : elements) {
						if (element instanceof CapellaElement) {
							CapellaElement mElement = (CapellaElement) element;
							for (Object unCheckMe : toUnCheck) {
								if (FilteringUtils.getExplicitAssociatedCriteria(mElement).contains(unCheckMe)) {
									featuresToRemove.add((FilteringCriterion) unCheckMe);
								}
							}
							for (Object checkMe : toCheck) {
								if (!FilteringUtils.getExplicitAssociatedCriteria(mElement).contains(checkMe)) {
									featuresToAdd.add((FilteringCriterion) checkMe);
								}
							}
							FilteringUtils.addAssociatedCriteria(mElement, featuresToAdd);
							FilteringUtils.removeAssociatedCriteria(mElement, featuresToRemove);
						}
					}
				}
			};
			FilteringUtils.executeCommand(command, elements);
		}
	}

	/**
	 * Setup Dialog
	 */
	private void setupDialog(Collection<FilteringModel> filteringModels, Collection<Object> globalFeatures,
			List<Object> checkedFeatures) {

		Collection<Project> projects = getProjects(filteringModels);
		Session session = SessionManager.INSTANCE.getSession(projects.iterator().next());
		TransactionalEditingDomain transactionalEditingDomain = session.getTransactionalEditingDomain();
		checkedTreeSelectionDialog = new FilteringCriteriaSelectionDialog(Display.getCurrent().getActiveShell(),
				new TransactionalAdapterFactoryLabelProvider(transactionalEditingDomain,
						((AdapterFactoryEditingDomain) transactionalEditingDomain).getAdapterFactory()),
				new CriteriaContentProvider(), projects);
		checkedTreeSelectionDialog.setTitle(Messages.AssignFilteringCriteriaAction_title); //$NON-NLS-1$
		checkedTreeSelectionDialog.setMessage(Messages.AssignFilteringCriteriaAction_message); //$NON-NLS-1$
		checkedTreeSelectionDialog.setInput(projects);
		checkedTreeSelectionDialog.setInitialElementSelections(checkedFeatures);
		checkedTreeSelectionDialog
				.setImage(AbstractUIPlugin.imageDescriptorFromPlugin(FilteringToolsPlugin.getDefault().getPluginId(),
						"icons/criteriaAssigning.png").createImage()); //$NON-NLS-1$
		checkedTreeSelectionDialog.setComparator(new ViewerComparator());
		// Undefined elements are the checked features that are not part of the
		// global features
		Set<Object> undefinedElements = new HashSet<>();
		for (Object feature : checkedFeatures) {
			if (!globalFeatures.contains(feature)) {
				undefinedElements.add(feature);
			}
		}
		checkedTreeSelectionDialog.setInitialUndefinedElements(undefinedElements);
	}

	private Collection<Project> getProjects(Collection<FilteringModel> filteringModels) {
		Collection<Project> result = new HashSet<>();
		for (FilteringModel fm : filteringModels) {
			Project project = CapellaProjectHelper.getProject(fm);
			if (project != null) {
				result.add(project);
			}
		}
		return result;
	}
}
