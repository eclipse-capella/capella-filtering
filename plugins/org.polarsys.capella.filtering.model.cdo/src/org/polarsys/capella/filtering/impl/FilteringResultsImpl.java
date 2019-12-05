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

import org.polarsys.capella.filtering.AbstractFilteringResult;
import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.capella.filtering.FilteringResult;
import org.polarsys.capella.filtering.FilteringResultPkg;
import org.polarsys.capella.filtering.FilteringResults;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Results</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.filtering.impl.FilteringResultsImpl#getFilteringResults <em>Filtering Results</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.impl.FilteringResultsImpl#getOwnedFilteringResultPkgs <em>Owned Filtering Result Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.impl.FilteringResultsImpl#getConfigurations <em>Configurations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FilteringResultsImpl extends NamedElementImpl implements FilteringResults {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected FilteringResultsImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FilteringPackage.Literals.FILTERING_RESULTS;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@SuppressWarnings("unchecked")
	@Override
	public EList<AbstractFilteringResult> getFilteringResults() {

		return (EList<AbstractFilteringResult>) eDynamicGet(FilteringPackage.FILTERING_RESULTS__FILTERING_RESULTS,
				FilteringPackage.Literals.FILTERING_RESULTS__FILTERING_RESULTS, true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@SuppressWarnings("unchecked")
	@Override
	public EList<FilteringResultPkg> getOwnedFilteringResultPkgs() {

		return (EList<FilteringResultPkg>) eDynamicGet(FilteringPackage.FILTERING_RESULTS__OWNED_FILTERING_RESULT_PKGS,
				FilteringPackage.Literals.FILTERING_RESULTS__OWNED_FILTERING_RESULT_PKGS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@SuppressWarnings("unchecked")
	@Override
	public EList<FilteringResult> getConfigurations() {

		return (EList<FilteringResult>) eDynamicGet(FilteringPackage.FILTERING_RESULTS__CONFIGURATIONS,
				FilteringPackage.Literals.FILTERING_RESULTS__CONFIGURATIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case FilteringPackage.FILTERING_RESULTS__FILTERING_RESULTS:
			return ((InternalEList<?>) getFilteringResults()).basicRemove(otherEnd, msgs);
		case FilteringPackage.FILTERING_RESULTS__OWNED_FILTERING_RESULT_PKGS:
			return ((InternalEList<?>) getOwnedFilteringResultPkgs()).basicRemove(otherEnd, msgs);
		case FilteringPackage.FILTERING_RESULTS__CONFIGURATIONS:
			return ((InternalEList<?>) getConfigurations()).basicRemove(otherEnd, msgs);
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
		case FilteringPackage.FILTERING_RESULTS__FILTERING_RESULTS:
			return getFilteringResults();
		case FilteringPackage.FILTERING_RESULTS__OWNED_FILTERING_RESULT_PKGS:
			return getOwnedFilteringResultPkgs();
		case FilteringPackage.FILTERING_RESULTS__CONFIGURATIONS:
			return getConfigurations();
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
		case FilteringPackage.FILTERING_RESULTS__FILTERING_RESULTS:
			getFilteringResults().clear();
			getFilteringResults().addAll((Collection<? extends AbstractFilteringResult>) newValue);
			return;
		case FilteringPackage.FILTERING_RESULTS__OWNED_FILTERING_RESULT_PKGS:
			getOwnedFilteringResultPkgs().clear();
			getOwnedFilteringResultPkgs().addAll((Collection<? extends FilteringResultPkg>) newValue);
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
		case FilteringPackage.FILTERING_RESULTS__FILTERING_RESULTS:
			getFilteringResults().clear();
			return;
		case FilteringPackage.FILTERING_RESULTS__OWNED_FILTERING_RESULT_PKGS:
			getOwnedFilteringResultPkgs().clear();
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
		case FilteringPackage.FILTERING_RESULTS__FILTERING_RESULTS:
			return !getFilteringResults().isEmpty();
		case FilteringPackage.FILTERING_RESULTS__OWNED_FILTERING_RESULT_PKGS:
			return !getOwnedFilteringResultPkgs().isEmpty();
		case FilteringPackage.FILTERING_RESULTS__CONFIGURATIONS:
			return !getConfigurations().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // FilteringResultsImpl