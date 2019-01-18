
package org.polarsys.capella.filtering;

import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Filtering Result</b></em>'. <!--
 * end-user-doc -->
 *
 *
 * @see org.polarsys.capella.filtering.FilteringPackage#getAbstractFilteringResult()
 * @model abstract="true"
 * @generated
 */

public interface AbstractFilteringResult extends NamedElement {

  /**
   * 
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @model
   * @generated
   */

  FilteringCriterionSet computeFilteringCriterionSet();

} // AbstractFilteringResult
