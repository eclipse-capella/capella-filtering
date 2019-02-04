
package org.polarsys.capella.filtering;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Composed Filtering Result</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.filtering.ComposedFilteringResult#getUnionFilteringResultSet <em>Union Filtering Result Set</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.ComposedFilteringResult#getIntersectionFilteringResultSet <em>Intersection Filtering Result Set</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.ComposedFilteringResult#getExclusionFilteringResultSet <em>Exclusion Filtering Result Set</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.filtering.FilteringPackage#getComposedFilteringResult()
 * @model
 * @generated
 */

public interface ComposedFilteringResult extends AbstractFilteringResult {

	/**
	 * Returns the value of the '<em><b>Union Filtering Result Set</b></em>' containment reference.
	
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Union Filtering Result Set</em>' containment reference isn't clear, there really should
	 * be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Union Filtering Result Set</em>' containment reference.
	 * @see #setUnionFilteringResultSet(UnionFilteringResultSet)
	 * @see org.polarsys.capella.filtering.FilteringPackage#getComposedFilteringResult_UnionFilteringResultSet()
	 * @model containment="true"
	 * @generated
	 */

	UnionFilteringResultSet getUnionFilteringResultSet();

	/**
	 * Sets the value of the '{@link org.polarsys.capella.filtering.ComposedFilteringResult#getUnionFilteringResultSet <em>Union Filtering Result Set</em>}' containment reference.
	
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Union Filtering Result Set</em>' containment reference.
	 * @see #getUnionFilteringResultSet()
	 * @generated
	 */

	void setUnionFilteringResultSet(UnionFilteringResultSet value);

	/**
	 * Returns the value of the '<em><b>Intersection Filtering Result Set</b></em>' containment reference.
	
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Intersection Filtering Result Set</em>' containment reference isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Intersection Filtering Result Set</em>' containment reference.
	 * @see #setIntersectionFilteringResultSet(IntersectionFilteringResultSet)
	 * @see org.polarsys.capella.filtering.FilteringPackage#getComposedFilteringResult_IntersectionFilteringResultSet()
	 * @model containment="true"
	 * @generated
	 */

	IntersectionFilteringResultSet getIntersectionFilteringResultSet();

	/**
	 * Sets the value of the '{@link org.polarsys.capella.filtering.ComposedFilteringResult#getIntersectionFilteringResultSet <em>Intersection Filtering Result Set</em>}' containment reference.
	
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Intersection Filtering Result Set</em>' containment reference.
	 * @see #getIntersectionFilteringResultSet()
	 * @generated
	 */

	void setIntersectionFilteringResultSet(IntersectionFilteringResultSet value);

	/**
	 * Returns the value of the '<em><b>Exclusion Filtering Result Set</b></em>' containment reference.
	
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exclusion Filtering Result Set</em>' containment reference isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exclusion Filtering Result Set</em>' containment reference.
	 * @see #setExclusionFilteringResultSet(ExclusionFilteringResultSet)
	 * @see org.polarsys.capella.filtering.FilteringPackage#getComposedFilteringResult_ExclusionFilteringResultSet()
	 * @model containment="true"
	 * @generated
	 */

	ExclusionFilteringResultSet getExclusionFilteringResultSet();

	/**
	 * Sets the value of the '{@link org.polarsys.capella.filtering.ComposedFilteringResult#getExclusionFilteringResultSet <em>Exclusion Filtering Result Set</em>}' containment reference.
	
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exclusion Filtering Result Set</em>' containment reference.
	 * @see #getExclusionFilteringResultSet()
	 * @generated
	 */

	void setExclusionFilteringResultSet(ExclusionFilteringResultSet value);

} // ComposedFilteringResult
