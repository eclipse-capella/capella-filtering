/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.filtering.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;

import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Criterion Set</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.filtering.impl.FilteringCriterionSetImpl#getFilteringCriteria <em>Filtering Criteria</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.impl.FilteringCriterionSetImpl#getVariabilityFeatures <em>Variability Features</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class FilteringCriterionSetImpl extends NamedElementImpl implements FilteringCriterionSet {

	/**
	 * The cached value of the '{@link #getFilteringCriteria() <em>Filtering Criteria</em>}' reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFilteringCriteria()
	 * @generated
	 * @ordered
	 */
	protected EList<FilteringCriterion> filteringCriteria;

	/**
	 * The cached value of the '{@link #getVariabilityFeatures() <em>Variability Features</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariabilityFeatures()
	 * @generated
	 * @ordered
	 */
	protected EList<FilteringCriterion> variabilityFeatures;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected FilteringCriterionSetImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FilteringPackage.Literals.FILTERING_CRITERION_SET;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public EList<FilteringCriterion> getFilteringCriteria() {

		if (filteringCriteria == null) {
			filteringCriteria = new EObjectResolvingEList<FilteringCriterion>(FilteringCriterion.class, this,
					FilteringPackage.FILTERING_CRITERION_SET__FILTERING_CRITERIA);
		}
		return filteringCriteria;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public EList<FilteringCriterion> getVariabilityFeatures() {

		if (variabilityFeatures == null) {
			variabilityFeatures = new EObjectResolvingEList<FilteringCriterion>(FilteringCriterion.class, this,
					FilteringPackage.FILTERING_CRITERION_SET__VARIABILITY_FEATURES);
		}
		return variabilityFeatures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case FilteringPackage.FILTERING_CRITERION_SET__FILTERING_CRITERIA:
			return getFilteringCriteria();
		case FilteringPackage.FILTERING_CRITERION_SET__VARIABILITY_FEATURES:
			return getVariabilityFeatures();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case FilteringPackage.FILTERING_CRITERION_SET__FILTERING_CRITERIA:
			getFilteringCriteria().clear();
			getFilteringCriteria().addAll((Collection<? extends FilteringCriterion>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case FilteringPackage.FILTERING_CRITERION_SET__FILTERING_CRITERIA:
			getFilteringCriteria().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case FilteringPackage.FILTERING_CRITERION_SET__FILTERING_CRITERIA:
			return filteringCriteria != null && !filteringCriteria.isEmpty();
		case FilteringPackage.FILTERING_CRITERION_SET__VARIABILITY_FEATURES:
			return variabilityFeatures != null && !variabilityFeatures.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // FilteringCriterionSetImpl