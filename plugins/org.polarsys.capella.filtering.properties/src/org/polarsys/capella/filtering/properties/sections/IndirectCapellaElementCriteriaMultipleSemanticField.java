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
package org.polarsys.capella.filtering.properties.sections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryLabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.ui.properties.controllers.IMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;
import org.polarsys.capella.core.ui.properties.helpers.NamingHelper;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.tools.dialogs.FilteringCriteriaSelectionDialog;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;
import org.polarsys.capella.filtering.tools.utils.ui.CriteriaContentProvider;

public class IndirectCapellaElementCriteriaMultipleSemanticField extends MultipleSemanticField {

  /**
   * @param parent
   * @param label
   * @param widgetFactory
   * @param controller
   */
  public IndirectCapellaElementCriteriaMultipleSemanticField(Composite parent, String label,
      TabbedPropertySheetWidgetFactory widgetFactory, IMultipleSemanticFieldController controller) {
    super(parent, label, widgetFactory, controller);
  }

  /**
   * We use our own dialog to select the features. {@inheritDoc}
   */
  @Override
  protected List<EObject> openTransferDialog(Button button, List<EObject> currentElements,
      List<EObject> availableElements, String title, String message) {
    Session session = SessionManager.INSTANCE.getSession(semanticElement);
    TransactionalEditingDomain transactionalEditingDomain = session.getTransactionalEditingDomain();
    Collection<Project> projects = FilteringUtils.getMainAndReferencedVariantProjects(semanticElement);
    final FilteringCriteriaSelectionDialog dialog = new FilteringCriteriaSelectionDialog(button.getShell(),
        new TransactionalAdapterFactoryLabelProvider(transactionalEditingDomain,
            ((AdapterFactoryEditingDomain) transactionalEditingDomain).getAdapterFactory()),
        new CriteriaContentProvider(), projects);
    dialog.setTitle(title);
    dialog.setMessage(message);
    dialog.setInput(projects);
    dialog.setInitialElementSelections(currentElements);
    if (dialog.open() != Window.OK) {
      // User press cancel or close, return previous elements
      return currentElements;
    }
    // Return the selection
    List<EObject> checkedElements = new ArrayList<>();
    for (Object o : dialog.getCheckedElements()) {
      if (o instanceof EObject) {
        checkedElements.add((EObject) o);
      }
    }
    return checkedElements;
  }

  @Override
  protected void handleDeleteButtonClicked() {
    // Do nothing if already empty
    if (FilteringUtils.getExplicitAssociatedCriteria(semanticElement).isEmpty()) {
      return;
    }
    // Delete
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      public void run() {
        FilteringUtils.removeAssociatedCriteria(semanticElement,
            FilteringUtils.getExplicitAssociatedCriteria(semanticElement));
      }
    };
    FilteringUtils.executeCommand(command, semanticElement);
    setValueTextField(Collections.emptyList());
  }

  /**
   * Handle Open button click event.
   * 
   * @param button
   */
  @Override
  protected void handleOpenButtonClicked(final Button button) {
    Session session = SessionManager.INSTANCE.getSession(semanticElement);
    TransactionalEditingDomain transactionalEditingDomain = session.getTransactionalEditingDomain();
    Collection<Project> projects = FilteringUtils.getMainAndReferencedVariantProjects(semanticElement);
    final FilteringCriteriaSelectionDialog dialog = new FilteringCriteriaSelectionDialog(button.getShell(),
        new TransactionalAdapterFactoryLabelProvider(transactionalEditingDomain,
            ((AdapterFactoryEditingDomain) transactionalEditingDomain).getAdapterFactory()),
        new CriteriaContentProvider(), projects);
    String title = NamingHelper.getDefaultTitle(semanticElement);
    String message = NamingHelper.getDefaultMessage(semanticElement,
        (semanticFeature != null) ? semanticFeature.getName() : ""); //$NON-NLS-1$
    dialog.setTitle(title);
    dialog.setMessage(message);
    dialog.setInput(projects);
    List<FilteringCriterion> currentElements = FilteringUtils.getExplicitAssociatedCriteria(semanticElement);
    dialog.setInitialElementSelections(currentElements);
    if (dialog.open() != Window.OK) {
      // User press cancel or close, return previous elements
      return;
    }
    // Return the selection
    List<EObject> checkedElements = new ArrayList<>();
    for (Object o : dialog.getCheckedElements()) {
      if (o instanceof EObject) {
        checkedElements.add((EObject) o);
      }
    }
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      public void run() {
        List<FilteringCriterion> featuresToAdd = new ArrayList<>();
        List<FilteringCriterion> featuresToRemove = new ArrayList<>();

        Collection<Object> toCheck = dialog.getCheckedElements();
        Collection<Object> toUnCheck = dialog.getUnCheckedElements();

        for (Object unCheckMe : toUnCheck) {
          if (FilteringUtils.getExplicitAssociatedCriteria(semanticElement).contains(unCheckMe)) {
            featuresToRemove.add((FilteringCriterion) unCheckMe);
          }
        }
        for (Object checkMe : toCheck) {
          if (!FilteringUtils.getExplicitAssociatedCriteria(semanticElement).contains(checkMe)) {
            featuresToAdd.add((FilteringCriterion) checkMe);
          }
        }
        FilteringUtils.addAssociatedCriteria(semanticElement, featuresToAdd);
        FilteringUtils.removeAssociatedCriteria(semanticElement, featuresToRemove);
      }
    };
    FilteringUtils.executeCommand(command, semanticElement);
    setValueTextField(FilteringUtils.getExplicitAssociatedCriteria(semanticElement));
  }
}
