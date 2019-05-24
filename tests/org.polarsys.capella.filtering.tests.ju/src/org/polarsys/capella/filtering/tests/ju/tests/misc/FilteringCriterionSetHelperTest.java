package org.polarsys.capella.filtering.tests.ju.tests.misc;

import java.util.Arrays;

import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringFactory;
import org.polarsys.capella.filtering.model.helpers.FilteringCriterionSetHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class FilteringCriterionSetHelperTest extends BasicTestCase {

  FilteringCriterion criteria1, criteria2, criteria3, criteria4, criteria5;

  @Override
  public void test() throws Exception {
    testUnionOf();
    testIntersectionOfOverlapingLists();
    testIntersectionOfDistinctLists();
    testIntersectionOf3OverlapingLists();
    testIntersectionOf2OverlapingAnd1Distinct();
  }

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
    criteria5 = FilteringFactory.eINSTANCE.createFilteringCriterion();
    criteria5.setName("criteria5");
  }

  private void testUnionOf() {
    AssociatedFilteringCriterionSet filteringCriterionSetA = FilteringFactory.eINSTANCE
        .createAssociatedFilteringCriterionSet();

    filteringCriterionSetA.getFilteringCriteria().add(criteria1);
    filteringCriterionSetA.getFilteringCriteria().add(criteria2);

    AssociatedFilteringCriterionSet filteringCriterionSetB = FilteringFactory.eINSTANCE
        .createAssociatedFilteringCriterionSet();

    filteringCriterionSetB.getFilteringCriteria().add(criteria3);
    filteringCriterionSetB.getFilteringCriteria().add(criteria4);

    FilteringCriterionSet unionSet = FilteringCriterionSetHelper.unionOf(filteringCriterionSetA,
        filteringCriterionSetB);

    // Expected
    AssociatedFilteringCriterionSet expectedSet = FilteringFactory.eINSTANCE.createAssociatedFilteringCriterionSet();
    expectedSet.getFilteringCriteria().addAll(Arrays.asList(criteria1, criteria2, criteria3, criteria4));

    assertEquals(expectedSet.getFilteringCriteria(), unionSet.getFilteringCriteria());
  }

  private void testIntersectionOfOverlapingLists() {

    AssociatedFilteringCriterionSet filteringCriterionSetA = FilteringFactory.eINSTANCE
        .createAssociatedFilteringCriterionSet();
    filteringCriterionSetA.getFilteringCriteria().add(criteria1);
    filteringCriterionSetA.getFilteringCriteria().add(criteria2);

    AssociatedFilteringCriterionSet filteringCriterionSetB = FilteringFactory.eINSTANCE
        .createAssociatedFilteringCriterionSet();

    filteringCriterionSetB.getFilteringCriteria().add(criteria2);
    filteringCriterionSetB.getFilteringCriteria().add(criteria3);

    FilteringCriterionSet intersectionSet = FilteringCriterionSetHelper.intersectionOf(filteringCriterionSetA,
        filteringCriterionSetB);

    // Expected
    AssociatedFilteringCriterionSet expectedSet = FilteringFactory.eINSTANCE.createAssociatedFilteringCriterionSet();
    expectedSet.getFilteringCriteria().addAll(Arrays.asList(criteria2));

    assertEquals(expectedSet.getFilteringCriteria(), intersectionSet.getFilteringCriteria());
  }

  private void testIntersectionOfDistinctLists() {

    AssociatedFilteringCriterionSet filteringCriterionSetA = FilteringFactory.eINSTANCE
        .createAssociatedFilteringCriterionSet();
    filteringCriterionSetA.getFilteringCriteria().add(criteria1);
    filteringCriterionSetA.getFilteringCriteria().add(criteria2);

    AssociatedFilteringCriterionSet filteringCriterionSetB = FilteringFactory.eINSTANCE
        .createAssociatedFilteringCriterionSet();

    filteringCriterionSetB.getFilteringCriteria().add(criteria3);
    filteringCriterionSetB.getFilteringCriteria().add(criteria4);

    FilteringCriterionSet intersectionSet = FilteringCriterionSetHelper.intersectionOf(filteringCriterionSetA,
        filteringCriterionSetB);

    // Expected
    AssociatedFilteringCriterionSet emptySet = FilteringFactory.eINSTANCE.createAssociatedFilteringCriterionSet();

    assertEquals(emptySet.getFilteringCriteria(), intersectionSet.getFilteringCriteria());
  }

  private void testIntersectionOf3OverlapingLists() {

    AssociatedFilteringCriterionSet filteringCriterionSetA = FilteringFactory.eINSTANCE
        .createAssociatedFilteringCriterionSet();
    filteringCriterionSetA.getFilteringCriteria().add(criteria1);
    filteringCriterionSetA.getFilteringCriteria().add(criteria2);

    AssociatedFilteringCriterionSet filteringCriterionSetB = FilteringFactory.eINSTANCE
        .createAssociatedFilteringCriterionSet();

    filteringCriterionSetB.getFilteringCriteria().add(criteria2);
    filteringCriterionSetB.getFilteringCriteria().add(criteria3);

    AssociatedFilteringCriterionSet filteringCriterionSetC = FilteringFactory.eINSTANCE
        .createAssociatedFilteringCriterionSet();

    filteringCriterionSetC.getFilteringCriteria().add(criteria2);
    filteringCriterionSetC.getFilteringCriteria().add(criteria5);

    FilteringCriterionSet intersectionSet = FilteringCriterionSetHelper.intersectionOf(filteringCriterionSetA,
        filteringCriterionSetB, filteringCriterionSetC);

    // Expected
    AssociatedFilteringCriterionSet expectedSet = FilteringFactory.eINSTANCE.createAssociatedFilteringCriterionSet();
    expectedSet.getFilteringCriteria().addAll(Arrays.asList(criteria2));

    assertEquals(expectedSet.getFilteringCriteria(), intersectionSet.getFilteringCriteria());
  }

  private void testIntersectionOf2OverlapingAnd1Distinct() {

    AssociatedFilteringCriterionSet filteringCriterionSetA = FilteringFactory.eINSTANCE
        .createAssociatedFilteringCriterionSet();
    filteringCriterionSetA.getFilteringCriteria().add(criteria1);
    filteringCriterionSetA.getFilteringCriteria().add(criteria2);

    AssociatedFilteringCriterionSet filteringCriterionSetB = FilteringFactory.eINSTANCE
        .createAssociatedFilteringCriterionSet();

    filteringCriterionSetB.getFilteringCriteria().add(criteria2);
    filteringCriterionSetB.getFilteringCriteria().add(criteria3);

    AssociatedFilteringCriterionSet filteringCriterionSetC = FilteringFactory.eINSTANCE
        .createAssociatedFilteringCriterionSet();

    filteringCriterionSetC.getFilteringCriteria().add(criteria4);
    filteringCriterionSetC.getFilteringCriteria().add(criteria5);

    FilteringCriterionSet intersectionSet = FilteringCriterionSetHelper.intersectionOf(filteringCriterionSetA,
        filteringCriterionSetB, filteringCriterionSetC);

    // Expected
    AssociatedFilteringCriterionSet emptySet = FilteringFactory.eINSTANCE.createAssociatedFilteringCriterionSet();

    assertEquals(emptySet.getFilteringCriteria(), intersectionSet.getFilteringCriteria());
  }

}
