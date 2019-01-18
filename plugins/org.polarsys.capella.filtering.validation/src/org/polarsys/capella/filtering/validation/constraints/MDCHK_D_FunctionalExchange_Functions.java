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
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

/**
 * 
 */
public class MDCHK_D_FunctionalExchange_Functions extends AbstractModelConstraint {

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    // check that it is batch validation
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof FunctionalExchange) {
        FunctionalExchange fe = (FunctionalExchange) eObj;

        ActivityNode functionOutputPort = fe.getSource();
        ActivityNode functionInputPort = fe.getTarget();

        // The activity nodes are childs of the function element
        EObject outputFunctionObject = functionOutputPort.eContainer();
        EObject inputFunctionObject = functionInputPort.eContainer();

        List<FilteringCriterion> feFeatures = FilteringUtils.getAssociatedCriteria(fe);

        List<FilteringCriterion> outputFunctionFeatures = FilteringUtils.getAssociatedCriteria(outputFunctionObject);
        // Calculate missingFeatures of the output function
        List<FilteringCriterion> missingOutputFeatures = ConstraintsUtil.missingFilteringCriteria(feFeatures,
            outputFunctionFeatures);

        List<FilteringCriterion> inputFunctionFeatures = FilteringUtils.getAssociatedCriteria(inputFunctionObject);

        // Calculate missingFeatures of the input function
        List<FilteringCriterion> missingInputFeatures = ConstraintsUtil.missingFilteringCriteria(feFeatures,
            inputFunctionFeatures);

        String message = ""; //$NON-NLS-1$
        if (!missingOutputFeatures.isEmpty()) {
          message = ConstraintsUtil.getNameForMessage(outputFunctionObject) + " is missing " + "[" //$NON-NLS-2$
              + FilteringUtils.getCommaSeparatedVariabilityFeaturesList(missingOutputFeatures) + "]"; //$NON-NLS-1$
        }
        if (!missingInputFeatures.isEmpty()) {
          if (!message.isEmpty()) {
            message = message + " and ";
          }
          message = message + ConstraintsUtil.getNameForMessage(inputFunctionObject) + " is missing " + "[" //$NON-NLS-2$
              + FilteringUtils.getCommaSeparatedVariabilityFeaturesList(missingInputFeatures) + "]"; //$NON-NLS-1$

        }
        if (!message.isEmpty()) {
          return ctx.createFailureStatus(ConstraintsUtil.getNameForMessage(fe),
              FilteringUtils.getCommaSeparatedVariabilityFeaturesList(feFeatures), message);
        }
      }
    }
    return ctx.createSuccessStatus();
  }

}
