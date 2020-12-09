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
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.polarsys.capella.core.data.capellacommon.GenericTrace;

import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.NamingRule;
import org.polarsys.capella.core.data.capellacore.Trace;

import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;

import org.polarsys.capella.core.data.requirement.RequirementsTrace;

import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringCriterionPkg;
import org.polarsys.capella.filtering.FilteringPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Criterion Pkg</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.filtering.impl.FilteringCriterionPkgImpl#getOwnedTraces <em>Owned Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.impl.FilteringCriterionPkgImpl#getContainedGenericTraces <em>Contained Generic Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.impl.FilteringCriterionPkgImpl#getContainedRequirementsTraces <em>Contained Requirements Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.impl.FilteringCriterionPkgImpl#getNamingRules <em>Naming Rules</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.impl.FilteringCriterionPkgImpl#getOwnedFilteringCriteria <em>Owned Filtering Criteria</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.impl.FilteringCriterionPkgImpl#getOwnedFilteringCriterionPkgs <em>Owned Filtering Criterion Pkgs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FilteringCriterionPkgImpl extends NamedElementImpl implements FilteringCriterionPkg {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected FilteringCriterionPkgImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FilteringPackage.Literals.FILTERING_CRITERION_PKG;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@SuppressWarnings("unchecked")
	@Override
	public EList<Trace> getOwnedTraces() {

		return (EList<Trace>) eDynamicGet(FilteringPackage.FILTERING_CRITERION_PKG__OWNED_TRACES,
				CapellacorePackage.Literals.NAMESPACE__OWNED_TRACES, true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	@Override
	public EList<GenericTrace> getContainedGenericTraces() {

		return ECollections.emptyEList();

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	@Override
	public EList<RequirementsTrace> getContainedRequirementsTraces() {

		return ECollections.emptyEList();

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@SuppressWarnings("unchecked")
	@Override
	public EList<NamingRule> getNamingRules() {

		return (EList<NamingRule>) eDynamicGet(FilteringPackage.FILTERING_CRITERION_PKG__NAMING_RULES,
				CapellacorePackage.Literals.NAMESPACE__NAMING_RULES, true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@SuppressWarnings("unchecked")
	@Override
	public EList<FilteringCriterion> getOwnedFilteringCriteria() {

		return (EList<FilteringCriterion>) eDynamicGet(
				FilteringPackage.FILTERING_CRITERION_PKG__OWNED_FILTERING_CRITERIA,
				FilteringPackage.Literals.FILTERING_CRITERION_PKG__OWNED_FILTERING_CRITERIA, true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@SuppressWarnings("unchecked")
	@Override
	public EList<FilteringCriterionPkg> getOwnedFilteringCriterionPkgs() {

		return (EList<FilteringCriterionPkg>) eDynamicGet(
				FilteringPackage.FILTERING_CRITERION_PKG__OWNED_FILTERING_CRITERION_PKGS,
				FilteringPackage.Literals.FILTERING_CRITERION_PKG__OWNED_FILTERING_CRITERION_PKGS, true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case FilteringPackage.FILTERING_CRITERION_PKG__OWNED_TRACES:
			return ((InternalEList<?>) getOwnedTraces()).basicRemove(otherEnd, msgs);
		case FilteringPackage.FILTERING_CRITERION_PKG__NAMING_RULES:
			return ((InternalEList<?>) getNamingRules()).basicRemove(otherEnd, msgs);
		case FilteringPackage.FILTERING_CRITERION_PKG__OWNED_FILTERING_CRITERIA:
			return ((InternalEList<?>) getOwnedFilteringCriteria()).basicRemove(otherEnd, msgs);
		case FilteringPackage.FILTERING_CRITERION_PKG__OWNED_FILTERING_CRITERION_PKGS:
			return ((InternalEList<?>) getOwnedFilteringCriterionPkgs()).basicRemove(otherEnd, msgs);
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
		case FilteringPackage.FILTERING_CRITERION_PKG__OWNED_TRACES:
			return getOwnedTraces();
		case FilteringPackage.FILTERING_CRITERION_PKG__CONTAINED_GENERIC_TRACES:
			return getContainedGenericTraces();
		case FilteringPackage.FILTERING_CRITERION_PKG__CONTAINED_REQUIREMENTS_TRACES:
			return getContainedRequirementsTraces();
		case FilteringPackage.FILTERING_CRITERION_PKG__NAMING_RULES:
			return getNamingRules();
		case FilteringPackage.FILTERING_CRITERION_PKG__OWNED_FILTERING_CRITERIA:
			return getOwnedFilteringCriteria();
		case FilteringPackage.FILTERING_CRITERION_PKG__OWNED_FILTERING_CRITERION_PKGS:
			return getOwnedFilteringCriterionPkgs();
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
		case FilteringPackage.FILTERING_CRITERION_PKG__OWNED_TRACES:
			getOwnedTraces().clear();
			getOwnedTraces().addAll((Collection<? extends Trace>) newValue);
			return;
		case FilteringPackage.FILTERING_CRITERION_PKG__NAMING_RULES:
			getNamingRules().clear();
			getNamingRules().addAll((Collection<? extends NamingRule>) newValue);
			return;
		case FilteringPackage.FILTERING_CRITERION_PKG__OWNED_FILTERING_CRITERIA:
			getOwnedFilteringCriteria().clear();
			getOwnedFilteringCriteria().addAll((Collection<? extends FilteringCriterion>) newValue);
			return;
		case FilteringPackage.FILTERING_CRITERION_PKG__OWNED_FILTERING_CRITERION_PKGS:
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
		case FilteringPackage.FILTERING_CRITERION_PKG__OWNED_TRACES:
			getOwnedTraces().clear();
			return;
		case FilteringPackage.FILTERING_CRITERION_PKG__NAMING_RULES:
			getNamingRules().clear();
			return;
		case FilteringPackage.FILTERING_CRITERION_PKG__OWNED_FILTERING_CRITERIA:
			getOwnedFilteringCriteria().clear();
			return;
		case FilteringPackage.FILTERING_CRITERION_PKG__OWNED_FILTERING_CRITERION_PKGS:
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
		case FilteringPackage.FILTERING_CRITERION_PKG__OWNED_TRACES:
			return !getOwnedTraces().isEmpty();
		case FilteringPackage.FILTERING_CRITERION_PKG__CONTAINED_GENERIC_TRACES:
			return !getContainedGenericTraces().isEmpty();
		case FilteringPackage.FILTERING_CRITERION_PKG__CONTAINED_REQUIREMENTS_TRACES:
			return !getContainedRequirementsTraces().isEmpty();
		case FilteringPackage.FILTERING_CRITERION_PKG__NAMING_RULES:
			return !getNamingRules().isEmpty();
		case FilteringPackage.FILTERING_CRITERION_PKG__OWNED_FILTERING_CRITERIA:
			return !getOwnedFilteringCriteria().isEmpty();
		case FilteringPackage.FILTERING_CRITERION_PKG__OWNED_FILTERING_CRITERION_PKGS:
			return !getOwnedFilteringCriterionPkgs().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // FilteringCriterionPkgImpl