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
package org.polarsys.capella.core.validation.commandline;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.Category;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.validation.ConstraintStatusDiagnostic;
import org.polarsys.capella.core.commandline.core.AbstractCommandLine;
import org.polarsys.capella.core.commandline.core.CommandLineException;
import org.polarsys.capella.core.commandline.core.CommandLineMode;
import org.polarsys.capella.core.commandline.core.Messages;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.filtering.AbstractFilteringResult;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.tools.actions.FilteringExtractionJob;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

public class FilteringCommandLine extends AbstractCommandLine {

  public FilteringCommandLine() {
    super();
    argHelper = new FilteringArgumentHelper();
  }

  /**
   * {@inheritDoc}
   *
   * @throws IOException
   */
  @Override
  public void checkArgs(IApplicationContext context) throws CommandLineException {
    // refreshing the workspace needed in case of folders removed from outside the workbench
    try {
      ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
    } catch (CoreException exception) {
      logErrorAndThrowException(Messages.refresh_problem);
    }

    // is filepath empty ?
    if (isEmtyOrNull(argHelper.getFilePath())) {
      logErrorAndThrowException(Messages.representation_mandatory);
    }

    // check -import argument
    if (isEmtyOrNull(argHelper.getImportProjects())) {
      setMode(CommandLineMode.NO_IMPORT);
      // project are not imported => check that -filepath and -outputfolder exists into the workspace
      checkFilePath();

    }
    // -import <list of projects> is specified => import the projects
    else if (!isEmtyOrNull(argHelper.getImportProjects())) {
      setMode(CommandLineMode.IMPORT);
    }

  }

  /**
   * @throws CommandLineException
   */
  private void checkFilePath() throws CommandLineException {
    // project exists in the workspace ?
    String projectName = getProjectName(argHelper.getFilePath());
    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
    if (!project.exists()) {
      logError(Messages.project + projectName + Messages.not_exist);
    }
    // file exists in the project ?
    try {
      project.open(new NullProgressMonitor());
    } catch (CoreException exception) {
      StringBuilder loggerMessage = new StringBuilder(Messages.unable_open_project + project.getName());
      logger.warn(loggerMessage.toString(), exception);
    }
    IFile file = project.getFile(getRelativeFilePath(argHelper.getFilePath()));
    if (!file.exists()) {
      String message = Messages.aird + argHelper.getFilePath() + Messages.not_exist;
      logError(message);
      throw new CommandLineException(message);
    }
  }

  @Override
  public void printHelp() {
    System.out.println("Capella Filtering Command Line Derivator"); //$NON-NLS-1$
    super.printHelp();
  }

  @Override
  public boolean execute(IApplicationContext context) throws CommandLineException {

    startFakeWorkbench();

    // load the AIRD
    String fileURI = Messages.resource_prefix + argHelper.getFilePath();
    URI uri = URI.createURI(fileURI);

    String outputFolder = argHelper.getOutputFolder();

    // get uri of filtering result specified as a complete path
    // set the rule set
    String filteringResultId = ((FilteringArgumentHelper) argHelper).getFilteringResultId();

    if (isEmtyOrNull(filteringResultId)) {// validate selected EObjects
      System.err.println("Filtering result id is null or empty!");
    }

    boolean status;

    try {

      status = execute(uri, filteringResultId, outputFolder);

    } catch (CoreException | FileNotFoundException | OperationCanceledException | InterruptedException exception) {
      logError(exception.getMessage());
      throw new CommandLineException(exception.getMessage());
    }

    if (status) {
      logInfo("Filtering Derivation generated to: " + " " + argHelper.getOutputFolder()); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return false;
  }

  private boolean execute(URI uri, final String filteringResultId, final String outputFolder)
      throws FileNotFoundException, CoreException, CommandLineException, OperationCanceledException,
      InterruptedException {

    // load
    Project semanticRootElement = loadSemanticRootElement(uri);
    IProject currentProject = FilteringUtils.getEclipseProject(semanticRootElement);

    if (semanticRootElement == null) {
      throw new CommandLineException("No semantic model found!"); //$NON-NLS-1$
    }

    ModelElement element = CapellaQueries.getInstance().getGetElementsQueries().getElementById(semanticRootElement,
        filteringResultId);

    if (!(element instanceof AbstractFilteringResult)) {
      System.err.println("Id does not reference a Filtering Result!");
      return false;
    }

    AbstractFilteringResult filteringResult = (AbstractFilteringResult) element;

    // create derivated empty project

    // List<IProject> referencedProjects = Collections.emptyList();
    // IProject clonedProject = helper.createNewEclipseProject(newProjectName, newPath, referencedProjects, monitor);
    // SessionCreationHelper helper = new ProjectSessionCreationHelper(true, true, ProjectApproach.SingletonComponents);

    // Create derived project
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    String derivedProjectName = filteringResult.getName();

    // If project already exists get a non existent project name
    int i = 2;

    while (FilteringUtils.projectExists(derivedProjectName)) {
      System.out.println("a project with name: " + derivedProjectName + " already exists");
      derivedProjectName = filteringResult.getName() + "_" + i; //$NON-NLS-1$
      i++;
    }

    IProject derivedProject = workspace.getRoot().getProject(derivedProjectName);

    IProgressMonitor progressMonitor = new NullProgressMonitor();
    derivedProject.create(progressMonitor);
    derivedProject.open(progressMonitor); // TODO: NEEDED ?

    FilteringCriterionSet computedFilteringCriterionSet = filteringResult.computeFilteringCriterionSet();

    // if (computedFilteringCriterionSet == null) {
    // fail(getInfoOfCurrentTest() + "Filtering result is empty! (computed filtering criterion set is empty)");
    // }

    // Create and run derivation Job
    FilteringExtractionJob job = new FilteringExtractionJob(currentProject, derivedProject,
        computedFilteringCriterionSet.getFilteringCriteria(), filteringResult, null);
    workspace.run(job, progressMonitor);

    Session session = SessionManager.INSTANCE.getSession(uri, new NullProgressMonitor());
    session.close(progressMonitor);

    // wait for all jobs to finish
    // Job.getJobManager().join(null, progressMonitor);

    return true;
  }

  /**
   * Ensure that all constraints have been loaded.
   */
  public static void ensureEMFValidationActivation() {

    ModelValidationService.getInstance().loadXmlConstraintDeclarations();

    return;
  }

  /**
   * @param project
   * @param uris
   * @throws CoreException
   */
  private List<EObject> loadEObjects(Project project, List<String> uris) throws CoreException {
    List<EObject> results = new ArrayList<EObject>();
    for (String uriFragment : uris) {
      String idSegment = getIdSegment(uriFragment);
      ModelElement element = CapellaQueries.getInstance().getGetElementsQueries().getElementById(project, idSegment);
      results.add(element);
    }
    return results;
  }

  /**
   * @param uriFragment
   * @return
   */
  private String getIdSegment(String uriFragment) {
    URI uri = URI.createURI(uriFragment);
    return uri.fragment();
  }

  /**
   * @param uri
   * @return
   */
  private Project loadSemanticRootElement(URI uri) {
    SessionManager sessionManager = SessionManager.INSTANCE;
    Session session = sessionManager.getSession(uri, new NullProgressMonitor());
    if (!session.isOpen()) {
      session.open(new NullProgressMonitor());
    }
    return SessionHelper.getCapellaProject(session);
  }

  /**
   * A workbench is needed by some Sirius plugins
   */
  public static void startFakeWorkbench() {
    if (PlatformUI.isWorkbenchRunning()) {
      return;
    }

    Display display = PlatformUI.createDisplay();
    PlatformUI.createAndRunWorkbench(display, new WorkbenchAdvisor() {

      /**
       * {@inheritDoc}
       */
      @Override
      public boolean openWindows() {
        return false;
      }

      @Override
      public String getInitialWindowPerspectiveId() {
        return null;
      }
    });
  }

  /**
   * @param attribute
   * @return
   */
  private String getSeverityLabel(Integer severity) {
    switch (severity.intValue()) {
    case IMarker.SEVERITY_ERROR:
      return "Error"; //$NON-NLS-1$
    case IMarker.SEVERITY_WARNING:
      return "Warning"; //$NON-NLS-1$
    case IMarker.SEVERITY_INFO:
      return "Info"; //$NON-NLS-1$
    }
    return ""; //$NON-NLS-1$
  }

  /**
   * @param iMarker
   * @return
   * @throws CoreException
   */
  private String getCategory(IMarker iMarker) throws CoreException {
    String result = ""; //$NON-NLS-1$
    Diagnostic diagnostic = (Diagnostic) iMarker.getAdapter(Diagnostic.class);
    if (diagnostic instanceof ConstraintStatusDiagnostic) {
      Set<Category> cats = ((ConstraintStatusDiagnostic) diagnostic).getConstraintStatus().getConstraint()
          .getDescriptor().getCategories();
      if (!cats.isEmpty()) {
        result = cats.iterator().next().getQualifiedName();
      }
    }
    return result;

  }

}
