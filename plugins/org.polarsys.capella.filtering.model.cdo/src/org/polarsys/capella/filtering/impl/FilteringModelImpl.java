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

package org.polarsys.capella.filtering.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;

import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringCriterionPkg;
import org.polarsys.capella.filtering.FilteringModel;
import org.polarsys.capella.filtering.FilteringPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Model</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.filtering.impl.FilteringModelImpl#getOwnedFilteringCriteria <em>Owned Filtering Criteria</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.impl.FilteringModelImpl#getOwnedFilteringCriterionPkgs <em>Owned Filtering Criterion Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.impl.FilteringModelImpl#getOwnedVariabilityFeatures <em>Owned Variability Features</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FilteringModelImpl extends NamedElementImpl implements FilteringModel {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected FilteringModelImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FilteringPackage.Literals.FILTERING_MODEL;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@SuppressWarnings("unchecked")
	@Override
	public EList<FilteringCriterion> getOwnedFilteringCriteria() {

		return (EList<FilteringCriterion>) eDynamicGet(FilteringPackage.FILTERING_MODEL__OWNED_FILTERING_CRITERIA,
				FilteringPackage.Literals.FILTERING_MODEL__OWNED_FILTERING_CRITERIA, true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@SuppressWarnings("unchecked")
	@Override
	public EList<FilteringCriterionPkg> getOwnedFilteringCriterionPkgs() {

		return (EList<FilteringCriterionPkg>) eDynamicGet(
				FilteringPackage.FILTERING_MODEL__OWNED_FILTERING_CRITERION_PKGS,
				FilteringPackage.Literals.FILTERING_MODEL__OWNED_FILTERING_CRITERION_PKGS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@SuppressWarnings("unchecked")
	@Override
	public EList<FilteringCriterion> getOwnedVariabilityFeatures() {

		return (EList<FilteringCriterion>) eDynamicGet(FilteringPackage.FILTERING_MODEL__OWNED_VARIABILITY_FEATURES,
				FilteringPackage.Literals.FILTERING_MODEL__OWNED_VARIABILITY_FEATURES, true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case FilteringPackage.FILTERING_MODEL__OWNED_FILTERING_CRITERIA:
			return ((InternalEList<?>) getOwnedFilteringCriteria()).basicRemove(otherEnd, msgs);
		case FilteringPackage.FILTERING_MODEL__OWNED_FILTERING_CRITERION_PKGS:
			return ((InternalEList<?>) getOwnedFilteringCriterionPkgs()).basicRemove(otherEnd, msgs);
		case FilteringPackage.FILTERING_MODEL__OWNED_VARIABILITY_FEATURES:
			return ((InternalEList<?>) getOwnedVariabilityFeatures()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case FilteringPackage.FILTERING_MODEL__OWNED_FILTERING_CRITERIA:
			return getOwnedFilteringCriteria();
		case FilteringPackage.FILTERING_MODEL__OWNED_FILTERING_CRITERION_PKGS:
			return getOwnedFilteringCriterionPkgs();
		case FilteringPackage.FILTERING_MODEL__OWNED_VARIABILITY_FEATURES:
			return getOwnedVariabilityFeatures();
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
		case FilteringPackage.FILTERING_MODEL__OWNED_FILTERING_CRITERIA:
			getOwnedFilteringCriteria().clear();
			getOwnedFilteringCriteria().addAll((Collection<? extends FilteringCriterion>) newValue);
			return;
		case FilteringPackage.FILTERING_MODEL__OWNED_FILTERING_CRITERION_PKGS:
			getOwnedFilteringCriterionPkgs().clear();
			getOwnedFilteringCriterionPkgs().addAll((Collection<? extends FilteringCriterionPkg>) newValue);
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
		case FilteringPackage.FILTERING_MODEL__OWNED_FILTERING_CRITERIA:
			getOwnedFilteringCriteria().clear();
			return;
		case FilteringPackage.FILTERING_MODEL__OWNED_FILTERING_CRITERION_PKGS:
			getOwnedFilteringCriterionPkgs().clear();
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
		case FilteringPackage.FILTERING_MODEL__OWNED_FILTERING_CRITERIA:
			return !getOwnedFilteringCriteria().isEmpty();
		case FilteringPackage.FILTERING_MODEL__OWNED_FILTERING_CRITERION_PKGS:
			return !getOwnedFilteringCriterionPkgs().isEmpty();
		case FilteringPackage.FILTERING_MODEL__OWNED_VARIABILITY_FEATURES:
			return !getOwnedVariabilityFeatures().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // FilteringModelImpl