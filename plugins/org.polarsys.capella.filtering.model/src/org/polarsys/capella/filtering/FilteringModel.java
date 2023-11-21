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

import org.polarsys.kitalpha.emde.model.ElementExtension;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Model</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.filtering.FilteringModel#getOwnedFilteringCriteria <em>Owned Filtering Criteria</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.FilteringModel#getOwnedFilteringCriterionPkgs <em>Owned Filtering Criterion Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.FilteringModel#getOwnedVariabilityFeatures <em>Owned Variability Features</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.filtering.FilteringPackage#getFilteringModel()
 * @model annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/constraint ExtendedElement='http://www.polarsys.org/capella/core/modeller/7.0.0#//SystemEngineering'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='null' usage\040guideline='null' used\040in\040levels='null' usage\040examples='null' constraints='null'"
 * @generated
 */

public interface FilteringModel extends NamedElement, ElementExtension {

	/**
	 * Returns the value of the '<em><b>Owned Filtering Criteria</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.filtering.FilteringCriterion}.
	
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Filtering Criteria</em>' containment reference list isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Filtering Criteria</em>' containment reference list.
	 * @see org.polarsys.capella.filtering.FilteringPackage#getFilteringModel_OwnedFilteringCriteria()
	 * @model containment="true"
	 *        annotation="http://www.polarsys.org/capella/semantic"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='null' usage\040guideline='null' used\040in\040levels='null' usage\040examples='null' constraints='null'"
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
	 * @see org.polarsys.capella.filtering.FilteringPackage#getFilteringModel_OwnedFilteringCriterionPkgs()
	 * @model containment="true"
	 * @generated
	 */

	EList<FilteringCriterionPkg> getOwnedFilteringCriterionPkgs();

	/**
	 * Returns the value of the '<em><b>Owned Variability Features</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.filtering.FilteringCriterion}.
	
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Variability Features</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Variability Features</em>' containment reference list.
	 * @see org.polarsys.capella.filtering.FilteringPackage#getFilteringModel_OwnedVariabilityFeatures()
	 * @model containment="true" changeable="false"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation deprecated='true'"
	 * @generated
	 */

	EList<FilteringCriterion> getOwnedVariabilityFeatures();

} // FilteringModel
