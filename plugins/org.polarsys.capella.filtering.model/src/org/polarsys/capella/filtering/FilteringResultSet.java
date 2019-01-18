
package org.polarsys.capella.filtering;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Result Set</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.polarsys.capella.filtering.FilteringResultSet#getFilteringResults <em>Filtering Results</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.filtering.FilteringPackage#getFilteringResultSet()
 * @model
 * @generated
 */

public interface FilteringResultSet extends EObject {

  /**
   * Returns the value of the '<em><b>Filtering Results</b></em>' reference list. The list contents are of type
   * {@link org.polarsys.capella.filtering.AbstractFilteringResult}.
   * 
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Filtering Results</em>' reference list isn't clear, there really should be more of a
   * description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Filtering Results</em>' reference list.
   * @see org.polarsys.capella.filtering.FilteringPackage#getFilteringResultSet_FilteringResults()
   * @model
   * @generated
   */

  EList<AbstractFilteringResult> getFilteringResults();

} // FilteringResultSet
