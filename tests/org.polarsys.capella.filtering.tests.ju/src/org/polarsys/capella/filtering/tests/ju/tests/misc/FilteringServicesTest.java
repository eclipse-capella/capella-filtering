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

import static org.polarsys.capella.filtering.tests.ju.tests.helpers.FilteringTestHelper.areEqualIgnoreOrder;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.helpers.query.IGetElementsQueries;
import org.polarsys.capella.filtering.ComposedFilteringResult;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringResult;
import org.polarsys.capella.filtering.sirius.analysis.FilteringServices;
import org.polarsys.capella.filtering.tests.ju.tests.helpers.FilteringTestHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class FilteringServicesTest extends BasicTestCase {

  private static final String TEST_MODEL_NAME = "FilteringServicesTestModel";

  ComposedFilteringResult CR1, CR2, CR3;
  FilteringResult R1, R2, R3;
  FilteringCriterion C1, C2, C3, C4;

  String R1_ID = "7078ce5b-6723-4665-b850-f1152c25b3bf";
  String R2_ID = "76c02a79-954d-4cda-9f5e-a9a7d596a80e";
  String R3_ID = "a1e2cafb-b078-4cd2-ad84-6907b71e61d2";

  String CR1_ID = "f40c8fe4-4beb-47dd-ad5d-db7feb5be73c";
  String CR2_ID = "7a5a9b26-02e8-43b0-b102-999e5dcbe979";
  String CR3_ID = "756dc973-bda9-4b08-b680-cfc5bb2900c8";

  String C1_ID = "0d1837f3-b2d5-44a0-8d05-d42ea5d32985";
  String C2_ID = "25183b5b-cb29-4390-9afe-cb4c5d817bb8";
  String C3_ID = "a1bf6566-679f-4509-b1c8-5b69dd81c362";
  String C4_ID = "f1a55c38-86fb-44f1-aa02-9d2969e0ade4";

  PhysicalComponent RootPC;
  PhysicalComponent PC1, PC2, PC3, PC4, PC5, PC6, PC7, PC8;
  Part PP1, PP2, PP3, PP4, PP5, PP6, PP7, PP8;

  // Physical components under test
  String PC1_ID = "f11409e9-68c4-4582-92dd-24511197423d";
  String PC2_ID = "69f433ec-a55a-41c1-8750-6a6356b5c7e8";
  String PC3_ID = "670ea8fb-d2bd-4bef-9f65-be9008a72b64";
  String PC4_ID = "0a16683f-6c78-4855-b1a1-5004b9a9ee5a";
  String PC5_ID = "6189b624-e696-4814-a71e-2583ae5be17c";
  String PC6_ID = "c5301c76-2d9d-4f9c-8977-10e27c5f2990";
  String PC7_ID = "70bea46f-8bdb-464f-8195-1df33416ac46";
  String PC8_ID = "a0404933-ad52-4597-b091-2caffd7f55f4";

  // corresponding parts
  String PP1_ID = "4b7e5b1f-b752-48f6-8c9b-1d9b6ae4698a";
  String PP2_ID = "6e491bcd-61fb-42e6-a15c-2fee8b37cd40";
  String PP3_ID = "c513b32d-b54c-4152-a95a-659ab549c32b";
  String PP4_ID = "f998d42d-913c-4a04-bfce-e11e2729be05";
  String PP5_ID = "026c106f-4443-4cce-8224-cfa66123e3e5";
  String PP6_ID = "60335861-2b9e-47fe-8e68-ef8abc02fd8e";
  String PP7_ID = "9a237b56-74a5-4194-aa41-98f1a4efd5aa";
  String PP8_ID = "f1ff18f0-3b9f-4378-9b93-ac57666b3d1b";

  Set<LogicalComponent> ALL_PCs;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    Session session = getSessionForTestModel(TEST_MODEL_NAME);
    session.open(new NullProgressMonitor());

    Project capellaProject = FilteringTestHelper.getCapellaProject(session.getSemanticResources());

    IGetElementsQueries getElementsQueries = CapellaQueries.getInstance().getGetElementsQueries();
    R1 = (FilteringResult) getElementsQueries.getElementById(capellaProject, R1_ID);
    R2 = (FilteringResult) getElementsQueries.getElementById(capellaProject, R2_ID);
    R3 = (FilteringResult) getElementsQueries.getElementById(capellaProject, R3_ID);
    CR1 = (ComposedFilteringResult) getElementsQueries.getElementById(capellaProject, CR1_ID);
    CR2 = (ComposedFilteringResult) getElementsQueries.getElementById(capellaProject, CR2_ID);
    CR3 = (ComposedFilteringResult) getElementsQueries.getElementById(capellaProject, CR3_ID);

    C1 = (FilteringCriterion) getElementsQueries.getElementById(capellaProject, C1_ID);
    C2 = (FilteringCriterion) getElementsQueries.getElementById(capellaProject, C2_ID);
    C3 = (FilteringCriterion) getElementsQueries.getElementById(capellaProject, C3_ID);
    C4 = (FilteringCriterion) getElementsQueries.getElementById(capellaProject, C4_ID);

    PC1 = (PhysicalComponent) getElementsQueries.getElementById(capellaProject, PC1_ID);
    PC2 = (PhysicalComponent) getElementsQueries.getElementById(capellaProject, PC2_ID);
    PC3 = (PhysicalComponent) getElementsQueries.getElementById(capellaProject, PC3_ID);
    PC4 = (PhysicalComponent) getElementsQueries.getElementById(capellaProject, PC4_ID);
    PC5 = (PhysicalComponent) getElementsQueries.getElementById(capellaProject, PC5_ID);
    PC6 = (PhysicalComponent) getElementsQueries.getElementById(capellaProject, PC6_ID);
    PC7 = (PhysicalComponent) getElementsQueries.getElementById(capellaProject, PC7_ID);
    PC8 = (PhysicalComponent) getElementsQueries.getElementById(capellaProject, PC8_ID);

    PP1 = (Part) getElementsQueries.getElementById(capellaProject, PP1_ID);
    PP2 = (Part) getElementsQueries.getElementById(capellaProject, PP2_ID);
    PP3 = (Part) getElementsQueries.getElementById(capellaProject, PP3_ID);
    PP4 = (Part) getElementsQueries.getElementById(capellaProject, PP4_ID);
    PP5 = (Part) getElementsQueries.getElementById(capellaProject, PP5_ID);
    PP6 = (Part) getElementsQueries.getElementById(capellaProject, PP6_ID);
    PP7 = (Part) getElementsQueries.getElementById(capellaProject, PP7_ID);
    PP8 = (Part) getElementsQueries.getElementById(capellaProject, PP8_ID);

  }

  @Override
  public void test() throws Exception {
    FilteringServices service = FilteringServices.getService();

    testGetAssociatedElements(service);
    testGetAssociatedCriteria(service);
    testGetAssociatedResults(service);
    testGetAssociatedElementsForFilteringResult(service);

  }

  private void testGetAssociatedElements(FilteringServices service) {
    // from a criteria, get all associated elements

    // N.B.: times 2 since we have a component and it's part
    assertEquals(4 * 2, service.getAssociatedElements(C1).size());
    assertEquals(2 * 2, service.getAssociatedElements(C2).size());
    assertEquals(3 * 2, service.getAssociatedElements(C3).size());
    assertEquals(2 * 2, service.getAssociatedElements(C4).size());
  }

  private void testGetAssociatedCriteria(FilteringServices service) {

    // from a criteria, get all associated elements

    assertTrue(areEqualIgnoreOrder(Arrays.asList(C1), service.getAssociatedCriteria(PC1)));
    assertTrue(areEqualIgnoreOrder(Arrays.asList(C2, C1), service.getAssociatedCriteria(PC2)));
    assertTrue(areEqualIgnoreOrder(Arrays.asList(C3), service.getAssociatedCriteria(PC3)));
    assertTrue(areEqualIgnoreOrder(Arrays.asList(C4), service.getAssociatedCriteria(PC4)));
    assertTrue(areEqualIgnoreOrder(Arrays.asList(C1), service.getAssociatedCriteria(PC5)));
    assertTrue(areEqualIgnoreOrder(Arrays.asList(C2), service.getAssociatedCriteria(PC6)));
    assertTrue(areEqualIgnoreOrder(Arrays.asList(C3), service.getAssociatedCriteria(PC7)));
    assertTrue(areEqualIgnoreOrder(Arrays.asList(C1, C3, C4), service.getAssociatedCriteria(PC8)));

  }

  private void testGetAssociatedResults(FilteringServices service) {

    // from a criteria, get all associated elements
    assertTrue(areEqualIgnoreOrder(service.getAssociatedResults(PC1), Arrays.asList(R1, CR1, CR2)));
    assertTrue(areEqualIgnoreOrder(service.getAssociatedResults(PC2), Arrays.asList(R1, R2, CR1, CR2, CR3)));
    assertTrue(areEqualIgnoreOrder(service.getAssociatedResults(PC3), Arrays.asList(R2, R3, CR1)));
    assertTrue(areEqualIgnoreOrder(service.getAssociatedResults(PC4), Arrays.asList(R3)));
    assertTrue(areEqualIgnoreOrder(service.getAssociatedResults(PC5), Arrays.asList(R1, CR1, CR2)));
    assertTrue(areEqualIgnoreOrder(service.getAssociatedResults(PC6), Arrays.asList(R1, R2, CR1, CR2, CR3)));
    assertTrue(areEqualIgnoreOrder(service.getAssociatedResults(PC7), Arrays.asList(R2, R3, CR1)));
    assertTrue(areEqualIgnoreOrder(service.getAssociatedResults(PC8), Arrays.asList(R1, R2, R3, CR1, CR3)));

  }

  private void testGetAssociatedElementsForFilteringResult(FilteringServices service) {

    // from a criteria, get all associated elements
    assertTrue(areEqualIgnoreOrder(service.getAssociatedElements(R1),
        Arrays.asList(PC1, PP1, PC2, PP2, PC5, PP5, PC6, PP6, PC8, PP8)));
    assertTrue(areEqualIgnoreOrder(service.getAssociatedElements(R2),
        Arrays.asList(PC2, PP2, PC3, PP3, PC6, PP6, PC7, PP7, PC8, PP8)));
    assertTrue(
        areEqualIgnoreOrder(service.getAssociatedElements(R3), Arrays.asList(PC3, PP3, PC4, PP4, PC7, PP7, PC8, PP8)));
    assertTrue(areEqualIgnoreOrder(service.getAssociatedElements(CR1),
        Arrays.asList(PC1, PP1, PC2, PP2, PC3, PP3, PC5, PP5, PC6, PP6, PC7, PP7, PC8, PP8)));
    assertTrue(
        areEqualIgnoreOrder(service.getAssociatedElements(CR2), Arrays.asList(PC1, PP1, PC2, PP2, PC5, PP5, PC6, PP6)));
    assertTrue(areEqualIgnoreOrder(service.getAssociatedElements(CR3), Arrays.asList(PC2, PP2, PC6, PP6, PC8, PP8)));

  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(TEST_MODEL_NAME);
  }
}
