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
package org.polarsys.capella.filtering.sirius.ui.diagram.actions;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.business.api.provider.AbstractDDiagramElementLabelItemProvider;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.filtering.sirius.ui.Activator;
import org.polarsys.capella.filtering.sirius.ui.FilteringSiriusUtils;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Action to filter Common elements. Or Optionals is common parameter is false
 */

public class FilterCommonOptionalAction extends FilteringDiagramAction {

	// Common or Optional Action
	private boolean common;

	private static final String DIAGRAM_ANNOTATION = "Filter"; //$NON-NLS-1$

	// Initialization in the constructor based on common or optional boolean
	private String DIAGRAM_ANNOTATION_SUFFIX = null;

	private static final String ICON_PATH = "icons/filters.png"; //$NON-NLS-1$

	/** The hide icon descriptor. */
	private static final ImageDescriptor DESC_FILTER = Activator.getBundledImageDescriptor(ICON_PATH);

	private final Predicate<Object> isVisible = new Predicate<Object>() {
		@Override
		public boolean apply(Object input) {
			boolean result = false;
			if (input instanceof DDiagramElement) {
				result = !(new DDiagramElementQuery((DDiagramElement) input).isHidden());
			} else if (input instanceof AbstractDDiagramElementLabelItemProvider) {
				Option<DDiagramElement> optionTarget = ((AbstractDDiagramElementLabelItemProvider) input)
						.getDiagramElementTarget();
				if (optionTarget.some()) {
					result = !(new DDiagramElementQuery(optionTarget.get()).isLabelHidden());
				}
			}
			return result;
		}
	};

	/**
	 * Constructor.
	 * 
	 * @param workbenchPage
	 *            the workbench page.
	 * @param part
	 *            the workbench part.
	 */
	public FilterCommonOptionalAction(IWorkbenchPage workbenchPage, IDiagramWorkbenchPart part, boolean isCommon) {
		super(workbenchPage);
		setWorkbenchPart(part);
		setImageDescriptor(getImage());
		this.common = isCommon;
		if (common) {
			DIAGRAM_ANNOTATION_SUFFIX = "Common"; //$NON-NLS-1$
		} else {
			DIAGRAM_ANNOTATION_SUFFIX = "Optionals"; //$NON-NLS-1$
		}
	}

	/**
	 * Get the decorated image
	 */
	public ImageDescriptor getImage() {
		return DESC_FILTER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Request createTargetRequest() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isSelectionListener() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getCommand() {
		Command result = UnexecutableCommand.INSTANCE;
		Iterable<IDDiagramEditPart> diagramEditParts = Iterables.filter(getSelectedObjects(), IDDiagramEditPart.class);
		if (Iterables.size(diagramEditParts) >= 1) {
			IDDiagramEditPart diagramEditPart = Iterables.get(diagramEditParts, 0);
			result = getElementsSelectionCommand(diagramEditPart);
		}
		return result;
	}

	@SuppressWarnings("synthetic-access")
	private Command getElementsSelectionCommand(IDDiagramEditPart diagramEditPart) {
		EObject semanticElement = diagramEditPart.resolveSemanticElement();
		if (semanticElement instanceof DDiagram) {
			DDiagram diagram = (DDiagram) semanticElement;
			return new ICommandProxy(new FilterCommonOptionalCommand(diagramEditPart.getEditingDomain(), "", diagram)); //$NON-NLS-1$
		}
		return UnexecutableCommand.INSTANCE;
	}

	public void setShow(boolean show) {
		// update annotation
		if (getWorkbenchPart() instanceof DiagramEditor) {
			final Diagram gmfDiagram = ((DiagramEditor) getWorkbenchPart()).getDiagram();
			if (gmfDiagram != null) {
				EObject diagram = gmfDiagram.getElement();
				if (diagram instanceof DDiagram) {
					final DDiagram dDiagram = (DDiagram) diagram;
					final DAnnotation dAnnotation = dDiagram
							.getDAnnotation(DIAGRAM_ANNOTATION + DIAGRAM_ANNOTATION_SUFFIX);
					AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
						@SuppressWarnings("synthetic-access")
						@Override
						public void run() {
							if (dAnnotation == null) {
								DAnnotation dAnnotation1 = DescriptionFactory.eINSTANCE.createDAnnotation();
								dAnnotation1.setSource(DIAGRAM_ANNOTATION + DIAGRAM_ANNOTATION_SUFFIX);
								dDiagram.getEAnnotations().add(dAnnotation1);
							} else {
								dDiagram.getEAnnotations().remove(dAnnotation);
							}
						}
					};
					FilteringUtils.executeCommand(command, diagram);
				}
			}
		}
	}

	// Show or Hide Action
	public boolean getShow() {
		return isAlreadyFiltered();
	}

	public boolean isAlreadyFiltered() {
		if (getWorkbenchPart() instanceof DiagramEditor) {
			final Diagram gmfDiagram = ((DiagramEditor) getWorkbenchPart()).getDiagram();
			if (gmfDiagram != null) {
				EObject diagram = gmfDiagram.getElement();
				if (diagram instanceof DDiagram) {
					DDiagram dDiagram = (DDiagram) diagram;
					DAnnotation dAnnotation = dDiagram.getDAnnotation(DIAGRAM_ANNOTATION + DIAGRAM_ANNOTATION_SUFFIX);
					if (dAnnotation != null) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private final class FilterCommonOptionalCommand extends AbstractTransactionalCommand {
		private final DDiagram diagram;

		private FilterCommonOptionalCommand(TransactionalEditingDomain domain, String label, DDiagram diagram) {
			super(domain, label, null);
			this.diagram = diagram;
		}

		@Override
		protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info)
				throws ExecutionException {

			boolean executed = false;
			Set<Object> allSelectedElements = Collections.unmodifiableSet(getAllSelectedElements());
			Set<Object> selectedAfterSet = getElementsSelectedAfter();
			Option<Set<Object>> response = Options.newSome(selectedAfterSet);
			if (response.some()) {
				Set<Object> selectedAfter = response.get();
				applyRequestedChanges(allSelectedElements, selectedAfter);
				assert selectedAfter.equals(allSelectedElements);
				executed = true;
			}
			// diagram = null;

			if (executed) {
				return CommandResult.newOKCommandResult();
			}
			return CommandResult.newCancelledCommandResult();
		}

		/**
		 * @return the elements to be select after
		 */
		@SuppressWarnings("synthetic-access")
		private Set<Object> getElementsSelectedAfter() {
			Set<Object> set = Sets.newHashSet();
			List<DDiagramElement> treeElements = diagram.getDiagramElements();
			// Get if it is show or hide
			boolean show = getShow();
			for (DDiagramElement element : treeElements) {
				boolean hasFeatures = FilteringSiriusUtils.hasAssociatedCriteria(element);
				if (show && ((common && !hasFeatures) || (!common && hasFeatures))) {
					set.add(element);
				} else { // hide
					// no elements selected after
				}
			}
			return set;
		}

		/**
		 * Return the elements that are not going to be selected
		 */
		@SuppressWarnings("synthetic-access")
		protected Set<Object> getAllSelectedElements() {
			// get the previously selected elements
			Set<Object> set = Sets.newHashSet();
			List<DDiagramElement> treeElements = diagram.getDiagramElements();
			for (DDiagramElement element : treeElements) {
				if (isVisible.apply(element)) {
					// No associated features in the case of Common
					// Has associated features in the case of Optionals
					if ((common && !FilteringSiriusUtils.hasAssociatedCriteria(element))
							|| (!common && FilteringSiriusUtils.hasAssociatedCriteria(element))) {
						set.add(element);
					}
				}
			}
			return set;
		}

		protected void applyRequestedChanges(Set<Object> selectedBefore, Set<Object> selectedAfter) {
			for (Object dde : Sets.difference(selectedBefore, selectedAfter)) {
				hideElement.apply(dde);
			}
			for (Object dde : Sets.difference(selectedAfter, selectedBefore)) {
				revealElement.apply(dde);
			}
		}

		private final Function<Object, Void> hideElement = new Function<Object, Void>() {
			@Override
			public Void apply(Object from) {
				if (from instanceof DDiagramElement) {
					HideFilterHelper.INSTANCE.hide((DDiagramElement) from);
				} else if (from instanceof AbstractDDiagramElementLabelItemProvider) {
					Option<DDiagramElement> optionTarget = ((AbstractDDiagramElementLabelItemProvider) from)
							.getDiagramElementTarget();
					if (optionTarget.some()) {
						HideFilterHelper.INSTANCE.hideLabel(optionTarget.get());
					}
				}
				return null;
			}
		};

		private final Function<Object, Void> revealElement = new Function<Object, Void>() {
			@Override
			public Void apply(Object from) {
				if (from instanceof DDiagramElement) {
					HideFilterHelper.INSTANCE.reveal((DDiagramElement) from);
				} else if (from instanceof AbstractDDiagramElementLabelItemProvider) {
					HideFilterHelper.INSTANCE.revealLabel(
							(DDiagramElement) ((AbstractDDiagramElementLabelItemProvider) from).getTarget());
				}
				return null;
			}
		};
	}

	@Override
	public void setWorkbenchPart(IWorkbenchPart workbenchPart) {
		super.setWorkbenchPart(workbenchPart);
	}
}
