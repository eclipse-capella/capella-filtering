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

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.commandline.core.CommandLineConstants;
import org.polarsys.capella.core.commandline.core.CommandLineException;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.validation.commandline.DerivationCommandLine;
import org.polarsys.capella.core.validation.commandline.FilteringCommandLineConstants;
import org.polarsys.capella.test.commandline.ju.utils.MockApplicationContext;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.IFileRequestor;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;

/**
 * Test simulating a Derivation launch from command line.
 */
public class DerivationCommandLineTest extends BasicTestCase {

  private static String INPUT_PROJECT_NAME = "derivationProject";
  private static String FILTERING_RESULT_ID = "4e535130-528c-49ff-b27e-69833f15f395";
  private static String FILTERING_RESULT_NAME = "FilteringResult 1";
  private static String DERIVATION_PROJECT_NAME = "DerivationProjectName";
  private static String APP_ID = "org.polarsys.capella.filtering.commandline";

  @Override
  public void test() throws Exception {
    File inputProjectFolder = getFolderInTestModelRepository(INPUT_PROJECT_NAME);
    ModelProviderHelper.getInstance().importCapellaProject(INPUT_PROJECT_NAME, inputProjectFolder);

    assertProjectDerivationDefaultName();
    assertProjectDerivationCustomName();
  }

  private void assertProjectDerivationDefaultName() {
    String[] commandLineArguments = { CommandLineConstants.ID, APP_ID, CommandLineConstants.INPUT, INPUT_PROJECT_NAME,
        FilteringCommandLineConstants.FILTERING_RESULT_ID, FILTERING_RESULT_ID };

    // First derivation should generate a project using FILTERING_RESULT_NAME
    runDerivationCommandLine(commandLineArguments);
    IProject derivedProject = IResourceHelpers.getEclipseProjectInWorkspace(FILTERING_RESULT_NAME);
    assertDerivedProject(derivedProject);

    // Second derivation should generate a project with an incremented FILTERING_RESULT_NAME
    runDerivationCommandLine(commandLineArguments);
    derivedProject = IResourceHelpers.getEclipseProjectInWorkspace(FILTERING_RESULT_NAME + "_2");
    assertDerivedProject(derivedProject);
  }

  private void assertProjectDerivationCustomName() {
    String[] commandLineArguments = { CommandLineConstants.ID, APP_ID, CommandLineConstants.INPUT, INPUT_PROJECT_NAME,
        FilteringCommandLineConstants.FILTERING_RESULT_ID, FILTERING_RESULT_ID,
        FilteringCommandLineConstants.DERIVATION_PROJECT_NAME, DERIVATION_PROJECT_NAME };

    // First derivation should generate a project using DERIVATION_PROJECT_NAME
    runDerivationCommandLine(commandLineArguments);
    IProject derivedProject = IResourceHelpers.getEclipseProjectInWorkspace(DERIVATION_PROJECT_NAME);
    assertDerivedProject(derivedProject);

    // Second derivation should generate a project with an incremented DERIVATION_PROJECT_NAME
    runDerivationCommandLine(commandLineArguments);
    derivedProject = IResourceHelpers.getEclipseProjectInWorkspace(DERIVATION_PROJECT_NAME + "_2");
    assertDerivedProject(derivedProject);
  }

  /**
   * Simulate launching from command line
   * 
   * @param commandLineArguments
   *          the arguments
   */
  private void runDerivationCommandLine(String[] commandLineArguments) {
    IApplicationContext mockApplicationContext = new MockApplicationContext(commandLineArguments);

    DerivationCommandLine derivationCommandLine = new DerivationCommandLine();
    try {
      derivationCommandLine.parseContext(mockApplicationContext);
      derivationCommandLine.checkArgs(mockApplicationContext);
      derivationCommandLine.prepare(mockApplicationContext);
      derivationCommandLine.execute(mockApplicationContext);

    } catch (CommandLineException e) {
      fail(e.getMessage());
    }

  }

  protected Session getSession(IProject project) {
    IFile airdFile = new IFileRequestor().search(project, CapellaResourceHelper.AIRD_FILE_EXTENSION).get(0);
    return SessionManager.INSTANCE.getSession(EcoreUtil2.getURI(airdFile), new NullProgressMonitor());
  }

  protected void assertDerivedProject(IProject project) {
    assertTrue(project.exists());

    Session session = getSession(project);
    assertTrue(session != null);

    session.open(new NullProgressMonitor());

    GuiActions.closeSession(session);
  }
}
