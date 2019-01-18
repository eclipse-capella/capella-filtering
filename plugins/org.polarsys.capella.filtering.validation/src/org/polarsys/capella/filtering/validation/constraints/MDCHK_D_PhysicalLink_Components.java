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
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

/**
 * 
 */
public class MDCHK_D_PhysicalLink_Components extends AbstractModelConstraint {

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    // check that it is batch validation
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof PhysicalLink) {
        PhysicalLink physicalLink = (PhysicalLink) eObj;
        EObject target = physicalLink.getTargetPhysicalPort();
        EObject source = physicalLink.getSourcePhysicalPort();

        // The activity nodes are childs of the function element
        EObject outputObject = target.eContainer();
        EObject inputObject = source.eContainer();

        List<FilteringCriterion> feFeatures = FilteringUtils.getAssociatedCriteria(physicalLink);

        List<FilteringCriterion> outputFunctionFeatures = FilteringUtils.getAssociatedCriteria(outputObject);

        // Calculate missingFeatures of the output function
        List<FilteringCriterion> missingOutputFeatures = ConstraintsUtil.missingFilteringCriteria(feFeatures,
            outputFunctionFeatures);

        List<FilteringCriterion> inputFunctionFeatures = FilteringUtils.getAssociatedCriteria(inputObject);

        // Calculate missingFeatures of the input function
        List<FilteringCriterion> missingInputFeatures = ConstraintsUtil.missingFilteringCriteria(feFeatures,
            inputFunctionFeatures);

        String message = ""; //$NON-NLS-1$
        if (!missingOutputFeatures.isEmpty()) {
          message = ConstraintsUtil.getNameForMessage(outputObject) + " is missing " + "[" //$NON-NLS-2$
              + FilteringUtils.getCommaSeparatedVariabilityFeaturesList(missingOutputFeatures) + "]"; //$NON-NLS-1$
        }
        if (!missingInputFeatures.isEmpty()) {
          if (!message.isEmpty()) {
            message = message + " and ";
          }
          message = message + ConstraintsUtil.getNameForMessage(inputObject) + " is missing " + "[" //$NON-NLS-2$
              + FilteringUtils.getCommaSeparatedVariabilityFeaturesList(missingInputFeatures) + "]"; //$NON-NLS-1$

        }
        if (!message.isEmpty()) {
          return ctx.createFailureStatus(ConstraintsUtil.getNameForMessage(physicalLink),
              FilteringUtils.getCommaSeparatedVariabilityFeaturesList(feFeatures), message);
        }
      }
    }
    return ctx.createSuccessStatus();
  }

}
