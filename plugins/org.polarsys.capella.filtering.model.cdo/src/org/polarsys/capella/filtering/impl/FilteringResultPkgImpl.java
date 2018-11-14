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
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.polarsys.capella.core.data.capellacommon.GenericTrace;

import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.NamingRule;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.core.data.capellacore.Trace;

import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;

import org.polarsys.capella.core.data.requirement.RequirementsTrace;

import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.capella.filtering.FilteringResult;
import org.polarsys.capella.filtering.FilteringResultPkg;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Result
 * Pkg</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.filtering.impl.FilteringResultPkgImpl#getOwnedTraces <em>Owned Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.impl.FilteringResultPkgImpl#getContainedGenericTraces <em>Contained Generic Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.impl.FilteringResultPkgImpl#getContainedRequirementsTraces <em>Contained Requirements Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.impl.FilteringResultPkgImpl#getNamingRules <em>Naming Rules</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.impl.FilteringResultPkgImpl#getOwnedFilteringResults <em>Owned Filtering Results</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.impl.FilteringResultPkgImpl#getOwnedFilteringResultPkgs <em>Owned Filtering Result Pkgs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FilteringResultPkgImpl extends NamedElementImpl implements FilteringResultPkg {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected FilteringResultPkgImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FilteringPackage.Literals.FILTERING_RESULT_PKG;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@SuppressWarnings("unchecked")
	public EList<Trace> getOwnedTraces() {

		return (EList<Trace>) eDynamicGet(FilteringPackage.FILTERING_RESULT_PKG__OWNED_TRACES,
				CapellacorePackage.Literals.NAMESPACE__OWNED_TRACES, true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	public EList<GenericTrace> getContainedGenericTraces() {

		return ECollections.emptyEList();

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	public EList<RequirementsTrace> getContainedRequirementsTraces() {

		return ECollections.emptyEList();

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@SuppressWarnings("unchecked")
	public EList<NamingRule> getNamingRules() {

		return (EList<NamingRule>) eDynamicGet(FilteringPackage.FILTERING_RESULT_PKG__NAMING_RULES,
				CapellacorePackage.Literals.NAMESPACE__NAMING_RULES, true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@SuppressWarnings("unchecked")
	public EList<FilteringResult> getOwnedFilteringResults() {

		return (EList<FilteringResult>) eDynamicGet(FilteringPackage.FILTERING_RESULT_PKG__OWNED_FILTERING_RESULTS,
				FilteringPackage.Literals.FILTERING_RESULT_PKG__OWNED_FILTERING_RESULTS, true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@SuppressWarnings("unchecked")
	public EList<FilteringResultPkg> getOwnedFilteringResultPkgs() {

		return (EList<FilteringResultPkg>) eDynamicGet(
				FilteringPackage.FILTERING_RESULT_PKG__OWNED_FILTERING_RESULT_PKGS,
				FilteringPackage.Literals.FILTERING_RESULT_PKG__OWNED_FILTERING_RESULT_PKGS, true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case FilteringPackage.FILTERING_RESULT_PKG__OWNED_TRACES:
			return ((InternalEList<?>) getOwnedTraces()).basicRemove(otherEnd, msgs);
		case FilteringPackage.FILTERING_RESULT_PKG__NAMING_RULES:
			return ((InternalEList<?>) getNamingRules()).basicRemove(otherEnd, msgs);
		case FilteringPackage.FILTERING_RESULT_PKG__OWNED_FILTERING_RESULTS:
			return ((InternalEList<?>) getOwnedFilteringResults()).basicRemove(otherEnd, msgs);
		case FilteringPackage.FILTERING_RESULT_PKG__OWNED_FILTERING_RESULT_PKGS:
			return ((InternalEList<?>) getOwnedFilteringResultPkgs()).basicRemove(otherEnd, msgs);
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
		case FilteringPackage.FILTERING_RESULT_PKG__OWNED_TRACES:
			return getOwnedTraces();
		case FilteringPackage.FILTERING_RESULT_PKG__CONTAINED_GENERIC_TRACES:
			return getContainedGenericTraces();
		case FilteringPackage.FILTERING_RESULT_PKG__CONTAINED_REQUIREMENTS_TRACES:
			return getContainedRequirementsTraces();
		case FilteringPackage.FILTERING_RESULT_PKG__NAMING_RULES:
			return getNamingRules();
		case FilteringPackage.FILTERING_RESULT_PKG__OWNED_FILTERING_RESULTS:
			return getOwnedFilteringResults();
		case FilteringPackage.FILTERING_RESULT_PKG__OWNED_FILTERING_RESULT_PKGS:
			return getOwnedFilteringResultPkgs();
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
		case FilteringPackage.FILTERING_RESULT_PKG__OWNED_TRACES:
			getOwnedTraces().clear();
			getOwnedTraces().addAll((Collection<? extends Trace>) newValue);
			return;
		case FilteringPackage.FILTERING_RESULT_PKG__NAMING_RULES:
			getNamingRules().clear();
			getNamingRules().addAll((Collection<? extends NamingRule>) newValue);
			return;
		case FilteringPackage.FILTERING_RESULT_PKG__OWNED_FILTERING_RESULTS:
			getOwnedFilteringResults().clear();
			getOwnedFilteringResults().addAll((Collection<? extends FilteringResult>) newValue);
			return;
		case FilteringPackage.FILTERING_RESULT_PKG__OWNED_FILTERING_RESULT_PKGS:
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
		case FilteringPackage.FILTERING_RESULT_PKG__OWNED_TRACES:
			getOwnedTraces().clear();
			return;
		case FilteringPackage.FILTERING_RESULT_PKG__NAMING_RULES:
			getNamingRules().clear();
			return;
		case FilteringPackage.FILTERING_RESULT_PKG__OWNED_FILTERING_RESULTS:
			getOwnedFilteringResults().clear();
			return;
		case FilteringPackage.FILTERING_RESULT_PKG__OWNED_FILTERING_RESULT_PKGS:
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
		case FilteringPackage.FILTERING_RESULT_PKG__OWNED_TRACES:
			return !getOwnedTraces().isEmpty();
		case FilteringPackage.FILTERING_RESULT_PKG__CONTAINED_GENERIC_TRACES:
			return !getContainedGenericTraces().isEmpty();
		case FilteringPackage.FILTERING_RESULT_PKG__CONTAINED_REQUIREMENTS_TRACES:
			return !getContainedRequirementsTraces().isEmpty();
		case FilteringPackage.FILTERING_RESULT_PKG__NAMING_RULES:
			return !getNamingRules().isEmpty();
		case FilteringPackage.FILTERING_RESULT_PKG__OWNED_FILTERING_RESULTS:
			return !getOwnedFilteringResults().isEmpty();
		case FilteringPackage.FILTERING_RESULT_PKG__OWNED_FILTERING_RESULT_PKGS:
			return !getOwnedFilteringResultPkgs().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // FilteringResultPkgImpl