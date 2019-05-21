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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
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
	 * The cached value of the '{@link #getFilteringResults() <em>Filtering Results</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getFilteringResults()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractFilteringResult> filteringResults;

	/**
	 * The cached value of the '{@link #getOwnedFilteringResultPkgs() <em>Owned Filtering Result Pkgs</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOwnedFilteringResultPkgs()
	 * @generated
	 * @ordered
	 */
	protected EList<FilteringResultPkg> ownedFilteringResultPkgs;

	/**
	 * The cached value of the '{@link #getConfigurations() <em>Configurations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigurations()
	 * @generated
	 * @ordered
	 */
	protected EList<FilteringResult> configurations;

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

	public EList<AbstractFilteringResult> getFilteringResults() {

		if (filteringResults == null) {
			filteringResults = new EObjectContainmentEList<AbstractFilteringResult>(AbstractFilteringResult.class, this,
					FilteringPackage.FILTERING_RESULTS__FILTERING_RESULTS);
		}
		return filteringResults;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EList<FilteringResultPkg> getOwnedFilteringResultPkgs() {

		if (ownedFilteringResultPkgs == null) {
			ownedFilteringResultPkgs = new EObjectContainmentEList<FilteringResultPkg>(FilteringResultPkg.class, this,
					FilteringPackage.FILTERING_RESULTS__OWNED_FILTERING_RESULT_PKGS);
		}
		return ownedFilteringResultPkgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<FilteringResult> getConfigurations() {

		if (configurations == null) {
			configurations = new EObjectContainmentEList<FilteringResult>(FilteringResult.class, this,
					FilteringPackage.FILTERING_RESULTS__CONFIGURATIONS);
		}
		return configurations;
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
			return filteringResults != null && !filteringResults.isEmpty();
		case FilteringPackage.FILTERING_RESULTS__OWNED_FILTERING_RESULT_PKGS:
			return ownedFilteringResultPkgs != null && !ownedFilteringResultPkgs.isEmpty();
		case FilteringPackage.FILTERING_RESULTS__CONFIGURATIONS:
			return configurations != null && !configurations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // FilteringResultsImpl