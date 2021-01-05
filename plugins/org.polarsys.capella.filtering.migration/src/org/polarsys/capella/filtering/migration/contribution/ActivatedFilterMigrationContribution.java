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
package org.polarsys.capella.filtering.migration.contribution;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.capella.filtering.tools.helpers.ViewpointHelper;
import org.polarsys.kitalpha.ad.metadata.metadata.Metadata;

/**
 * Removes filters that were added in previous versions, if the viewpoint is not active. </br>
 * For more details check: https://bugs.eclipse.org/bugs/show_bug.cgi?id=569618
 *
 */
public class ActivatedFilterMigrationContribution extends AbstractMigrationContribution {

  private static final String FILTER_TO_REMOVE_NAME = "VisibleDiagramFilteringCriteria";

  /**
   * Flag to signal if the viewpoint metadata should be cleaned. </br>
   */
  private boolean shouldCleanMetadata = false;

  @Override
  public void unaryMigrationExecute(EObject currentElement, MigrationContext context) {
    super.unaryMigrationExecute(currentElement, context);

    if (currentElement instanceof DSemanticDiagram && !ViewpointHelper.isViewpointActive(currentElement)) {
      DSemanticDiagram diagram = (DSemanticDiagram) currentElement;
      shouldCleanMetadata = true;

      diagram.getActivatedFilters().removeIf(filter -> FILTER_TO_REMOVE_NAME.equals(filter.getName()));
    }
  }

  @Override
  public void postMigrationExecute(ExecutionManager executionManager, ResourceSet resourceSet,
      MigrationContext context) {
    super.postMigrationExecute(executionManager, resourceSet, context);

    if (shouldCleanMetadata) {
      cleanMetadata(resourceSet);
      shouldCleanMetadata = false;
    }
  }

  @Override
  public void dispose(MigrationContext context) {
    super.dispose(context);
    shouldCleanMetadata = false;
  }

  /**
   * In the ViewpointMigrationContribution class, postMigrationExecute(ExecutionManager, ResourceSet, MigrationContext)
   * method we use the MetadataHelper.initMetadata(Resource) method to update the version of the viewpoints that are
   * referenced in the afm file.</br>
   * 
   * If the viewpoint is not referenced in the afm file, but elements originating from the viewpoint (such as the
   * filters we want to remove) are present, then a new reference towards the viewpoint is added in the afm file. </br>
   * 
   * A new reference towards the filtering add-on is added, because we removed the filters in the memory model, but the
   * memory model is not yet serialized.
   * 
   * Since we remove filters that were added IF AND ONLY IF the viewpoint is not currently activated. Once this is done
   * we do not want to add a new reference towards the add-on and thus reactivate it.
   */
  private void cleanMetadata(ResourceSet resourceSet) {
    Metadata metadata = getMetadata(resourceSet);
    if (metadata != null) {
      metadata.getViewpointReferences().removeIf(viewpoint -> ViewpointHelper.VIEWPOINT_ID.equals(viewpoint.getVpId()));
    }
  }

  // org.polarsys.kitalpha.ad.metadata.helpers.ViewpointMetadata.getMetadataStorage() is protected
  private Metadata getMetadata(ResourceSet resourceSet) {
    Optional<Resource> afmResource = resourceSet.getResources().stream() //
        .filter(r -> r.getURI().toString().endsWith("afm")) //
        .findFirst();

    if (afmResource.isPresent()) {
      Resource resource = afmResource.get();
      if (!resource.getContents().isEmpty()) {
        EObject root = resource.getContents().get(0);
        if (root instanceof Metadata) {
          return (Metadata) root;
        }
      }
    }
    return null;
  }

}
