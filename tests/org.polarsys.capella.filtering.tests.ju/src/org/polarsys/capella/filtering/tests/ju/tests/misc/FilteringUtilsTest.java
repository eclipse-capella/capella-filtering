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

package org.polarsys.capella.filtering.tests.ju.tests.misc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.filtering.ComposedFilteringResult;
import org.polarsys.capella.filtering.ExclusionFilteringResultSet;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringFactory;
import org.polarsys.capella.filtering.FilteringResult;
import org.polarsys.capella.filtering.IntersectionFilteringResultSet;
import org.polarsys.capella.filtering.UnionFilteringResultSet;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class FilteringUtilsTest extends BasicTestCase {

  ComposedFilteringResult CR_1_AND_2, CR_1_OR_2, CR_1_OR_2_NOT_R3;
  FilteringResult R1, R2, R3;
  FilteringCriterion criteria1, criteria2, criteria3, criteria4;
  LogicalComponent RootLC;
  LogicalComponent LC1, LC2, LC3, LC4, LC5, LC6, LC7, LC8;
  Set<LogicalComponent> ALL_LCs;

  @Override
  public void setUp() throws Exception {
    super.setUp();
    criteria1 = FilteringFactory.eINSTANCE.createFilteringCriterion();
    criteria1.setName("criteria1");
    criteria2 = FilteringFactory.eINSTANCE.createFilteringCriterion();
    criteria2.setName("criteria2");
    criteria3 = FilteringFactory.eINSTANCE.createFilteringCriterion();
    criteria3.setName("criteria3");
    criteria4 = FilteringFactory.eINSTANCE.createFilteringCriterion();
    criteria4.setName("criteria4");

    LC1 = createLogicalComponentWithAssociatedCriteria("LC1", criteria1);
    LC2 = createLogicalComponentWithAssociatedCriteria("LC2", criteria1, criteria2);
    LC3 = createLogicalComponentWithAssociatedCriteria("LC3", criteria3);
    LC4 = createLogicalComponentWithAssociatedCriteria("LC4", criteria4);
    LC5 = createLogicalComponentWithAssociatedCriteria("LC5", criteria1);
    LC6 = createLogicalComponentWithAssociatedCriteria("LC6", criteria2);
    LC7 = createLogicalComponentWithAssociatedCriteria("LC7", criteria3);
    LC8 = createLogicalComponentWithAssociatedCriteria("LC8", criteria1, criteria3, criteria4);

    ALL_LCs = new HashSet<LogicalComponent>(Arrays.asList(LC1, LC2, LC3, LC4, LC5, LC6, LC7, LC8));

    RootLC = LaFactory.eINSTANCE.createLogicalComponent("RootLC");

    RootLC.getOwnedLogicalComponents().addAll(ALL_LCs);

    R1 = createResultWithCriteria("R1", Arrays.asList(criteria1, criteria2));
    R2 = createResultWithCriteria("R2", Arrays.asList(criteria2, criteria3));
    R3 = createResultWithCriteria("R3", Arrays.asList(criteria3, criteria4));

    CR_1_AND_2 = createResultAsANDCompositionOf(R1, R2);
    CR_1_OR_2 = createResultAsORCompositionOf(R1, R2);
    CR_1_OR_2_NOT_R3 = createResultAsORCompositionThenNotOf(R1, R2, R3);
  }

  @Override
  public void test() throws Exception {
    R1_GIVES_LC_1_2_5_6_8();
    R2_GIVES_LC_2_3_6_7_8();
    R3_GIVES_LC_3_4_7_8();
    R1_AND_R2_GIVES_LC_2_6_8();
    R1_OR_R2_GIVES_ALL_BUT_LC_4();
    R1_OR_R2_NOT_R3_GIVES_LC1_LC2_LC5_LC6();
  }

  private void R1_GIVES_LC_1_2_5_6_8() {

    Set<EObject> actualDerivation = FilteringUtils.computeDerivation(R1, RootLC);

    // Expected
    Set<EObject> expectedDerivation = new HashSet<>(Arrays.asList(LC1, LC2, LC5, LC6, LC8));
    assertEquals(expectedDerivation, actualDerivation);
  }

  private void R2_GIVES_LC_2_3_6_7_8() {

    Set<EObject> actualDerivation = FilteringUtils.computeDerivation(R2, RootLC);

    // Expected
    Set<EObject> expectedDerivation = new HashSet<>(Arrays.asList(LC2, LC3, LC6, LC7, LC8));
    assertEquals(expectedDerivation, actualDerivation);
  }

  private void R3_GIVES_LC_3_4_7_8() {

    Set<EObject> actualDerivation = FilteringUtils.computeDerivation(R3, RootLC);

    // Expected
    Set<EObject> expectedDerivation = new HashSet<>(Arrays.asList(LC3, LC4, LC7, LC8));
    assertEquals(expectedDerivation, actualDerivation);
  }

  private void R1_AND_R2_GIVES_LC_2_6_8() {

    Set<EObject> actualDerivation = FilteringUtils.computeDerivation(CR_1_AND_2, RootLC);

    // Expected
    Set<EObject> expectedDerivation = new HashSet<>(Arrays.asList(LC2, LC6, LC8));
    assertEquals(expectedDerivation, actualDerivation);
  }

  private void R1_OR_R2_GIVES_ALL_BUT_LC_4() {

    Set<EObject> actualDerivation = FilteringUtils.computeDerivation(CR_1_OR_2, RootLC);

    // Expected
    Set<EObject> expectedDerivation = new HashSet<>(ALL_LCs);
    expectedDerivation.remove(LC4);

    assertEquals(expectedDerivation, actualDerivation);
  }

  private void R1_OR_R2_NOT_R3_GIVES_LC1_LC2_LC5_LC6() {

    Set<EObject> actualDerivation = FilteringUtils.computeDerivation(CR_1_OR_2_NOT_R3, RootLC);

    // Expected
    Set<EObject> expectedDerivation = new HashSet<>(Arrays.asList(LC1, LC2, LC5, LC6));

    assertEquals(expectedDerivation, actualDerivation);
  }

  private static LogicalComponent createLogicalComponentWithAssociatedCriteria(String componentName,
      FilteringCriterion... criterion) {

    LogicalComponent element = LaFactory.eINSTANCE.createLogicalComponent(componentName);
    FilteringUtils.addAssociatedCriteria(element, Arrays.asList(criterion));
    return element;

  }

  private static FilteringResult createResultWithCriteria(String name, List<FilteringCriterion> criterionList) {
    FilteringResult result = FilteringFactory.eINSTANCE.createFilteringResult();
    result.setName(name);
    result.getFilteringCriteria().addAll(criterionList);
    return result;
  }

  private ComposedFilteringResult createResultAsORCompositionOf(FilteringResult orResult2, FilteringResult orResult1) {
    ComposedFilteringResult composedResult = FilteringFactory.eINSTANCE.createComposedFilteringResult();
    UnionFilteringResultSet unionSet = FilteringFactory.eINSTANCE.createUnionFilteringResultSet();
    unionSet.getFilteringResults().addAll(Arrays.asList(orResult1, orResult2));
    composedResult.setUnionFilteringResultSet(unionSet);
    return composedResult;
  }

  private ComposedFilteringResult createResultAsANDCompositionOf(FilteringResult orResult2, FilteringResult orResult1) {

    ComposedFilteringResult composedResult = FilteringFactory.eINSTANCE.createComposedFilteringResult();
    IntersectionFilteringResultSet intersectionSet = FilteringFactory.eINSTANCE.createIntersectionFilteringResultSet();
    intersectionSet.getFilteringResults().addAll(Arrays.asList(orResult1, orResult2));
    composedResult.setIntersectionFilteringResultSet(intersectionSet);
    return composedResult;
  }

  private ComposedFilteringResult createResultAsORCompositionThenNotOf(FilteringResult orResult1,
      FilteringResult orResult2, FilteringResult notResult) {

    ComposedFilteringResult composedResult = FilteringFactory.eINSTANCE.createComposedFilteringResult();

    UnionFilteringResultSet unionSet = FilteringFactory.eINSTANCE.createUnionFilteringResultSet();
    unionSet.getFilteringResults().addAll(Arrays.asList(orResult1, orResult2));
    composedResult.setUnionFilteringResultSet(unionSet);

    ExclusionFilteringResultSet exclusionSet = FilteringFactory.eINSTANCE.createExclusionFilteringResultSet();
    exclusionSet.getFilteringResults().add(notResult);
    composedResult.setExclusionFilteringResultSet(exclusionSet);

    return composedResult;

  }

}
