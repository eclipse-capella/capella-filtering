package org.polarsys.capella.filtering.tests.ju.tests.misc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.TestHelper;

public class DirtyReferencedModelsTest extends BasicTestCase {

  public static final String PROJECT_NAME = "simple-project";
  public static final String ROOT_LIBRARY_NAME = "simple-library";
  public static final String CHILD_LIBRARY_NAME = "simple-library-child";

  @Override
  public void test() throws Exception {
    Session projectSession = getSessionForTestModel(PROJECT_NAME);
    assertNotNull(projectSession);

    Session rootLibrarySession = getSessionForTestModel(ROOT_LIBRARY_NAME);
    assertNotNull(rootLibrarySession);

    Session childLibrarySession = getSessionForTestModel(CHILD_LIBRARY_NAME);
    assertNotNull(childLibrarySession);

    Resource projectResource = TestHelper.getSemanticResource(projectSession);
    Project project = ProjectExt.getProject(projectResource);
    assertNotNull(project);

    Map<IModel, Session> dirtyReferencedModels = FilteringUtils.getDirtyReferencedModels(project);
    assertTrue(dirtyReferencedModels.isEmpty());

    setSessionDirty(projectSession);
    dirtyReferencedModels = FilteringUtils.getDirtyReferencedModels(project);

    assertEquals(1, dirtyReferencedModels.size());
    assertTrue(dirtyReferencedModels.containsValue(projectSession));

    setSessionDirty(rootLibrarySession);
    dirtyReferencedModels = FilteringUtils.getDirtyReferencedModels(project);

    assertEquals(2, dirtyReferencedModels.size());
    assertTrue(dirtyReferencedModels.containsValue(projectSession));
    assertTrue(dirtyReferencedModels.containsValue(rootLibrarySession));

    setSessionDirty(childLibrarySession);
    dirtyReferencedModels = FilteringUtils.getDirtyReferencedModels(project);

    assertEquals(3, dirtyReferencedModels.size());
    assertTrue(dirtyReferencedModels.containsValue(projectSession));
    assertTrue(dirtyReferencedModels.containsValue(rootLibrarySession));
    assertTrue(dirtyReferencedModels.containsValue(childLibrarySession));
  }

  private void setSessionDirty(Session session) {
    Resource resource = session.getSessionResource();

    TransactionHelper.getExecutionManager(session).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        EList<EObject> resourceContents = resource.getContents();
        int lastElementIndex = resourceContents.size() - 1;
        EObject lastElement = resourceContents.remove(lastElementIndex);
        resourceContents.add(lastElementIndex, lastElement);
      }
    });

  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(PROJECT_NAME, ROOT_LIBRARY_NAME, CHILD_LIBRARY_NAME);
  }

}
