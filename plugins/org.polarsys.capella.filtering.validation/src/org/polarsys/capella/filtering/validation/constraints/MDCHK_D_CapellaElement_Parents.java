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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

/**
 * 
 */
public class MDCHK_D_CapellaElement_Parents extends AbstractModelConstraint {

  /**
   * TODO refactor with MDCHK_D_CapellaElement_Parents_Resolver
   */
  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    HashMap<EObject, List<FilteringCriterion>> currentConstraintData = (HashMap<EObject, List<FilteringCriterion>>) ctx
        .getCurrentConstraintData();
    if (currentConstraintData == null) {
      currentConstraintData = new HashMap<EObject, List<FilteringCriterion>>();
      ctx.putCurrentConstraintData(currentConstraintData);
    }

    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    // check that it is batch validation
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof CapellaElement) {
        if (!FilteringUtils.isInstanceOfFilteringExcludedElements(eObj)) {
          List<IStatus> statuses = new ArrayList<>();
          // Features of element

          List<FilteringCriterion> filteringCriteria = ConstraintsUtil.getAssociatedCriteria(eObj,
              currentConstraintData);

          if (!filteringCriteria.isEmpty()) {

            // Loop through all childs
            Iterator<EObject> i = eObj.eContents().iterator();
            while (i.hasNext()) {
              EObject child = i.next();
              if (child instanceof CapellaElement) {
                if (!FilteringUtils.isInstanceOfFilteringExcludedElements(child)) {
                  List<FilteringCriterion> childFeatures = ConstraintsUtil.getAssociatedCriteria(child,
                      currentConstraintData);
                  if (!childFeatures.isEmpty()) {
                    // Calculate missingFeatures
                    List<FilteringCriterion> missingFeatures = ConstraintsUtil.missingFilteringCriteria(childFeatures,
                        filteringCriteria);
                    if (!missingFeatures.isEmpty()) {
                      statuses.add(ctx.createFailureStatus(ConstraintsUtil.getNameForMessage(eObj),
                          FilteringUtils.getCommaSeparatedVariabilityFeaturesList(missingFeatures),
                          ConstraintsUtil.getNameForMessage(child)));
                    }
                  }
                }
              }
            }
          }
          if (!statuses.isEmpty()) {
            return ConstraintStatus.createMultiStatus(ctx, statuses);
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }

}
