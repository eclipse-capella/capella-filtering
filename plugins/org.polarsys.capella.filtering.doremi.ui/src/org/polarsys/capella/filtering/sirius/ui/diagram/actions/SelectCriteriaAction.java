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

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.business.api.provider.AbstractDDiagramElementLabelItemProvider;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditorUtil;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.capella.filtering.sirius.ui.FilteringSiriusUtils;
import org.polarsys.capella.filtering.sirius.ui.dialogs.DiagramElementsSelectionDialogWithExtraCheckbox;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Action to open a dialog box where the user can select the diagram Product
 * Line features which should be selected in the diagram.
 * 
 * 
 */

public class SelectCriteriaAction extends FilteringDiagramAction {

	private final class FeaturesSelectionCommand extends AbstractTransactionalCommand {
		private final DDiagram diagram;
		private IEditorSite editorSite;

		private FeaturesSelectionCommand(TransactionalEditingDomain domain, String label, DDiagram diagram) {
			super(domain, label, null);
			this.diagram = diagram;
		}

		@SuppressWarnings("synthetic-access")
		@Override
		protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info)
				throws ExecutionException {
			IWorkbenchPart activePart = FilteringSiriusUtils.getActivePage().getActivePart();
			if (!(activePart instanceof IEditorPart)) {
				return CommandResult.newErrorCommandResult("This action is available only for diagrams"); //$NON-NLS-1$
			}
			editorSite = ((IEditorPart) activePart).getEditorSite();

			DiagramElementsSelectionDialogWithExtraCheckbox dlg = new DiagramElementsSelectionDialogWithExtraCheckbox(
					TITLE, MESSAGE, CHECKBOX_MESSAGE, false);
			dlg.setSelectionPredicate(Predicates.alwaysFalse());
			dlg.setSelectedAction(selectElements);
			// Do nothing for deselectedAction
			dlg.setGrayedPredicate(Predicates.alwaysFalse());

			// Open the selection dialog
			boolean executed = dlg.open(getShell(), diagram, true);
			if (executed) {

				boolean includeCommon = dlg.getCheckboxSelection();

				// List to store the editParts
				ArrayList<EditPart> editPartsToSelect = new ArrayList<EditPart>();

				// Get the diagramEditPart. The root of EditPart elements
				// TODO find a better way to get diagramEditPart.
				// Now it is based in that the current selection must be the
				// diagramEditPart. Notice that otherwise the
				// action is not visible

				StructuredSelection diagramSelected = (StructuredSelection) editorSite.getSelectionProvider()
						.getSelection();
				IDDiagramEditPart diagramEditPart = (IDDiagramEditPart) diagramSelected.getFirstElement();

				// Loop through semantic objects to select in order to get its
				// edit parts
				for (EObject o : objectsToSelect) {
					// Check if we should skip common elements
					if (!includeCommon && (o instanceof DDiagramElement)
							&& FilteringSiriusUtils.getExplicitAssociatedCriteria((DDiagramElement) o).isEmpty()) {
						// skip this element
					} else {
						EditPart foundEditPart = findEditPart(diagramEditPart, o);
						if ((foundEditPart != null) && !editPartsToSelect.contains(foundEditPart)) {
							editPartsToSelect.add(foundEditPart);
						}
					}
				}

				// clear the objectsToSelect for the next time
				objectsToSelect.clear();

				// Select elements
				SiriusDiagramEditorUtil.selectWithoutRevealElementsInDiagram(getDiagramWorkbenchPart(),
						editPartsToSelect);

				return CommandResult.newOKCommandResult();
			}
			return CommandResult.newCancelledCommandResult();
		}
	}

	private static final String TITLE = "Diagram Filtering Features selector"; //$NON-NLS-1$

	private static final String MESSAGE = "Selected diagram Filtering Features are selected."; //$NON-NLS-1$

	private static final String CHECKBOX_MESSAGE = "Common elements"; //$NON-NLS-1$

	List<EObject> objectsToSelect = new ArrayList<EObject>();

	private final Function<Object, Void> selectElements = new Function<Object, Void>() {
		@Override
		public Void apply(Object from) {
			if (from instanceof DDiagramElement) {
				if (!objectsToSelect.contains(objectsToSelect)) {
					objectsToSelect.add((EObject) from);
				}
			} else if (from instanceof AbstractDDiagramElementLabelItemProvider) {
				if (!objectsToSelect.contains(objectsToSelect)) {
					objectsToSelect.add((EObject) from);
				}
			}
			return null;
		}
	};

	/**
	 * Constructor.
	 * 
	 * @param workbenchPage
	 *            the workbench page.
	 */
	public SelectCriteriaAction(IWorkbenchPage workbenchPage) {
		super(workbenchPage);
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

	private Shell getShell() {
		return getWorkbenchPart().getSite().getShell();
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
			return new ICommandProxy(new FeaturesSelectionCommand(diagramEditPart.getEditingDomain(), "", diagram)); //$NON-NLS-1$
		}
		return UnexecutableCommand.INSTANCE;
	}

	@Override
	protected boolean calculateEnabled() {
		boolean canEditInstance = true;
		if ((getWorkbenchPart() instanceof DDiagramEditor)
				&& (((DDiagramEditor) getWorkbenchPart()).getRepresentation() instanceof DDiagram)) {
			final DDiagramEditor editor = (DDiagramEditor) getWorkbenchPart();
			final DDiagram editorDiagram = (DDiagram) editor.getRepresentation();
			IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault()
					.getPermissionAuthority(editor.getSession().getSessionResource().getResourceSet());
			canEditInstance = permissionAuthority.canEditInstance(editorDiagram);
		}
		boolean result = canEditInstance && super.calculateEnabled();
		return result;
	}

	/** Finds an editpart given a starting editpart and an EObject */
	/**
	 * This method is a modification of EditPart.findEditPart because it does
	 * not handle Edges
	 **/
	public EditPart findEditPart(EditPart editPart, EObject theElement) {
		if (theElement == null) {
			return null;
		}

		final View view = (View) ((IAdaptable) editPart).getAdapter(View.class);

		if (view != null) {
			EObject el = ViewUtil.resolveSemanticElement(view);

			if ((el != null) && el.equals(theElement)) {
				return editPart;
			}
		}
		// Try with its links
		if (editPart instanceof GraphicalEditPart) {
			List<EditPart> sourceConnections = ((GraphicalEditPart) editPart).getSourceConnections();
			for (EditPart connectionEditPart : sourceConnections) {
				EditPart elementEP = findEditPart(connectionEditPart, theElement);
				if (elementEP != null) {
					return elementEP;
				}
			}
		}

		// Try with its childs
		ListIterator<?> childLI = editPart.getChildren().listIterator();
		while (childLI.hasNext()) {
			EditPart epChild = (EditPart) childLI.next();

			EditPart elementEP = findEditPart(epChild, theElement);
			if (elementEP != null) {
				return elementEP;
			}
		}
		return null;
	}

	@Override
	public void setWorkbenchPart(IWorkbenchPart workbenchPart) {
		super.setWorkbenchPart(workbenchPart);
	}

}
