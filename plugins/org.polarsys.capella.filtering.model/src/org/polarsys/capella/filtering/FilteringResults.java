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

package org.polarsys.capella.filtering;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.capellacore.NamedElement;

import org.polarsys.kitalpha.emde.model.ElementExtension;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Results</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.filtering.FilteringResults#getFilteringResults <em>Filtering Results</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.FilteringResults#getOwnedFilteringResultPkgs <em>Owned Filtering Result Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.FilteringResults#getConfigurations <em>Configurations</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.filtering.FilteringPackage#getFilteringResults()
 * @model annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/constraint ExtendedElement='http://www.polarsys.org/capella/core/modeller/1.3.0#//SystemEngineering'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='null' usage\040guideline='null' used\040in\040levels='null' usage\040examples='null' constraints='null'"
 * @generated
 */

public interface FilteringResults extends NamedElement, ElementExtension {

	/**
	 * Returns the value of the '<em><b>Filtering Results</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.filtering.AbstractFilteringResult}.
	
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filtering Results</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filtering Results</em>' containment reference list.
	 * @see org.polarsys.capella.filtering.FilteringPackage#getFilteringResults_FilteringResults()
	 * @model containment="true"
	 *        annotation="http://www.polarsys.org/capella/semantic"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='null' usage\040guideline='null' used\040in\040levels='null' usage\040examples='null' constraints='null'"
	 * @generated
	 */

	EList<AbstractFilteringResult> getFilteringResults();

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
	 * @see org.polarsys.capella.filtering.FilteringPackage#getFilteringResults_OwnedFilteringResultPkgs()
	 * @model containment="true"
	 * @generated
	 */

	EList<FilteringResultPkg> getOwnedFilteringResultPkgs();

	/**
	 * Returns the value of the '<em><b>Configurations</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.filtering.FilteringResult}.
	
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Configurations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Configurations</em>' containment reference list.
	 * @see org.polarsys.capella.filtering.FilteringPackage#getFilteringResults_Configurations()
	 * @model containment="true" changeable="false"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation deprecated='true'"
	 * @generated
	 */

	EList<FilteringResult> getConfigurations();

} // FilteringResults
