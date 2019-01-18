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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 * 
 * @see org.polarsys.capella.filtering.FilteringPackage
 * @generated
 */
public interface FilteringFactory extends EFactory {
  /**
   * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  FilteringFactory eINSTANCE = org.polarsys.capella.filtering.impl.FilteringFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Model</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Model</em>'.
   * @generated
   */
  FilteringModel createFilteringModel();

  /**
   * Returns a new object of class '<em>Criterion</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Criterion</em>'.
   * @generated
   */
  FilteringCriterion createFilteringCriterion();

  /**
   * Returns a new object of class '<em>Results</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Results</em>'.
   * @generated
   */
  FilteringResults createFilteringResults();

  /**
   * Returns a new object of class '<em>Result</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Result</em>'.
   * @generated
   */
  FilteringResult createFilteringResult();

  /**
   * Returns a new object of class '<em>Associated Filtering Criterion Set</em>'. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return a new object of class '<em>Associated Filtering Criterion Set</em>'.
   * @generated
   */
  AssociatedFilteringCriterionSet createAssociatedFilteringCriterionSet();

  /**
   * Returns a new object of class '<em>Creation Default Filtering Criterion Set</em>'. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return a new object of class '<em>Creation Default Filtering Criterion Set</em>'.
   * @generated
   */
  CreationDefaultFilteringCriterionSet createCreationDefaultFilteringCriterionSet();

  /**
   * Returns a new object of class '<em>Result Pkg</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Result Pkg</em>'.
   * @generated
   */
  FilteringResultPkg createFilteringResultPkg();

  /**
   * Returns a new object of class '<em>Criterion Pkg</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Criterion Pkg</em>'.
   * @generated
   */
  FilteringCriterionPkg createFilteringCriterionPkg();

  /**
   * Returns a new object of class '<em>Composed Filtering Result</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Composed Filtering Result</em>'.
   * @generated
   */
  ComposedFilteringResult createComposedFilteringResult();

  /**
   * Returns a new object of class '<em>Result Set</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Result Set</em>'.
   * @generated
   */
  FilteringResultSet createFilteringResultSet();

  /**
   * Returns a new object of class '<em>Union Filtering Result Set</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Union Filtering Result Set</em>'.
   * @generated
   */
  UnionFilteringResultSet createUnionFilteringResultSet();

  /**
   * Returns a new object of class '<em>Exclusion Filtering Result Set</em>'. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @return a new object of class '<em>Exclusion Filtering Result Set</em>'.
   * @generated
   */
  ExclusionFilteringResultSet createExclusionFilteringResultSet();

  /**
   * Returns a new object of class '<em>Intersection Filtering Result Set</em>'. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return a new object of class '<em>Intersection Filtering Result Set</em>'.
   * @generated
   */
  IntersectionFilteringResultSet createIntersectionFilteringResultSet();

  /**
   * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the package supported by this factory.
   * @generated
   */
  FilteringPackage getFilteringPackage();

} // FilteringFactory
