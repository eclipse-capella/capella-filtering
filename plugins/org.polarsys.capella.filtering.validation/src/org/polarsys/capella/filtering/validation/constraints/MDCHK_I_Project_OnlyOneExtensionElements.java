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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.filtering.CreationDefaultFilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringModel;
import org.polarsys.capella.filtering.FilteringResults;
import org.polarsys.kitalpha.emde.model.ElementExtension;

/**
 * 
 */
public class MDCHK_I_Project_OnlyOneExtensionElements extends AbstractModelConstraint {

	@Override
	public IStatus validate(IValidationContext ctx) {
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();

		// check that it is batch validation
		if (eType == EMFEventType.NULL) {
			if (eObj instanceof SystemEngineering) {
				SystemEngineering systemEngineering = (SystemEngineering) eObj;

				// Message for the error
				String message1 = "";

				boolean oneFilteringModelFound = false;
				boolean oneConfigurationsFound = false;
				boolean oneCreationDefaultFeatureSetFound = false;

				for (ElementExtension elementExtension : systemEngineering.getOwnedExtensions()) {
					if (elementExtension instanceof FilteringModel) {
						if (oneFilteringModelFound) {
							// One was previously found so this is the second.
							message1 = message1 + "FilteringModel" + " "; //$NON-NLS-2$
						}
						oneFilteringModelFound = true;
					} else if (elementExtension instanceof FilteringResults) {
						if (oneConfigurationsFound) {
							// One was previously found so this is the second.
							message1 = message1 + "Filtering Results" + " "; //$NON-NLS-2$
						}
						oneConfigurationsFound = true;
					} else if (elementExtension instanceof CreationDefaultFilteringCriterionSet) {
						if (oneCreationDefaultFeatureSetFound) {
							// One was previously found so this is the second.
							message1 = message1 + "CreationDefaultFilteringCriterionSet" + " "; //$NON-NLS-2$
						}
						oneCreationDefaultFeatureSetFound = true;
					}
				}

				// There were errors
				if (!message1.isEmpty()) {
					return ctx.createFailureStatus(ConstraintsUtil.getNameForMessage(systemEngineering), message1);
				}
			}
		}

		// Everything ok
		return ctx.createSuccessStatus();
	}

}
