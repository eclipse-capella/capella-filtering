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
package org.polarsys.capella.filtering.tests.ju;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.filtering.tests.ju.tests.derivation.DerivationTestSuite;
import org.polarsys.capella.filtering.tests.ju.tests.misc.ActivatedFilterMigrationTest;
import org.polarsys.capella.filtering.tests.ju.tests.misc.DerivationCommandLineTest;
import org.polarsys.capella.filtering.tests.ju.tests.misc.MigrationBetweenVersionsTest;
import org.polarsys.capella.filtering.tests.ju.tests.misc.MiscTestSuite;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class MainTestSuite extends BasicTestSuite {

  public static Test suite() {
    return new MainTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> testCases = new ArrayList<>();
    testCases.add(new DerivationTestSuite());
    testCases.add(new MiscTestSuite());

    testCases.add(new MigrationBetweenVersionsTest());
    testCases.add(new ActivatedFilterMigrationTest());
    testCases.add(new DerivationCommandLineTest());
    return testCases;
  }

}