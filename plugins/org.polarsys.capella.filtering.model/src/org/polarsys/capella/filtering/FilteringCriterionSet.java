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

package org.polarsys.capella.filtering;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.capellacore.NamedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Criterion Set</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.filtering.FilteringCriterionSet#getFilteringCriteria <em>Filtering Criteria</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.FilteringCriterionSet#getVariabilityFeatures <em>Variability Features</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.filtering.FilteringPackage#getFilteringCriterionSet()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='null' usage\040guideline='null' used\040in\040levels='null' usage\040examples='null' constraints='null'"
 * @generated
 */

public interface FilteringCriterionSet extends NamedElement {

	/**
	 * Returns the value of the '<em><b>Filtering Criteria</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.filtering.FilteringCriterion}.
	
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filtering Criteria</em>' reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filtering Criteria</em>' reference list.
	 * @see org.polarsys.capella.filtering.FilteringPackage#getFilteringCriterionSet_FilteringCriteria()
	 * @model annotation="http://www.polarsys.org/capella/semantic"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='null' usage\040guideline='null' used\040in\040levels='null' usage\040examples='null' constraints='null'"
	 * @generated
	 */

	EList<FilteringCriterion> getFilteringCriteria();

	/**
	 * Returns the value of the '<em><b>Variability Features</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.filtering.FilteringCriterion}.
	
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variability Features</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variability Features</em>' reference list.
	 * @see org.polarsys.capella.filtering.FilteringPackage#getFilteringCriterionSet_VariabilityFeatures()
	 * @model changeable="false"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation deprecated='true'"
	 * @generated
	 */

	EList<FilteringCriterion> getVariabilityFeatures();

} // FilteringCriterionSet
