package org.polarsys.capella.filtering.tests.ju.tests.misc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.resources.IProject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.polarsys.capella.filtering.tools.helpers.ViewpointHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.migration.ju.helpers.MigrationHelper;

public class ActivatedFilterMigrationTest extends BasicTestCase {

  private static final String FILTER_TO_REMOVE_NAME = "VisibleDiagramFilteringCriteria";

  private static final List<String> MODELS_WITH_VIEWPOINT_ACTIVATED = Arrays.asList("library-viewpoint-activated",
      "model-viewpoint-activated");

  private static final List<String> MODELS_WITH_VIEWPOINT_DEACTIVATED = Arrays.asList("library-viewpoint-deactivated",
      "model-viewpoint-deactivated");

  @Override
  public void test() throws Exception {
    assertValidModelMigration(MODELS_WITH_VIEWPOINT_ACTIVATED, true);
    assertValidModelMigration(MODELS_WITH_VIEWPOINT_DEACTIVATED, false);
  }

  protected void assertValidModelMigration(List<String> modelNames, boolean viewpointWasActivated) {

    for (String modelName : modelNames) {
      IProject project = IResourceHelpers.getEclipseProjectInWorkspace(modelName);

      MigrationHelper.migrateProject(project);

      Session session = getSessionForTestModel(modelName);
      assertNotNull(session);

      List<DSemanticDiagram> diagrams = DialectManager.INSTANCE.getAllRepresentations(session).stream(). //
          filter(DSemanticDiagram.class::isInstance). //
          map(DSemanticDiagram.class::cast). //
          collect(Collectors.toList());

      assertFalse(diagrams.isEmpty());

      // assert that the viewpoint activation status is preserved after migration
      assertEquals(viewpointWasActivated, ViewpointHelper.isViewpointActive(diagrams.get(0)));

      for (DSemanticDiagram diagram : diagrams) {
        Optional<FilterDescription> visibleFilter = diagram.getActivatedFilters().stream() //
            .filter(f -> FILTER_TO_REMOVE_NAME.equals(f.getName())) //
            .findAny();

        assertEquals(visibleFilter.isPresent(), viewpointWasActivated);
      }

      GuiActions.closeSession(session);
    }

  }

  @Override
  public List<String> getRequiredTestModels() {
    return Stream.concat(MODELS_WITH_VIEWPOINT_ACTIVATED.stream(), MODELS_WITH_VIEWPOINT_DEACTIVATED.stream())
        .collect(Collectors.toList());
  }

}
