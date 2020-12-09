/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.filtering;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.Structure;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Criterion Pkg</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.filtering.FilteringCriterionPkg#getOwnedFilteringCriteria <em>Owned Filtering Criteria</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.FilteringCriterionPkg#getOwnedFilteringCriterionPkgs <em>Owned Filtering Criterion Pkgs</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.filtering.FilteringPackage#getFilteringCriterionPkg()
 * @model
 * @generated
 */

public interface FilteringCriterionPkg extends Namespace {

	/**
	 * Returns the value of the '<em><b>Owned Filtering Criteria</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.filtering.FilteringCriterion}.
	
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Filtering Criteria</em>' reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Filtering Criteria</em>' containment reference list.
	 * @see org.polarsys.capella.filtering.FilteringPackage#getFilteringCriterionPkg_OwnedFilteringCriteria()
	 * @model containment="true"
	 * @generated
	 */

	EList<FilteringCriterion> getOwnedFilteringCriteria();

	/**
	 * Returns the value of the '<em><b>Owned Filtering Criterion Pkgs</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.filtering.FilteringCriterionPkg}.
	
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Filtering Criterion Pkgs</em>' reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Filtering Criterion Pkgs</em>' containment reference list.
	 * @see org.polarsys.capella.filtering.FilteringPackage#getFilteringCriterionPkg_OwnedFilteringCriterionPkgs()
	 * @model containment="true"
	 * @generated
	 */

	EList<FilteringCriterionPkg> getOwnedFilteringCriterionPkgs();

} // FilteringCriterionPkg
