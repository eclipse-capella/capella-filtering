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
package org.polarsys.capella.filtering.validation.constraints;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;
import org.polarsys.kitalpha.emde.model.ElementExtension;

/**
 * 
 */
public class MDCHK_I_CapellaElement_EmptyAssociatedCriterionSet extends AbstractModelConstraint {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    // check that it is batch validation
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof CapellaElement) {
        CapellaElement capellaElement = (CapellaElement) eObj;
        for (ElementExtension elementExtension : capellaElement.getOwnedExtensions()) {
          if (elementExtension instanceof AssociatedFilteringCriterionSet) {

            // Check if it is empty
            List<?> list = ((AssociatedFilteringCriterionSet) elementExtension).getFilteringCriteria();
            if ((list == null) || list.isEmpty()) {
              return ctx.createFailureStatus(ConstraintsUtil.getNameForMessage(capellaElement));
            }

          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }

}
