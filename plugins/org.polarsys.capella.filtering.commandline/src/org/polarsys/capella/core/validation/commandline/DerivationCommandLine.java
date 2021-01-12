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
package org.polarsys.capella.core.validation.commandline;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.commandline.core.CommandLineConstants;
import org.polarsys.capella.core.commandline.core.CommandLineException;
import org.polarsys.capella.core.commandline.core.ui.AbstractWorkbenchCommandLine;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.filtering.AbstractFilteringResult;
import org.polarsys.capella.filtering.ComposedFilteringResult;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringResult;
import org.polarsys.capella.filtering.tools.actions.ComposedFilteringExtractionJob;
import org.polarsys.capella.filtering.tools.actions.FilteringExtractionJob;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

public class DerivationCommandLine extends AbstractWorkbenchCommandLine {

  public DerivationCommandLine() {
    super();
    argHelper = new FilteringArgumentHelper();
  }

  @Override
  public void checkArgs(IApplicationContext context) throws CommandLineException {
    super.checkArgs(context);
    String filteringResultId = getArgHelper().getFilteringResultId();

    if (StringUtil.isEmpty(filteringResultId)) {
      logErrorAndThrowException(Messages.FILTERING_RESULT_ID_MANDATORY);
    }
  }

  @Override
  protected IStatus executeWithinWorkbench() {
    Set<IProject> projectsFromInput = getProjectsFromInput();
    IProject sourceProject = projectsFromInput.iterator().next();

    if (projectsFromInput.size() > 1) {
      logInfo(NLS.bind(Messages.MULTIPLE_INPUT_PROJECTS_DETECTED, sourceProject.getName()));
    }

    List<IFile> airdFiles = getAirdFiles(sourceProject.getFullPath().toPortableString());
    if (airdFiles.isEmpty()) {
      return new Status(Status.ERROR, Activator.PLUGIN_ID,
          NLS.bind(Messages.PROJECT_CONTAINS_NO_AIRD, sourceProject.getName()));
    }

    IFile airdFile = airdFiles.get(0);
    Session session = getSession(airdFile);
    Project rootElement = loadSemanticRootElement(session);

    if (rootElement == null) {
      return new Status(Status.ERROR, Activator.PLUGIN_ID, Messages.NO_SEMANTIC_MODEL_FOUND);
    }

    String filteringResultId = getArgHelper().getFilteringResultId();
    ModelElement filteringResultCandidate = CapellaQueries.getInstance().getGetElementsQueries()
        .getElementById(rootElement, filteringResultId);

    if (!(filteringResultCandidate instanceof AbstractFilteringResult)) {
      return new Status(Status.ERROR, Activator.PLUGIN_ID, Messages.INVALID_FILTERING_RESULT_ID);
    }

    AbstractFilteringResult filteringResult = (AbstractFilteringResult) filteringResultCandidate;

    String derivedProjectName = computeDerivedProjectName(getArgHelper().getDerivationProjectName(),
        filteringResult.getName());

    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    IProgressMonitor progressMonitor = new NullProgressMonitor();
    IProject targetDerivedProject = workspace.getRoot().getProject(derivedProjectName);

    try {
      targetDerivedProject.create(progressMonitor);
      targetDerivedProject.open(progressMonitor);
    } catch (CoreException e) {
      return new Status(Status.ERROR, Activator.PLUGIN_ID, e.getMessage(), e);
    }

    FilteringCriterionSet computedFilteringCriterionSet = filteringResult.computeFilteringCriterionSet();
    List<FilteringCriterion> filteringCriteria = computedFilteringCriterionSet.getFilteringCriteria();
    IWorkspaceRunnable job = null;

    if (filteringResult instanceof ComposedFilteringResult)
      job = new ComposedFilteringExtractionJob(sourceProject, targetDerivedProject, filteringCriteria, filteringResult,
          null);
    else if (filteringResult instanceof FilteringResult) {
      job = new FilteringExtractionJob(sourceProject, targetDerivedProject, filteringCriteria, filteringResult, null);
    }

    try {
      workspace.run(job, progressMonitor);
    } catch (CoreException e) {
      return new Status(Status.ERROR, Activator.PLUGIN_ID, e.getMessage(), e);
    }

    session.close(progressMonitor);

    return Status.OK_STATUS;
  }

  private FilteringArgumentHelper getArgHelper() {
    return (FilteringArgumentHelper) argHelper;
  }

  @Override
  public void printHelp() {
    List<String> hiddenArguments = Arrays.asList( //
        CommandLineConstants.OUTPUTFOLDER.replace("-", ""), //
        CommandLineConstants.EXPORTZIP.replace("-", ""));

    super.printHelp(hiddenArguments);

    System.out.printf("%-30s%-15s%s\n", FilteringCommandLineConstants.FILTERING_RESULT_ID.replace("-", ""),
        Messages.FILTERING_RESULT_ID_CATEGORY, Messages.FILTERING_RESULT_ID_DESCRIPTION);

    System.out.printf("%-30s%-15s%s\n", FilteringCommandLineConstants.DERIVATION_PROJECT_NAME.replace("-", ""),
        Messages.DERIVATION_PROJECT_CATEGORY, Messages.DERIVATION_PROJECT_DESCRIPTION);
  }

  private Project loadSemanticRootElement(Session session) {
    if (!session.isOpen()) {
      session.open(new NullProgressMonitor());
    }
    return SessionHelper.getCapellaProject(session);
  }

  private Session getSession(IFile airdFile) {
    String airdFilePath = airdFile.getFullPath().toPortableString();
    URI airdFileURI = URI.createPlatformResourceURI(airdFilePath, true);

    return SessionManager.INSTANCE.getSession(airdFileURI, new NullProgressMonitor());
  }

  /**
   * Computes the project derivation name. </br>
   * The user provided project name argument is used in priority. </br>
   * If absent then the filtering result name is used instead.
   * 
   * @param userProvidedProjectName
   * @param filteringResultName
   * @return the project derivation name.
   */
  private String computeDerivedProjectName(String userProvidedProjectName, String filteringResultName) {
    String derivationProjectName = "";

    if (!StringUtil.isEmpty(userProvidedProjectName)) {
      derivationProjectName = computeDerivedProjectName(userProvidedProjectName);

      if (!userProvidedProjectName.equals(derivationProjectName)) {
        logInfo(NLS.bind(Messages.DERIVATION_PROJECTS_ALREADY_EXISTS, userProvidedProjectName));
      }
    } else {
      derivationProjectName = computeDerivedProjectName(filteringResultName);
    }

    logInfo(NLS.bind(Messages.DERIVATION_PROJECT_COMPUTED_NAME, derivationProjectName));

    return derivationProjectName;
  }

  /**
   * Same strategy as org.polarsys.capella.filtering.tools.actions.ComposedFilteringProjectWizard
   * 
   * @param rootName
   *          the root name
   * @return the derivation project name
   */
  private String computeDerivedProjectName(String rootName) {
    String derivedProjectName = rootName;

    for (int i = 2; FilteringUtils.projectExists(derivedProjectName); i++) {
      derivedProjectName = rootName + "_" + i;
    }

    return derivedProjectName;
  }
}