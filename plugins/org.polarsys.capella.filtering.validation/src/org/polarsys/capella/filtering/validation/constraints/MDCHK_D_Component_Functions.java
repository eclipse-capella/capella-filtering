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
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

/**
 * 
 */
public class MDCHK_D_Component_Functions extends AbstractModelConstraint {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		// check that it is batch validation
		if (eType == EMFEventType.NULL) {
			List<IStatus> statuses = new ArrayList<>();
			if (eObj instanceof Component) {
				Component component = (Component) eObj;
				List<FilteringCriterion> componentFeatures = FilteringUtils.getAssociatedCriteria(component);
				for (AbstractFunction function : component.getAllocatedFunctions()) {
					List<FilteringCriterion> functionFeatures = FilteringUtils.getAssociatedCriteria(function);
					List<FilteringCriterion> missingFeatures = ConstraintsUtil
							.missingFilteringCriteria(functionFeatures, componentFeatures);
					if (!missingFeatures.isEmpty()) {
						statuses.add(ctx.createFailureStatus(ConstraintsUtil.getNameForMessage(component),
								FilteringUtils.getCommaSeparatedVariabilityFeaturesList(missingFeatures),
								ConstraintsUtil.getNameForMessage(function)));
					}
				}
			}
			if (!statuses.isEmpty()) {
				return ConstraintStatus.createMultiStatus(ctx, statuses);
			}
		}
		return ctx.createSuccessStatus();
	}

}
