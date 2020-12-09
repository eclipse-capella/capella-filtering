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
package org.polarsys.capella.filtering.validation.constraints;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

/**
 * 
 */
public class MDCHK_D_FilteringCriterion_Unused extends AbstractModelConstraint {

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    // check that it is batch validation
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof FilteringCriterion) {
        // Loop all project elements
        Project project = CapellaProjectHelper.getProject(eObj);
        Iterator<EObject> i = project.eAllContents();
        while (i.hasNext()) {
          EObject object = i.next();
          if (object instanceof CapellaElement) {
            List<FilteringCriterion> filteringCriteria = FilteringUtils.getExplicitAssociatedCriteria(object);
            if (filteringCriteria.contains(eObj)) {
              // OK, used Filtering Criterion
              return ctx.createSuccessStatus();
            }
          }
        }
        return ctx.createFailureStatus(ConstraintsUtil.getNameForMessage(eObj));
      }
    }
    return ctx.createSuccessStatus();
  }

}
