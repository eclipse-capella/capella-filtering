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

import java.util.Iterator;

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
import org.eclipse.sirius.diagram.tools.api.layout.PinHelper;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.capella.filtering.sirius.ui.Activator;
import org.polarsys.capella.filtering.sirius.ui.FilteringSiriusUtils;
import org.polarsys.capella.filtering.sirius.ui.dialogs.DiagramElementsSelectionDialog;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Action to open a dialog box where the user can select/deselect the diagram Product Line features which should be
 * hidden.
 * 
 * 
 */

public class SelectPinnedCriteriaAction extends FilteringDiagramAction {

  private final class HiddenFeaturesSelectionCommand extends AbstractTransactionalCommand {
    private final DDiagram diagram;

    private HiddenFeaturesSelectionCommand(TransactionalEditingDomain domain, String label, DDiagram diagram) {
      super(domain, label, null);
      this.diagram = diagram;
    }

    @SuppressWarnings("synthetic-access")
    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
      DiagramElementsSelectionDialog dlg = new DiagramElementsSelectionDialog(TITLE, MESSAGE);
      dlg.setSelectionPredicate(isPinned);
      dlg.setSelectedAction(pinElement);
      dlg.setDeselectedAction(unpinElement);
      dlg.setGrayedPredicate(getNonSelectablePredicate());
      boolean executed = dlg.open(getShell(), diagram, true);
      if (executed) {
        return CommandResult.newOKCommandResult();
      }
      return CommandResult.newCancelledCommandResult();
    }

    private Predicate<Object> getNonSelectablePredicate() {

      return new Predicate<Object>() {
        @Override
        public boolean apply(Object input) {
          if (input instanceof DDiagramElement) {
            return !PinHelper.allowsPinUnpin((DDiagramElement) input);
          }
          return false;
        }
      };
    }
  }

  private static final String TITLE = "Diagram Filtering Features pinning"; //$NON-NLS-1$

  private static final String MESSAGE = "Pinned diagram Filtering Features are checked."; //$NON-NLS-1$

  private static final String TOOLTIP = "Pin/Unpin features"; //$NON-NLS-1$

  private static final String ICON_PATH = "icons/pinUnpinCriteria.png"; //$NON-NLS-1$

  /** The hide icon descriptor. */
  private static final ImageDescriptor DESC_PIN = Activator.getBundledImageDescriptor(ICON_PATH);

  private final Predicate<Object> isPinned = new Predicate<Object>() {
    @Override
    public boolean apply(Object input) {
      if (input instanceof DDiagramElement) {
        return new PinHelper().isPinned((DDiagramElement) input);
      }
      return false;
    }
  };

  private final Function<Object, Void> pinElement = new Function<Object, Void>() {
    @Override
    public Void apply(Object from) {
      if (from instanceof DDiagramElement) {
        new PinHelper().markAsPinned((DDiagramElement) from);
      }
      return null;
    }
  };

  private final Function<Object, Void> unpinElement = new Function<Object, Void>() {
    @Override
    public Void apply(Object from) {
      if (from instanceof DDiagramElement) {
        new PinHelper().markAsUnpinned((DDiagramElement) from);
      }
      return null;
    }
  };

  /**
   * Constructor.
   * 
   * @param workbenchPage
   *          the workbench page.
   */
  public SelectPinnedCriteriaAction(IWorkbenchPage workbenchPage) {
    super(workbenchPage);
    // setText(TOOLTIP);
    // setId(ActionIds.SELECT_HIDDEN_ELEMENTS);
    // setToolTipText(TOOLTIP);
    setImageDescriptor(DESC_PIN);
  }

  /**
   * Constructor.
   * 
   * @param workbenchPage
   *          the workbench page.
   * @param part
   *          the workbench part.
   */
  public SelectPinnedCriteriaAction(IWorkbenchPage workbenchPage, IDiagramWorkbenchPart part) {
    super(workbenchPage);
    setWorkbenchPart(part);
    // setText(TOOLTIP);
    // setId(ActionIds.SELECT_HIDDEN_ELEMENTS);
    // setToolTipText(TOOLTIP);
    setImageDescriptor(getImage());
  }

  /**
   * Get the decorated image
   */
  public ImageDescriptor getImage() {
    if (getWorkbenchPart() instanceof DiagramEditor) {
      final Diagram gmfDiagram = ((DiagramEditor) getWorkbenchPart()).getDiagram();
      if (gmfDiagram != null) {
        EObject diagram = gmfDiagram.getElement();
        if (diagram instanceof DDiagram) {
          if (hasPinnedAndOptionalElements((DDiagram) diagram)) {
            // FIXME When the problem of pinned ports when a diagram
            // is reopened is solved, enable the customized icon.
            // return
            // Implementation.getDecoratedCheckedImageDescriptor(DESC_PIN);
            return DESC_PIN;
          }
        }
      }
    }
    return DESC_PIN;
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
      return new ICommandProxy(
          new HiddenFeaturesSelectionCommand(diagramEditPart.getEditingDomain(), TOOLTIP, diagram));
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
    return canEditInstance && super.calculateEnabled();
  }

  private boolean hasPinnedAndOptionalElements(DDiagram dDiagram) {
    Iterator<DDiagramElement> iterator = dDiagram.getDiagramElements().iterator();
    PinHelper pinHelper = new PinHelper();
    while (iterator.hasNext()) {
      final DDiagramElement element = iterator.next();
      if (pinHelper.isPinned(element) && FilteringSiriusUtils.hasAssociatedCriteria(element)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void setWorkbenchPart(IWorkbenchPart workbenchPart) {
    super.setWorkbenchPart(workbenchPart);
  }
}
