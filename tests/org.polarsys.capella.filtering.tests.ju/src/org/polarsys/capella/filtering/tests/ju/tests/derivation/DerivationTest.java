/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.filtering.tests.ju.tests.derivation;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy;
import org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.image.ImageManager;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.filtering.AbstractFilteringResult;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringResult;
import org.polarsys.capella.filtering.FilteringResults;
import org.polarsys.capella.filtering.tests.ju.tests.helpers.FilteringTestHelper;
import org.polarsys.capella.filtering.tools.actions.FilteringExtractionJob;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Filtering Derivation test.<br>
 * Tests a set of derivation performed on {@link #getModelsUnderTest()} and compared against expected <br>
 * derivation models identified by names with pattern given by {@link #getReferenceModelName(String, String)}
 * 
 */
@SuppressWarnings({ "nls" })
public class DerivationTest extends BasicTestCase {
  private IProgressMonitor progressMonitor = new NullProgressMonitor();

  Session session = null;
  IProject project = null;
  AbstractFilteringResult currentFilteringResult = null;
  
  String derivedProjectName;
  IProject expectedResultProject;

  private String modelId;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("MyLib", "sysmodel", "sysmodel_S_FR1", "sysmodel_S_FR2", "sysmodel_S_Empty_FR", "TestLib",
        "TestLib_DieselFilteringResult", "TestLib_HybridFilteringResult", "TestLib_PetrolFilteringResult",
        "TestSPLProject", "TestSPLProject_EmptyFilteringResult", "TestSPLProject_FilteringResult");
  }

  public List<String> getModelsUnderTest() {
    return Arrays.asList("sysmodel", "TestLib", "TestSPLProject");
  }

  private String getReferenceModelName(String originalModelName, String filteringResultName) {

    return originalModelName + "_" + filteringResultName;

  }

  @Override
  public void test() {
    // We use this try/catch with a wrappedException to be able to see in
    // junit involved project and filtering results
    try {
      doTestDerivation();
    } catch (Exception exception) {
      throw new WrappedException(getInfoOfCurrentTest(), exception);
    }
  }

  public void doTestDerivation() throws Exception {
    for (String inputModelName : getModelsUnderTest()) {
      performDerivationAndCheck(inputModelName);
    }
  }

  /**
   * Perform derivation for each {@link FilteringResult} in an already loaded model. Models can be loaded using
   * {@link #getRequiredTestModels()}
   * 
   * @throws Exception
   */
  private void performDerivationAndCheck(String inputModelName) throws Exception {
    project = IResourceHelpers.getEclipseProjectInWorkspace(inputModelName);

    Session session = getSessionForTestModel(inputModelName);
    session.open(progressMonitor);

    // Get the configurations element from the model
    FilteringResults FilteringResults = FilteringTestHelper.getFilteringResults(session.getSemanticResources());

    // foreach result derive and test
    for (AbstractFilteringResult filterResult : FilteringResults.getFilteringResults()) {
      this.currentFilteringResult = filterResult;
      System.err.println("=== currentFilteringResult: " + currentFilteringResult);

      expectedResultProject = IResourceHelpers.getEclipseProjectInWorkspace(filterResult.getName());

      // Create derived project
      IWorkspace workspace = ResourcesPlugin.getWorkspace();
      derivedProjectName = filterResult.getName();
      IProject derivedProject = workspace.getRoot().getProject(derivedProjectName);
      derivedProject.create(progressMonitor);
      derivedProject.open(progressMonitor);

      // Perform derivation. The process we are interested in this test
      FilteringCriterionSet computedFilteringCriterionSet = filterResult.computeFilteringCriterionSet();

      if (computedFilteringCriterionSet == null) {
        fail(getInfoOfCurrentTest() + "Filtering result is empty! (computed filtering criterion set is empty)");
      }

      FilteringExtractionJob job = new FilteringExtractionJob(project, derivedProject,
          computedFilteringCriterionSet.getFilteringCriteria(), filterResult, modelId);
      workspace.run(job, progressMonitor);

      // Check derived project
      //
      // Open session
      IFile derivedAirdFile = findFirstMatchingFile(SiriusUtil.SESSION_RESOURCE_EXTENSION, derivedProject);

      Session sessionOfDerived = org.polarsys.capella.test.framework.helpers.TestHelper
          .openOrGetSession(derivedAirdFile);
      sessionOfDerived.open(progressMonitor);
      assertTrue("Session of derived project does not open!: " + getInfoOfCurrentTest(), sessionOfDerived.isOpen());

      // Get and open reference model
      String refModelName = getReferenceModelName(inputModelName, filterResult.getName());
      Session expectedResultSession = getSessionForTestModel(refModelName);

      expectedResultSession.open(progressMonitor);
      assertTrue("Session of expected project does not open!: " + getInfoOfCurrentTest(),
          expectedResultSession.isOpen());

      // Check original and derived projects have same natures

      checkProjectsHaveSameNatures(project, derivedProject);

      //
      // Check derived project against expected one
      //
      // Natures
      checkProjectsHaveSameNatures(expectedResultProject, derivedProject);
      // Project's content (files, folders)
      checkProjectsContainSameIResources(expectedResultProject, derivedProject);
      // References to semantic resources
      checkReferencedSemanticResources(expectedResultSession, sessionOfDerived);
      // Compare .capella files
      checkSemanticResourcesAreEqual(TestHelper.getSemanticResource(expectedResultSession),
          TestHelper.getSemanticResource(sessionOfDerived));

      // Delete derived project
      sessionOfDerived.save(progressMonitor);
      sessionOfDerived.close(progressMonitor);
      derivedProject.close(progressMonitor);
      derivedProject.delete(IResource.FORCE | IResource.ALWAYS_DELETE_PROJECT_CONTENT, progressMonitor);
    }

  }

  /**
   * Check that given Sessions have the same list of semantic resources (paths relative to the containing project are
   * used).
   * 
   * @param expectedResultSession
   * @param sessionOfDerived
   */
  public void checkReferencedSemanticResources(Session expectedResultSession, Session sessionOfDerived) {
    Collection<Resource> expectedSemanticResources = expectedResultSession.getSemanticResources();
    Collection<Resource> derivedSemanticResources = sessionOfDerived.getSemanticResources();

    Set<IPath> expectedSemanticResourcesSet = getResourceRelativePaths(expectedSemanticResources);
    Set<IPath> derivedSemanticResourceSet = getResourceRelativePaths(derivedSemanticResources);

    assertEquals("Sessions do not have the same semantic resources list", expectedSemanticResourcesSet,
        derivedSemanticResourceSet);
  }

  // Info to display (in case of failing test)
  private String getInfoOfCurrentTest() {
    String projectName = project != null ? project.getName() : "?";
    String filteringResultName = currentFilteringResult != null ? currentFilteringResult.getName() : "?";
    return "Project=[" + projectName + "] with FilteringResult=[" + filteringResultName + "]";
  }

  /**
   * Specific matching policy for Project element: When comparing Project elements, do not use their ids but their
   * names.
   */
  private class TestCaseMatchPolicy extends DefaultMatchPolicy {

    @Override
    public Object getMatchID(EObject element, ITreeDataScope<EObject> scope) {
      // Use the name as match id for projects because in case of library,
      // derived projects have different Capella id.
      if (element instanceof Project) {
        return ((Project) element).getName();
      }
      return super.getMatchID(element, scope);
    }
  }
  
  /**
   * Specific Diff Policy to ensure diffs between images links in description fields are not taken into account.</br>
   * In order to do so, take the description of CapellaElements, and replace the name of the expected results project by
   * the name of the derived project name.
   */
  private class TestCaseDiffPolicy extends DefaultDiffPolicy {
    
    private String derivedProjectName;
    private String expectedResultProjectName;
    private static final String QUOTE = "\"";
      
    public TestCaseDiffPolicy(String derivedProjectName, String expectedResultProjectName) {
        this.derivedProjectName = derivedProjectName;
        this.expectedResultProjectName = expectedResultProjectName;
    }

    @Override
      public boolean considerEqual(Object value1_p, Object value2_p, Object attribute_p, ITreeDataScope<EObject> scope_p) {
          if (attribute_p.equals(CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION)) {
              // Replace image paths to ensure diff is correct
              // Adapted from /org.polarsys.capella.core.sirius.ui/src/org/polarsys/capella/core/sirius/ui/refactoring/WorkspaceImagePathChange.java
              String textWithOriginalImagePaths = (String) value1_p;
              String textWithUpdatedImagePaths = (String) value2_p;
              Pattern pattern = Pattern.compile(ImageManager.HTML_IMAGE_PATH_PATTERN);
              Matcher matcher = pattern.matcher(textWithOriginalImagePaths);

              while (matcher.find()) {
                String originalPath = matcher.group(1);
                if (!originalPath.startsWith("/")) {
                  String newPath = originalPath;
                  Path path = new Path(originalPath);
                  String segment = path.segment(0);
                  if (derivedProjectName.equals(segment)) {
                    newPath = originalPath.replace(derivedProjectName, expectedResultProjectName);
                  }
                  if (!newPath.equals(originalPath)) {
                    // Use quote as start and end character to be sure to replace the path as a whole and not only a
                    // part of the path
                      textWithOriginalImagePaths = textWithOriginalImagePaths.replace(QUOTE + originalPath + QUOTE,
                        QUOTE + newPath + QUOTE);
                  }
                }
              }
              return textWithOriginalImagePaths.equals(textWithUpdatedImagePaths);
          }
          return super.considerEqual(value1_p, value2_p, attribute_p, scope_p);
      }
  }

  /**
   * Return the first IFile under resource tree starting at 'root' that has a given extension. Returns null if no
   * matching file is found.
   *
   * @param fileExtension
   *          the file extension, may be null or the empty string
   * @param root
   *          where to start searching, null to start at the workspace root
   * @see IResource.getFileExtension
   * @throws CoreException
   */
  public static IFile findFirstMatchingFile(final String fileExtension, IResource root) throws CoreException {
    final AtomicReference<IFile> result = new AtomicReference<IFile>();
    IResource scope = root != null ? root : ResourcesPlugin.getWorkspace().getRoot();
    scope.accept(new IResourceVisitor() {
      public boolean visit(IResource resource) {
        if (result.get() != null) {
          return false;
        }
        if (resource.getType() == IResource.FILE) {
          if (fileExtension == null) {
            if (((IFile) resource).getFileExtension() == null) {
              result.set((IFile) resource);
            }
          } else {
            if (fileExtension.equals(((IFile) resource).getFileExtension())) {
              result.set((IFile) resource);
            }
          }
        }
        return true;
      }
    });
    return result.get();
  }

  /**
   * Compare given semantic resources using Diff/Merge.
   * 
   * @param expectedResource
   * @param derivedResource
   * @throws InterruptedException
   * @throws IOException
   * @throws CoreException
   */
  public void checkSemanticResourcesAreEqual(Resource expectedResource, Resource derivedResource)
      throws InterruptedException, IOException, CoreException {
    // Compare with the expected results (if contributed) Check
    // readme_namingConventions
    // Import expected result

    // Instantiate the scopes to compare
    IEditableModelScope targetScope = new FragmentedModelScope(derivedResource, true);
    IEditableModelScope referenceScope = new FragmentedModelScope(expectedResource, true);

    // Compare
    IComparison comparison = new EComparisonImpl(targetScope, referenceScope);
    comparison.compute(new TestCaseMatchPolicy(), new TestCaseDiffPolicy(derivedProjectName, project.getName() + "_" + derivedProjectName), null, progressMonitor);

    // Check if differences
    if (comparison.getNbDifferences() != 0) {
      fail(getInfoOfCurrentTest() + " expected derivation result failed.");
    }
  }

  /**
   * Check that given IProjects have the same natures.
   * 
   * @param project1
   * @param project2
   * @throws CoreException
   */
  public void checkProjectsHaveSameNatures(IProject project1, IProject project2) throws CoreException {
    String[] naturesProject1 = project1.getDescription().getNatureIds();
    String[] naturesProject2 = project2.getDescription().getNatureIds();

    // Check natures ignoring order.
    Set<String> naturesProject1Set = new HashSet<>(Arrays.asList(naturesProject1));
    Set<String> naturesProject2Set = new HashSet<>(Arrays.asList(naturesProject2));

    assertEquals("Projects do not have the same nature(s)", naturesProject1Set, naturesProject2Set);
  }

  /**
   * Check (recursively) that given IContainers contain the same IResource (i.e. the same list of file/folder)
   * 
   * @param container1
   * @param container2
   * @throws CoreException
   */
  public void checkProjectsContainSameIResources(IContainer container1, IContainer container2) throws CoreException {
    IResource[] project1Members = container1.members();
    IResource[] project2Members = container2.members();

    Set<IPath> project1MembersPaths = getProjectRelativePaths(project1Members);
    Set<IPath> project2MembersPaths = getProjectRelativePaths(project2Members);

    assertEquals("Projects do not have same content", project1MembersPaths, project2MembersPaths);

    // Look in IContainers (folders...).
    for (IResource project1IResource : project1Members) {
      if (project1IResource instanceof IContainer) {
        IResource project2Resource = container2.findMember(project1IResource.getName());
        checkProjectsContainSameIResources((IContainer) project1IResource, (IContainer) project2Resource);
      }
    }
  }

  /**
   * Get project relative paths of given IResources.
   * 
   * @param projectMembers
   * @return
   */
  public Set<IPath> getProjectRelativePaths(IResource[] projectMembers) {
    Set<IPath> relativePaths = new HashSet<>();
    for (IResource iResource : projectMembers) {
      relativePaths.add(iResource.getProjectRelativePath());
    }
    return relativePaths;
  }

  /**
   * Get project relative paths of given Resources.
   * 
   * @param projectMembers
   * @return
   */
  public Set<IPath> getResourceRelativePaths(Collection<Resource> resources) {
    Set<IPath> resourceRelativePaths = new HashSet<>();
    for (Resource r : resources) {
      resourceRelativePaths.add(WorkspaceSynchronizer.getFile(r).getProjectRelativePath());
    }
    return resourceRelativePaths;
  }

}
