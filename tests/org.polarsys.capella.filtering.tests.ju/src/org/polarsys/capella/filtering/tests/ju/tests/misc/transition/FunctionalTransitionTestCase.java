/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.filtering.tests.ju.tests.misc.transition;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

import junit.framework.Test;

/**
 * This test case tests the transition of a AssociatedFilteringCriterionSet
 *
 */
public class FunctionalTransitionTestCase extends TopDownTransitionTestCase {
  public static final String SYSTEMFUNCTION_1 = "bc18b8d1-7b7c-4a92-8ee3-85ae431c1593"; //$NON-NLS-1$
  public static final String SF1_ASSOCIATED_FILTERING_CRITERION_SET = "e29b9987-09cb-4acb-8070-0a1abaaa7e3a"; //$NON-NLS-1$
  public static final String LOGICALCOMPONENT_1 = "ccefe212-df01-4926-9d04-dcf1acdcc4bc"; //$NON-NLS-1$
  public static final String LC1_ASSOCIATED_FILTERING_CRITERION_SET = "0a4ba05c-bd82-4bb2-98c6-f1736fd20299"; //$NON-NLS-1$

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Transition");
  }

  @Override
  public void performTest() throws Exception {
    EObject sf1 = getObject(SYSTEMFUNCTION_1);
    performFunctionalTransition(Arrays.asList(sf1));
    mustBeTransitioned(SF1_ASSOCIATED_FILTERING_CRITERION_SET);
    EObject lc1 = getObject(LOGICALCOMPONENT_1);
    performLCtoPCTransition(Arrays.asList(lc1));
    mustBeTransitioned(LC1_ASSOCIATED_FILTERING_CRITERION_SET);
  }

  public static Test suite() {
    return new FunctionalTransitionTestCase();
  }
}
