
package org.polarsys.capella.filtering.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;
import org.polarsys.capella.filtering.ComposedFilteringResult;
import org.polarsys.capella.filtering.ExclusionFilteringResultSet;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringFactory;
import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.capella.filtering.IntersectionFilteringResultSet;
import org.polarsys.capella.filtering.UnionFilteringResultSet;
import org.polarsys.capella.filtering.model.helpers.FilteringCriterionSetHelper;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Composed Filtering Result</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.filtering.impl.ComposedFilteringResultImpl#getUnionFilteringResultSet <em>Union Filtering Result Set</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.impl.ComposedFilteringResultImpl#getIntersectionFilteringResultSet <em>Intersection Filtering Result Set</em>}</li>
 *   <li>{@link org.polarsys.capella.filtering.impl.ComposedFilteringResultImpl#getExclusionFilteringResultSet <em>Exclusion Filtering Result Set</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ComposedFilteringResultImpl extends AbstractFilteringResultImpl implements ComposedFilteringResult {

  /**
   * The cached value of the '{@link #getUnionFilteringResultSet() <em>Union Filtering Result Set</em>}' containment reference.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getUnionFilteringResultSet()
   * @generated
   * @ordered
   */
  protected UnionFilteringResultSet unionFilteringResultSet;

  /**
   * The cached value of the '{@link #getIntersectionFilteringResultSet() <em>Intersection Filtering Result Set</em>}' containment reference.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getIntersectionFilteringResultSet()
   * @generated
   * @ordered
   */
  protected IntersectionFilteringResultSet intersectionFilteringResultSet;

  /**
   * The cached value of the '{@link #getExclusionFilteringResultSet() <em>Exclusion Filtering Result Set</em>}' containment reference.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getExclusionFilteringResultSet()
   * @generated
   * @ordered
   */
  protected ExclusionFilteringResultSet exclusionFilteringResultSet;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedFilteringResultImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FilteringPackage.Literals.COMPOSED_FILTERING_RESULT;
	}

	/**
	 * <!-- begin-user-doc --> Computing a {@link FilteringCriterionSet} for a {@link ComposedFilteringResultImpl} is
	 * computing: UnionOf().Excluding()
	 * 
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public FilteringCriterionSet computeFilteringCriterionSet() {

		FilteringCriterionSet unionFResult = null;
		FilteringCriterionSet intersectionFResult = null;
		FilteringCriterionSet excludingCriterionSet = null;
		FilteringCriterionSet computedCriterionSet = null;

		// Compute union of unionFilteringResultSet set of filtering results
		if (unionFilteringResultSet != null) {
			Stream<FilteringCriterionSet> unionFResultStm = unionFilteringResultSet.getFilteringResults().stream()
					.map(afResult -> afResult.computeFilteringCriterionSet());

			unionFResult = FilteringCriterionSetHelper.unionOf(unionFResultStm.collect(Collectors.toList()));
		}

		// Compute intersection of intersectionFilteringResultSet set of
		// filtering results
		if (intersectionFilteringResultSet != null) {
			Stream<FilteringCriterionSet> intersectionFResultStm = intersectionFilteringResultSet.getFilteringResults()
					.stream().map(afResult -> afResult.computeFilteringCriterionSet());

			intersectionFResult = FilteringCriterionSetHelper
					.unionOf(intersectionFResultStm.collect(Collectors.toSet()));
		}
		if (exclusionFilteringResultSet != null) {
			// compute exclusion criterion set
			List<FilteringCriterionSet> exclusionCriterionSets = exclusionFilteringResultSet.getFilteringResults()
					.stream().map(afResult -> afResult.computeFilteringCriterionSet()).collect(Collectors.toList());
			excludingCriterionSet = FilteringCriterionSetHelper.unionOf(exclusionCriterionSets);

		}
		// compute union of (intersectionFilteringResultSet and
		// unionFilteringResultSet)

		EList<FilteringCriterion> unionOfAll = FilteringCriterionSetHelper
				.unionOf(unionFResult, intersectionFResult, excludingCriterionSet).getFilteringCriteria();

		if (unionOfAll != null) {
			computedCriterionSet = FilteringFactory.eINSTANCE.createAssociatedFilteringCriterionSet();
			computedCriterionSet.getFilteringCriteria().addAll(unionOfAll);

		}

		return computedCriterionSet;

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public UnionFilteringResultSet getUnionFilteringResultSet() {

		return (UnionFilteringResultSet) eDynamicGet(
				FilteringPackage.COMPOSED_FILTERING_RESULT__UNION_FILTERING_RESULT_SET,
				FilteringPackage.Literals.COMPOSED_FILTERING_RESULT__UNION_FILTERING_RESULT_SET, true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetUnionFilteringResultSet(UnionFilteringResultSet newUnionFilteringResultSet,
			NotificationChain msgs) {

		msgs = eDynamicInverseAdd((InternalEObject) newUnionFilteringResultSet,
				FilteringPackage.COMPOSED_FILTERING_RESULT__UNION_FILTERING_RESULT_SET, msgs);

		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public void setUnionFilteringResultSet(UnionFilteringResultSet newUnionFilteringResultSet) {

		eDynamicSet(FilteringPackage.COMPOSED_FILTERING_RESULT__UNION_FILTERING_RESULT_SET,
				FilteringPackage.Literals.COMPOSED_FILTERING_RESULT__UNION_FILTERING_RESULT_SET,
				newUnionFilteringResultSet);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public IntersectionFilteringResultSet getIntersectionFilteringResultSet() {

		return (IntersectionFilteringResultSet) eDynamicGet(
				FilteringPackage.COMPOSED_FILTERING_RESULT__INTERSECTION_FILTERING_RESULT_SET,
				FilteringPackage.Literals.COMPOSED_FILTERING_RESULT__INTERSECTION_FILTERING_RESULT_SET, true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetIntersectionFilteringResultSet(
			IntersectionFilteringResultSet newIntersectionFilteringResultSet, NotificationChain msgs) {

		msgs = eDynamicInverseAdd((InternalEObject) newIntersectionFilteringResultSet,
				FilteringPackage.COMPOSED_FILTERING_RESULT__INTERSECTION_FILTERING_RESULT_SET, msgs);

		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public void setIntersectionFilteringResultSet(IntersectionFilteringResultSet newIntersectionFilteringResultSet) {

		eDynamicSet(FilteringPackage.COMPOSED_FILTERING_RESULT__INTERSECTION_FILTERING_RESULT_SET,
				FilteringPackage.Literals.COMPOSED_FILTERING_RESULT__INTERSECTION_FILTERING_RESULT_SET,
				newIntersectionFilteringResultSet);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public ExclusionFilteringResultSet getExclusionFilteringResultSet() {

		return (ExclusionFilteringResultSet) eDynamicGet(
				FilteringPackage.COMPOSED_FILTERING_RESULT__EXCLUSION_FILTERING_RESULT_SET,
				FilteringPackage.Literals.COMPOSED_FILTERING_RESULT__EXCLUSION_FILTERING_RESULT_SET, true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetExclusionFilteringResultSet(
			ExclusionFilteringResultSet newExclusionFilteringResultSet, NotificationChain msgs) {

		msgs = eDynamicInverseAdd((InternalEObject) newExclusionFilteringResultSet,
				FilteringPackage.COMPOSED_FILTERING_RESULT__EXCLUSION_FILTERING_RESULT_SET, msgs);

		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public void setExclusionFilteringResultSet(ExclusionFilteringResultSet newExclusionFilteringResultSet) {

		eDynamicSet(FilteringPackage.COMPOSED_FILTERING_RESULT__EXCLUSION_FILTERING_RESULT_SET,
				FilteringPackage.Literals.COMPOSED_FILTERING_RESULT__EXCLUSION_FILTERING_RESULT_SET,
				newExclusionFilteringResultSet);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case FilteringPackage.COMPOSED_FILTERING_RESULT__UNION_FILTERING_RESULT_SET:
			return basicSetUnionFilteringResultSet(null, msgs);
		case FilteringPackage.COMPOSED_FILTERING_RESULT__INTERSECTION_FILTERING_RESULT_SET:
			return basicSetIntersectionFilteringResultSet(null, msgs);
		case FilteringPackage.COMPOSED_FILTERING_RESULT__EXCLUSION_FILTERING_RESULT_SET:
			return basicSetExclusionFilteringResultSet(null, msgs);
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
		case FilteringPackage.COMPOSED_FILTERING_RESULT__UNION_FILTERING_RESULT_SET:
			return getUnionFilteringResultSet();
		case FilteringPackage.COMPOSED_FILTERING_RESULT__INTERSECTION_FILTERING_RESULT_SET:
			return getIntersectionFilteringResultSet();
		case FilteringPackage.COMPOSED_FILTERING_RESULT__EXCLUSION_FILTERING_RESULT_SET:
			return getExclusionFilteringResultSet();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case FilteringPackage.COMPOSED_FILTERING_RESULT__UNION_FILTERING_RESULT_SET:
			setUnionFilteringResultSet((UnionFilteringResultSet) newValue);
			return;
		case FilteringPackage.COMPOSED_FILTERING_RESULT__INTERSECTION_FILTERING_RESULT_SET:
			setIntersectionFilteringResultSet((IntersectionFilteringResultSet) newValue);
			return;
		case FilteringPackage.COMPOSED_FILTERING_RESULT__EXCLUSION_FILTERING_RESULT_SET:
			setExclusionFilteringResultSet((ExclusionFilteringResultSet) newValue);
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
		case FilteringPackage.COMPOSED_FILTERING_RESULT__UNION_FILTERING_RESULT_SET:
			setUnionFilteringResultSet((UnionFilteringResultSet) null);
			return;
		case FilteringPackage.COMPOSED_FILTERING_RESULT__INTERSECTION_FILTERING_RESULT_SET:
			setIntersectionFilteringResultSet((IntersectionFilteringResultSet) null);
			return;
		case FilteringPackage.COMPOSED_FILTERING_RESULT__EXCLUSION_FILTERING_RESULT_SET:
			setExclusionFilteringResultSet((ExclusionFilteringResultSet) null);
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
		case FilteringPackage.COMPOSED_FILTERING_RESULT__UNION_FILTERING_RESULT_SET:
			return getUnionFilteringResultSet() != null;
		case FilteringPackage.COMPOSED_FILTERING_RESULT__INTERSECTION_FILTERING_RESULT_SET:
			return getIntersectionFilteringResultSet() != null;
		case FilteringPackage.COMPOSED_FILTERING_RESULT__EXCLUSION_FILTERING_RESULT_SET:
			return getExclusionFilteringResultSet() != null;
		}
		return super.eIsSet(featureID);
	}

} // ComposedFilteringResultImpl