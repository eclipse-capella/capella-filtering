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
/**
 * <copyright>
 *
 * </copyright>
 */

package org.polarsys.capella.filtering.model.helpers;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;
import org.polarsys.capella.filtering.CreationDefaultFilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringCriterionPkg;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringModel;
import org.polarsys.capella.filtering.FilteringResult;
import org.polarsys.capella.filtering.FilteringResultPkg;
import org.polarsys.capella.filtering.FilteringResults;

/**
 * @generated
 */
public class FilteringPackageHelper implements IHelper {

	/**
	 * @generated
	 */
	public Object getValue(EObject object, EStructuralFeature feature, EAnnotation annotation) {
		Object ret = null;

		if (ret == null && object instanceof FilteringModel) {
			ret = FilteringModelHelper.getInstance().doSwitch((FilteringModel) object, feature);
		}
		if (ret == null && object instanceof FilteringCriterion) {
			ret = FilteringCriterionHelper.getInstance().doSwitch((FilteringCriterion) object, feature);
		}
		if (ret == null && object instanceof FilteringCriterionSet) {
			ret = FilteringCriterionSetHelper.getInstance().doSwitch((FilteringCriterionSet) object, feature);
		}
		if (ret == null && object instanceof FilteringResults) {
			ret = FilteringResultsHelper.getInstance().doSwitch((FilteringResults) object, feature);
		}
		if (ret == null && object instanceof FilteringResult) {
			ret = FilteringResultHelper.getInstance().doSwitch((FilteringResult) object, feature);
		}
		if (ret == null && object instanceof AssociatedFilteringCriterionSet) {
			ret = AssociatedFilteringCriterionSetHelper.getInstance().doSwitch((AssociatedFilteringCriterionSet) object,
					feature);
		}
		if (ret == null && object instanceof CreationDefaultFilteringCriterionSet) {
			ret = CreationDefaultFilteringCriterionSetHelper.getInstance()
					.doSwitch((CreationDefaultFilteringCriterionSet) object, feature);
		}
		if (ret == null && object instanceof FilteringResultPkg) {
			ret = FilteringResultPkgHelper.getInstance().doSwitch((FilteringResultPkg) object, feature);
		}
		if (ret == null && object instanceof FilteringCriterionPkg) {
			ret = FilteringCriterionPkgHelper.getInstance().doSwitch((FilteringCriterionPkg) object, feature);
		}
		return ret;
	}

}
