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
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.capella.filtering.sirius.ui.Activator;
import org.polarsys.capella.filtering.sirius.ui.FilteringSiriusUtils;
import org.polarsys.capella.filtering.sirius.ui.dialogs.DiagramElementsSelectionDialog;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Action to open a dialog box where the user can select/deselect the diagram Product Line features which should be
 * hidden.
 * 
 * 
 */

public class SelectHiddenCriteriaAction extends FilteringDiagramAction {

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
      dlg.setSelectionPredicate(isVisibleAndOptional);
      dlg.setSelectedAction(revealElement);
      dlg.setDeselectedAction(hideElement);
      dlg.setGrayedPredicate(getNonSelectablePredicate());
      Shell shell = getWorkbenchPart().getSite().getShell();
      boolean executed = dlg.open(shell, diagram, true);
      if (executed) {
        return CommandResult.newOKCommandResult();
      }
      return CommandResult.newCancelledCommandResult();
    }

    private Predicate<Object> getNonSelectablePredicate() {
      final Predicate<DDiagramElement> allowsHideReveal = FilteringSiriusUtils.allowsHideReveal(diagram);
      if (Predicates.alwaysTrue().equals(allowsHideReveal)) {
        return Predicates.alwaysFalse();
      }

      return new Predicate<Object>() {
        @Override
        public boolean apply(Object input) {
          if (input instanceof DDiagramElement) {
            return !allowsHideReveal.apply((DDiagramElement) input);
          }
          return false;
        }
      };
    }
  }

  private static final String TITLE = "Diagram Filtering Features visibility"; //$NON-NLS-1$

  private static final String MESSAGE = "Visible diagram Filtering Features are checked."; //$NON-NLS-1$

  private static final String TOOLTIP = "Show/Hide features"; //$NON-NLS-1$

  private static final String ICON_PATH = "icons/showHideCriteria.png"; //$NON-NLS-1$

  /** The hide icon descriptor. */
  private static final ImageDescriptor DESC_HIDE = Activator.getBundledImageDescriptor(ICON_PATH);

  private final Predicate<Object> isVisibleAndOptional = new Predicate<Object>() {
    @Override
    public boolean apply(Object input) {
      boolean result = false;
      if (input instanceof DDiagramElement) {
        // check if visible
        result = !(new DDiagramElementQuery((DDiagramElement) input).isHidden());
        // check if it is optional
        if (result) {
          result = FilteringSiriusUtils.hasAssociatedCriteria((DDiagramElement) input);
        }
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
        HideFilterHelper.INSTANCE
            .revealLabel((DDiagramElement) ((AbstractDDiagramElementLabelItemProvider) from).getTarget());
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
  public SelectHiddenCriteriaAction(IWorkbenchPage workbenchPage) {
    super(workbenchPage);
    setImageDescriptor(DESC_HIDE);
  }

  /**
   * Constructor.
   * 
   * @param workbenchPage
   *          the workbench page.
   * @param part
   *          the workbench part.
   */
  public SelectHiddenCriteriaAction(IWorkbenchPage workbenchPage, IDiagramWorkbenchPart part) {
    super(workbenchPage);
    setWorkbenchPart(part);
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
          if (!getHiddenAndOptionalElements((DDiagram) diagram).isEmpty()) {
            // TODO Check what happens with Functional Input Ports
            // as they seem to be hidden diagram elements that are
            // shown.
            // FIXME When the problem of hidden ports when a diagram
            // is reopened is solved, enable the customized icon.
            // return
            // Implementation.getDecoratedCheckedImageDescriptor(DESC_HIDE);
            return DESC_HIDE;
          }
        }
      }
    }

    return DESC_HIDE;
  }

  /**
   * @param diagram
   * @return
   */
  private List<DDiagramElement> getHiddenAndOptionalElements(DDiagram diagram) {
    List<DDiagramElement> hiddenAndOptional = new ArrayList<>();
    for (DDiagramElement element : diagram.getHiddenElements()) {
      if (FilteringSiriusUtils.hasAssociatedCriteria(element)) {
        hiddenAndOptional.add(element);
      }
    }
    return hiddenAndOptional;
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
    boolean result = canEditInstance && super.calculateEnabled();
    return result;
  }

  @Override
  public void setWorkbenchPart(IWorkbenchPart workbenchPart) {
    super.setWorkbenchPart(workbenchPart);
  }
}
