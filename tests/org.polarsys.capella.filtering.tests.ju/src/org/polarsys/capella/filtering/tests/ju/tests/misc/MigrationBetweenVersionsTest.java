/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.filtering.tests.ju.tests.misc;

import org.polarsys.capella.core.data.migration.MigrationHelpers;
import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * As a migration test might be broken while bump process, this junit ensures that :
 * 
 * - Filtering migration contribution still exist and properly registered
 * 
 * - specify that current EFactory is registered for the prefix of old releases of filtering (which is the same across
 * versions)
 */
public class MigrationBetweenVersionsTest extends BasicTestCase {

  @Override
  public void test() throws Exception {
    // It tests existence of a MigrationContribution providing this requirement and properly loaded.
    assertNotNull(MigrationHelpers.getInstance().getEFactory(FilteringPackage.eNS_PREFIX, null, null));
  }

}
