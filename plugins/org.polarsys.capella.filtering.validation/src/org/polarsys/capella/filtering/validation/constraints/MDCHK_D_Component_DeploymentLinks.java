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
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

/**
 * 
 */
public class MDCHK_D_Component_DeploymentLinks extends AbstractModelConstraint {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		// check that it is batch validation
		if (eType == EMFEventType.NULL) {
			if (eObj instanceof DeploymentTarget) {
				List<IStatus> statuses = new ArrayList<>();
				DeploymentTarget location = (DeploymentTarget) eObj;

				// For each deployment link
				for (AbstractDeploymentLink dLink : location.getDeploymentLinks()) {
					List<FilteringCriterion> targetFeatures = FilteringUtils.getAssociatedCriteria(location);
					DeployableElement deployedElement = dLink.getDeployedElement();
					List<FilteringCriterion> deployedElementFeatures = FilteringUtils
							.getAssociatedCriteria(deployedElement);
					List<FilteringCriterion> missingFeatures = ConstraintsUtil
							.missingFilteringCriteria(deployedElementFeatures, targetFeatures);

					if (!missingFeatures.isEmpty()) {
						statuses.add(ctx.createFailureStatus(ConstraintsUtil.getNameForMessage(location),
								FilteringUtils.getCommaSeparatedVariabilityFeaturesList(missingFeatures),
								ConstraintsUtil.getNameForMessage(deployedElement)));
					}
				}
				if (!statuses.isEmpty()) {
					return ConstraintStatus.createMultiStatus(ctx, statuses);
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}
