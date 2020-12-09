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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Result Pkg</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.filtering.FilteringResultPkg#getOwnedFilteringResults <em>Owned Filtering Results</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.FilteringResultPkg#getOwnedFilteringResultPkgs <em>Owned Filtering Result Pkgs</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.filtering.FilteringPackage#getFilteringResultPkg()
 * @model
 * @generated
 */

public interface FilteringResultPkg extends Namespace {

	/**
	 * Returns the value of the '<em><b>Owned Filtering Results</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.filtering.AbstractFilteringResult}.
	
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Filtering Results</em>' reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Filtering Results</em>' containment reference list.
	 * @see org.polarsys.capella.filtering.FilteringPackage#getFilteringResultPkg_OwnedFilteringResults()
	 * @model containment="true"
	 * @generated
	 */

	EList<AbstractFilteringResult> getOwnedFilteringResults();

	/**
	 * Returns the value of the '<em><b>Owned Filtering Result Pkgs</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.filtering.FilteringResultPkg}.
	
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Filtering Result Pkgs</em>' reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Filtering Result Pkgs</em>' containment reference list.
	 * @see org.polarsys.capella.filtering.FilteringPackage#getFilteringResultPkg_OwnedFilteringResultPkgs()
	 * @model containment="true"
	 * @generated
	 */

	EList<FilteringResultPkg> getOwnedFilteringResultPkgs();

} // FilteringResultPkg
