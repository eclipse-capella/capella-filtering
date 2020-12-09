/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.filtering.sirius.ui.decorators;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IPrimaryEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.CreateDecoratorsOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.polarsys.capella.filtering.sirius.ui.FilteringSiriusUtils;

/**
 * Optional Decorator Provider. Used for extension point org.eclipse.gmf.runtime.diagram.ui.decoratorProviders. It adds
 * an Image and a Label IFigure with current features if the DDiagramElement semantic elements have associated features
 */
public class OptionalDecoratorProvider extends AbstractProvider implements IDecoratorProvider {

  /**
   * Check if the element should provide the decorator {@inheritDoc}
   */
  @Override
  public boolean provides(IOperation operation) {
    // Just to be sure that it is a decoration operation
    if (!(operation instanceof CreateDecoratorsOperation)) {
      return false;
    }
    // Get the editPart and then the DDiagramElement
    IDecoratorTarget decoratorTarget = ((CreateDecoratorsOperation) operation).getDecoratorTarget();
    IGraphicalEditPart editPart = decoratorTarget.getAdapter(IGraphicalEditPart.class);
    // Check if it is IPrimaryEditPart (Node or Connection in gmf)
    if (editPart instanceof IPrimaryEditPart) {
      EObject eObject = editPart.resolveSemanticElement();
      if (eObject instanceof DDiagramElement) {
        // Return true if the DDiagramElement contains at least one
        // CapellaElement. That means that this element will be suitable
        // to include the decorator
        return FilteringSiriusUtils.containsCapellaElements((DDiagramElement) eObject);
      }
    }
    // Return false otherwise
    return false;

  }

  /**
   * Create decorators {@inheritDoc}
   */
  @Override
  public void createDecorators(IDecoratorTarget decoratorTarget) {
    IGraphicalEditPart editPart = decoratorTarget.getAdapter(IGraphicalEditPart.class);
    // Check if it is a IPrimaryEditPart (Node or Connection) that contains
    // DDiagramElements that contains Melody
    // Elements
    if (editPart instanceof IPrimaryEditPart) {
      EObject eObject = editPart.resolveSemanticElement();
      if (eObject instanceof DDiagramElement
          && FilteringSiriusUtils.containsCapellaElements((DDiagramElement) eObject)) {
        // Install the OptionalDecorator in the decoratorTarget
        // It doesn't matter if then it will be shown or not, this is
        // responsibility of the OptionalDecorator
        decoratorTarget.installDecorator("Product Line Optional element", //$NON-NLS-1$
            new OptionalDecorator(decoratorTarget));
      }
    }
  }

}
