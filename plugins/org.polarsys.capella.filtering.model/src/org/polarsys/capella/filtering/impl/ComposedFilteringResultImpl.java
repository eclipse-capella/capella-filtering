
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
 * <li>{@link org.polarsys.capella.filtering.impl.ComposedFilteringResultImpl#getUnionFilteringResultSet <em>Union
 * Filtering Result Set</em>}</li>
 * <li>{@link org.polarsys.capella.filtering.impl.ComposedFilteringResultImpl#getIntersectionFilteringResultSet
 * <em>Intersection Filtering Result Set</em>}</li>
 * <li>{@link org.polarsys.capella.filtering.impl.ComposedFilteringResultImpl#getExclusionFilteringResultSet
 * <em>Exclusion Filtering Result Set</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ComposedFilteringResultImpl extends AbstractFilteringResultImpl implements ComposedFilteringResult {

  /**
   * The cached value of the '{@link #getUnionFilteringResultSet() <em>Union Filtering Result Set</em>}' containment
   * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getUnionFilteringResultSet()
   * @generated
   * @ordered
   */
  protected UnionFilteringResultSet unionFilteringResultSet;

  /**
   * The cached value of the '{@link #getIntersectionFilteringResultSet() <em>Intersection Filtering Result Set</em>}'
   * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getIntersectionFilteringResultSet()
   * @generated
   * @ordered
   */
  protected IntersectionFilteringResultSet intersectionFilteringResultSet;

  /**
   * The cached value of the '{@link #getExclusionFilteringResultSet() <em>Exclusion Filtering Result Set</em>}'
   * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getExclusionFilteringResultSet()
   * @generated
   * @ordered
   */
  protected ExclusionFilteringResultSet exclusionFilteringResultSet;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected ComposedFilteringResultImpl() {

    super();

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
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
          .intersectionOf(intersectionFResultStm.collect(Collectors.toSet()));
    }
    if (exclusionFilteringResultSet != null) {
      // compute exclusion criterion set
      List<FilteringCriterionSet> exclusionCriterionSets = exclusionFilteringResultSet.getFilteringResults().stream()
          .map(afResult -> afResult.computeFilteringCriterionSet()).collect(Collectors.toList());
      excludingCriterionSet = FilteringCriterionSetHelper.unionOf(exclusionCriterionSets);

    }
    // compute union of (intersectionFilteringResultSet and
    // unionFilteringResultSet)

    EList<FilteringCriterion> unionAndIntersection = FilteringCriterionSetHelper
        .unionOf(unionFResult, intersectionFResult).getFilteringCriteria();

    if (unionAndIntersection != null) {
      computedCriterionSet = FilteringFactory.eINSTANCE.createAssociatedFilteringCriterionSet();
      computedCriterionSet.getFilteringCriteria().addAll(unionAndIntersection);

      if (excludingCriterionSet != null) {

        computedCriterionSet.getFilteringCriteria().removeAll(excludingCriterionSet.getFilteringCriteria());
      }

    }
    return computedCriterionSet;

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public UnionFilteringResultSet getUnionFilteringResultSet() {

    return unionFilteringResultSet;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public NotificationChain basicSetUnionFilteringResultSet(UnionFilteringResultSet newUnionFilteringResultSet,
      NotificationChain msgs) {

    UnionFilteringResultSet oldUnionFilteringResultSet = unionFilteringResultSet;
    unionFilteringResultSet = newUnionFilteringResultSet;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
          FilteringPackage.COMPOSED_FILTERING_RESULT__UNION_FILTERING_RESULT_SET, oldUnionFilteringResultSet,
          newUnionFilteringResultSet);
      if (msgs == null)
        msgs = notification;
      else
        msgs.add(notification);
    }

    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public void setUnionFilteringResultSet(UnionFilteringResultSet newUnionFilteringResultSet) {

    if (newUnionFilteringResultSet != unionFilteringResultSet) {
      NotificationChain msgs = null;
      if (unionFilteringResultSet != null)
        msgs = ((InternalEObject) unionFilteringResultSet).eInverseRemove(this,
            EOPPOSITE_FEATURE_BASE - FilteringPackage.COMPOSED_FILTERING_RESULT__UNION_FILTERING_RESULT_SET, null,
            msgs);
      if (newUnionFilteringResultSet != null)
        msgs = ((InternalEObject) newUnionFilteringResultSet).eInverseAdd(this,
            EOPPOSITE_FEATURE_BASE - FilteringPackage.COMPOSED_FILTERING_RESULT__UNION_FILTERING_RESULT_SET, null,
            msgs);
      msgs = basicSetUnionFilteringResultSet(newUnionFilteringResultSet, msgs);
      if (msgs != null)
        msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          FilteringPackage.COMPOSED_FILTERING_RESULT__UNION_FILTERING_RESULT_SET, newUnionFilteringResultSet,
          newUnionFilteringResultSet));

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public IntersectionFilteringResultSet getIntersectionFilteringResultSet() {

    return intersectionFilteringResultSet;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public NotificationChain basicSetIntersectionFilteringResultSet(
      IntersectionFilteringResultSet newIntersectionFilteringResultSet, NotificationChain msgs) {

    IntersectionFilteringResultSet oldIntersectionFilteringResultSet = intersectionFilteringResultSet;
    intersectionFilteringResultSet = newIntersectionFilteringResultSet;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
          FilteringPackage.COMPOSED_FILTERING_RESULT__INTERSECTION_FILTERING_RESULT_SET,
          oldIntersectionFilteringResultSet, newIntersectionFilteringResultSet);
      if (msgs == null)
        msgs = notification;
      else
        msgs.add(notification);
    }

    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public void setIntersectionFilteringResultSet(IntersectionFilteringResultSet newIntersectionFilteringResultSet) {

    if (newIntersectionFilteringResultSet != intersectionFilteringResultSet) {
      NotificationChain msgs = null;
      if (intersectionFilteringResultSet != null)
        msgs = ((InternalEObject) intersectionFilteringResultSet).eInverseRemove(this,
            EOPPOSITE_FEATURE_BASE - FilteringPackage.COMPOSED_FILTERING_RESULT__INTERSECTION_FILTERING_RESULT_SET,
            null, msgs);
      if (newIntersectionFilteringResultSet != null)
        msgs = ((InternalEObject) newIntersectionFilteringResultSet).eInverseAdd(this,
            EOPPOSITE_FEATURE_BASE - FilteringPackage.COMPOSED_FILTERING_RESULT__INTERSECTION_FILTERING_RESULT_SET,
            null, msgs);
      msgs = basicSetIntersectionFilteringResultSet(newIntersectionFilteringResultSet, msgs);
      if (msgs != null)
        msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          FilteringPackage.COMPOSED_FILTERING_RESULT__INTERSECTION_FILTERING_RESULT_SET,
          newIntersectionFilteringResultSet, newIntersectionFilteringResultSet));

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public ExclusionFilteringResultSet getExclusionFilteringResultSet() {

    return exclusionFilteringResultSet;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public NotificationChain basicSetExclusionFilteringResultSet(
      ExclusionFilteringResultSet newExclusionFilteringResultSet, NotificationChain msgs) {

    ExclusionFilteringResultSet oldExclusionFilteringResultSet = exclusionFilteringResultSet;
    exclusionFilteringResultSet = newExclusionFilteringResultSet;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
          FilteringPackage.COMPOSED_FILTERING_RESULT__EXCLUSION_FILTERING_RESULT_SET, oldExclusionFilteringResultSet,
          newExclusionFilteringResultSet);
      if (msgs == null)
        msgs = notification;
      else
        msgs.add(notification);
    }

    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public void setExclusionFilteringResultSet(ExclusionFilteringResultSet newExclusionFilteringResultSet) {

    if (newExclusionFilteringResultSet != exclusionFilteringResultSet) {
      NotificationChain msgs = null;
      if (exclusionFilteringResultSet != null)
        msgs = ((InternalEObject) exclusionFilteringResultSet).eInverseRemove(this,
            EOPPOSITE_FEATURE_BASE - FilteringPackage.COMPOSED_FILTERING_RESULT__EXCLUSION_FILTERING_RESULT_SET, null,
            msgs);
      if (newExclusionFilteringResultSet != null)
        msgs = ((InternalEObject) newExclusionFilteringResultSet).eInverseAdd(this,
            EOPPOSITE_FEATURE_BASE - FilteringPackage.COMPOSED_FILTERING_RESULT__EXCLUSION_FILTERING_RESULT_SET, null,
            msgs);
      msgs = basicSetExclusionFilteringResultSet(newExclusionFilteringResultSet, msgs);
      if (msgs != null)
        msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          FilteringPackage.COMPOSED_FILTERING_RESULT__EXCLUSION_FILTERING_RESULT_SET, newExclusionFilteringResultSet,
          newExclusionFilteringResultSet));

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
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
   * 
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
   * 
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
   * 
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
   * 
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
    case FilteringPackage.COMPOSED_FILTERING_RESULT__UNION_FILTERING_RESULT_SET:
      return unionFilteringResultSet != null;
    case FilteringPackage.COMPOSED_FILTERING_RESULT__INTERSECTION_FILTERING_RESULT_SET:
      return intersectionFilteringResultSet != null;
    case FilteringPackage.COMPOSED_FILTERING_RESULT__EXCLUSION_FILTERING_RESULT_SET:
      return exclusionFilteringResultSet != null;
    }
    return super.eIsSet(featureID);
  }

} // ComposedFilteringResultImpl