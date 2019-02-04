package org.polarsys.capella.filtering.tests.ju.tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringFactory;
import org.polarsys.capella.filtering.model.helpers.FilteringCriterionSetHelper;

public class FilteringCriterionSetHelperTest {

  FilteringCriterion criteria1, criteria2, criteria3, criteria4, criteria5;

  @Before
  public void setup() {
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

  @Test
  public void testUnionOf() {
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

  @Test
  public void testIntersectionOfOverlapingLists() {

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

  @Test
  public void testIntersectionOfDistinctLists() {

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

  @Test
  public void testIntersectionOf3OverlapingLists() {

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

  @Test
  public void testIntersectionOf2OverlapingAnd1Distinct() {

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
