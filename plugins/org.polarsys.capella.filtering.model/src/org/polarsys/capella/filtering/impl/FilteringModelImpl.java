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

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
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
	 * The cached value of the '{@link #getOwnedFilteringCriteria() <em>Owned Filtering Criteria</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOwnedFilteringCriteria()
	 * @generated
	 * @ordered
	 */
	protected EList<FilteringCriterion> ownedFilteringCriteria;

	/**
	 * The cached value of the '{@link #getOwnedFilteringCriterionPkgs() <em>Owned Filtering Criterion Pkgs</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOwnedFilteringCriterionPkgs()
	 * @generated
	 * @ordered
	 */
	protected EList<FilteringCriterionPkg> ownedFilteringCriterionPkgs;

	/**
	 * The cached value of the '{@link #getOwnedVariabilityFeatures() <em>Owned Variability Features</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedVariabilityFeatures()
	 * @generated
	 * @ordered
	 */
	protected EList<FilteringCriterion> ownedVariabilityFeatures;

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

	@Override
	public EList<FilteringCriterion> getOwnedFilteringCriteria() {

		if (ownedFilteringCriteria == null) {
			ownedFilteringCriteria = new EObjectContainmentEList<FilteringCriterion>(FilteringCriterion.class, this,
					FilteringPackage.FILTERING_MODEL__OWNED_FILTERING_CRITERIA);
		}
		return ownedFilteringCriteria;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public EList<FilteringCriterionPkg> getOwnedFilteringCriterionPkgs() {

		if (ownedFilteringCriterionPkgs == null) {
			ownedFilteringCriterionPkgs = new EObjectContainmentEList<FilteringCriterionPkg>(
					FilteringCriterionPkg.class, this,
					FilteringPackage.FILTERING_MODEL__OWNED_FILTERING_CRITERION_PKGS);
		}
		return ownedFilteringCriterionPkgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public EList<FilteringCriterion> getOwnedVariabilityFeatures() {

		if (ownedVariabilityFeatures == null) {
			ownedVariabilityFeatures = new EObjectContainmentEList<FilteringCriterion>(FilteringCriterion.class, this,
					FilteringPackage.FILTERING_MODEL__OWNED_VARIABILITY_FEATURES);
		}
		return ownedVariabilityFeatures;
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
			return ownedFilteringCriteria != null && !ownedFilteringCriteria.isEmpty();
		case FilteringPackage.FILTERING_MODEL__OWNED_FILTERING_CRITERION_PKGS:
			return ownedFilteringCriterionPkgs != null && !ownedFilteringCriterionPkgs.isEmpty();
		case FilteringPackage.FILTERING_MODEL__OWNED_VARIABILITY_FEATURES:
			return ownedVariabilityFeatures != null && !ownedVariabilityFeatures.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // FilteringModelImpl