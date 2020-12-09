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
package org.polarsys.capella.filtering.tools.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.ReloadingPolicy;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractNonDirtyingCommand;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.handler.helpers.CrossReferencerHelper;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.sirius.ui.actions.DesignerControlAction;
import org.polarsys.capella.core.sirius.ui.actions.DesignerControlAction.CapellaSiriusUncontrolCommand;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.filtering.AbstractFilteringResult;
import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringModel;
import org.polarsys.capella.filtering.tools.extract.FilteringExtractor;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

/**
 * TODO A workspace runnable is used instead of a Job because resources modification notifications caused several
 * exceptions in Capella Project Explorer and Properties views.
 */
public class ComposedFilteringExtractionJob implements IWorkspaceRunnable {

  String domainId;
  IProject clonedProject;
  IProject currentProject;
  EObject configuration;
  List<FilteringCriterion> selectedFeatures;
  ExecutionManager executionManager = null;
  TransactionalEditingDomain editingDomain = null;
  ArrayList<Resource> resourcesToDelete = new ArrayList<>();
  Collection<String> configurationDerivationNames;
  Collection<String> selectedFeaturesFullLabels;

  /**
   * @param name
   * @param domainId
   */
  public ComposedFilteringExtractionJob(IProject currentProject, IProject clonedProject,
      List<FilteringCriterion> selectedFeatures, AbstractFilteringResult filteringResult, String domainId) {
    this.domainId = domainId;
    this.currentProject = currentProject;
    this.clonedProject = clonedProject;
    this.selectedFeatures = selectedFeatures;

    computeSelectedFeatureFullLabels(selectedFeatures);

    computeDerivationElementFullLabels(filteringResult);

    this.configuration = filteringResult;
  }

  private void computeDerivationElementFullLabels(AbstractFilteringResult filteringResult) {
    Collection<EObject> configurationDerivation = FilteringUtils.computeDerivation(filteringResult,
        FilteringUtils.getSystemEngineering(filteringResult));
    this.configurationDerivationNames = configurationDerivation.parallelStream()
        .filter(elt -> elt instanceof ModelElement).map(modelElt -> ((ModelElement) modelElt).getFullLabel())
        .map(this::removeProjectAndSysEngPaths).collect(Collectors.toSet());
  }

  private void computeSelectedFeatureFullLabels(List<FilteringCriterion> selectedFeatures) {

    selectedFeaturesFullLabels = selectedFeatures.parallelStream().filter(elt -> elt instanceof ModelElement)
        .map(modelElt -> ((ModelElement) modelElt).getFullLabel()).map(this::removeProjectAndSysEngPaths)
        .collect(Collectors.toSet());
  }

  private String removeProjectAndSysEngPaths(String fullLabel) {
    return removeFirst2Segments(fullLabel);
  }

  private static String removeFirst2Segments(String fullLabel) {

    String separator = "/";
    if (fullLabel == null) {

      return null;
    }
    int indexOfThirdOccurenceOfSeparator = fullLabel.indexOf(separator,
        fullLabel.indexOf(separator, fullLabel.indexOf(separator) + 1) + 1);

    String result = fullLabel.substring(indexOfThirdOccurenceOfSeparator);

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(IProgressMonitor monitor) {
    try {

      SubMonitor subMonitor = SubMonitor.convert(monitor, Messages.FilteringExtractionJob_0, 12);
      subMonitor.setTaskName(Messages.FilteringExtractionJob_TaskName);
      subMonitor.subTask(Messages.FilteringExtractionJob_0 + currentProject.getName());

      subMonitor.split(1);
      FilteringExtractor extractor = new FilteringExtractor(clonedProject, domainId);

      /**
       * A new eclipse project has been created (copy nature from the cloned project)
       */
      extractor.cloneProjectNature(currentProject);

      subMonitor.split(1);
      subMonitor.subTask(Messages.FilteringExtractionJob_1);

      /**
       * We copy all files including '.melodymodeller', '.aird', '.melodyfragment', '.airdfragment' and '.afm' files
       * into the newly created eclipse project.
       */
      Map<String, String> oldReferenceToNewReference = extractor.cloneModels(currentProject);

      subMonitor.split(1);
      subMonitor.subTask(Messages.FilteringExtractionJob_2);

      /**
       * Then we update the reference from the 'aird' model to the 'melodymodeller' and 'afm' models, because the models
       * filename have changed.
       */
      extractor.updateReferences(oldReferenceToNewReference);

      subMonitor.split(1);

      /**
       * The cloned model is opened in a Sirius session
       */
      // Aird Models
      List<IFile> airdModels = FilteringUtils.getAirdModels(clonedProject);
      subMonitor.setWorkRemaining(airdModels.size() * 20);

      for (IFile aird : airdModels) {
        subMonitor.subTask(Messages.FilteringExtractionJob_3);

        Session session = FilteringUtils.openSession(aird, subMonitor.split(4));

        // This will prevent exceptions for external modifications and
        // deletions.
        session.setReloadingPolicy(new ReloadingPolicy() {
          @Override
          public List<Action> getActions(Session session, Resource resource, ResourceStatus newStatus) {
            return new ArrayList<>();
          }
        });

        subMonitor.subTask(Messages.FilteringExtractionJob_4);
        subMonitor.split(2);

        editingDomain = session.getTransactionalEditingDomain();
        executionManager = ExecutionManagerRegistry.getInstance().getExecutionManager(editingDomain);

        // Semantic models
        Collection<Resource> resources = session.getSemanticResources();
        List<Resource> referencedLibraries = FilteringUtils.getReferencedLibraries(session);
        for (final Resource semanticResource : resources) {
          if (referencedLibraries.contains(semanticResource)) {
            continue;
          }

          /**
           * Builds a table to establish a mapping between the features from the initial project and the features from
           * the cloned project
           */
          final Map<FilteringCriterion, FilteringCriterion> mappingTable = new HashMap<>();
          // If session is not open semanticResource.getAllContents
          // returns empty list
          // We stop if we found the project AND the system
          // engineering
          boolean projectFound = false;
          boolean systemEngineeringFound = false;
          List<FilteringModel> derivedFilteringModels = null;
          List<FilteringCriterion> newFeatures = Collections.emptyList();
          IModel currentModel = null;
          for (Iterator<EObject> iterator = semanticResource.getAllContents(); iterator.hasNext();) {
            final EObject current = iterator.next();

            /**
             * Change SystemEngineering name
             */
            if (current instanceof SystemEngineering) {
              executionManager.execute(new AbstractNonDirtyingCommand() {
                @Override
                public void run() {
                  ((SystemEngineering) current).setName(clonedProject.getName());
                }
              });
              systemEngineeringFound = true;
            }

            if (current instanceof Project) {
              // Init the current model
              currentModel = ILibraryManager.INSTANCE.getModel(current);
              /**
               * Change Project name
               */
              executionManager.execute(new AbstractNonDirtyingCommand() {
                @Override
                public void run() {
                  ((Project) current).setName(clonedProject.getName());
                }
              });

              List<FilteringModel> rootSemanticObjectFilteringModels = FilteringUtils.getFilteringModels(configuration,
                  true);
              derivedFilteringModels = FilteringUtils.getFilteringModels(current, true);
              if (FilteringUtils.hasFilteringFeatures(rootSemanticObjectFilteringModels)
                  && FilteringUtils.hasFilteringFeatures(derivedFilteringModels)) {

                List<FilteringCriterion> oldFeatures = FilteringUtils
                    .getOwnedFilteringCriteria(rootSemanticObjectFilteringModels);

                newFeatures = FilteringUtils.getOwnedFilteringCriteria(derivedFilteringModels);

                // associate new features of cloned project with
                // old features of initial project
                mapFeatures(newFeatures, oldFeatures, mappingTable);
              }

              projectFound = true;
            }

            if (projectFound && systemEngineeringFound) {
              break;
            }
          }

          subMonitor.subTask(Messages.FilteringExtractionJob_5);
          subMonitor.split(1);

          /**
           * All the elements tagged with a wrong feature and belonging to the current model are deleted. This is to
           * prevent from deleting semantic objects located in referenced libraries.
           */
          // The elements that are going to be completely removed
          Set<EObject> elementsToDelete = new HashSet<>();

          // The elements that are going to be uncontrol before removed
          List<EObject> elementsToUncontrol = new ArrayList<>();

          if (derivedFilteringModels != null) {

            for (Object feature : newFeatures) {

              final FilteringCriterion filteringCriterion = (FilteringCriterion) feature;

              List<EObject> referencingElements = CrossReferencerHelper.getReferencingElements(filteringCriterion);

              for (EObject eObject : referencingElements) {

                EObject eContainer = eObject.eContainer();

                if (eObject instanceof AssociatedFilteringCriterionSet && eContainer instanceof CapellaElement) {

                  final CapellaElement capellaElement = (CapellaElement) eContainer;
                  IModel model = ILibraryManager.INSTANCE.getModel(capellaElement);

                  if (currentModel != null && currentModel.equals(model)

                      && !isAlreadyContained(elementsToDelete, capellaElement)) {
                    // We delete the element only if it
                    // doesn't associated with a checked
                    // criterion
                    if (!isPartOfDerivation(capellaElement)) {
                      // We add it to the delete set
                      elementsToDelete.add(capellaElement);
                    }

                    if (!selectedFeatures.contains(mappingTable.get(filteringCriterion))) {
                      // We remove unnecessary associated
                      // features even if we keep it
                      executionManager.execute(new AbstractNonDirtyingCommand() {
                        @Override
                        public void run() {
                          FilteringUtils.removeAssociatedCriteria(capellaElement,
                              Collections.singletonList(filteringCriterion));
                        }
                      });

                    }

                  }
                }
              }

              Set<EObject> impactedElements = FilteringUtils.getImpactedElements(elementsToDelete);
              for (EObject element : impactedElements) {
                IModel model = ILibraryManager.INSTANCE.getModel(element);
                if (currentModel != null && currentModel.equals(model)
                /*
                 * && !isAlreadyContained(elementsToDelete, element)
                 */) {
                  elementsToDelete.add(element);
                }
              }

              for (EObject element : elementsToDelete) {
                if (editingDomain.isControllable(element) && AdapterFactoryEditingDomain.isControlled(element)
                    && !elementsToUncontrol.contains(element)) {
                  elementsToUncontrol.add(element);
                }
              }
            }
          }

          subMonitor.subTask(Messages.FilteringExtractionJob_6);
          subMonitor.split(1);

          /**
           * Uncontrol elements to be deleted
           */
          /*
           * TODO See CapellaDeleteCommand execute() method fix_me comments. Cannot delete fragmented elements.
           */
          for (EObject eObject : elementsToUncontrol) {
            performUncontrol(eObject);
            // Cancel control
            if (monitor.isCanceled()) {
              throw new OperationCanceledException();
            }
          }

          /**
           * Delete the model elements to be deleted
           */
          elementsToDelete = new HashSet<>(EcoreUtil.filterDescendants(elementsToDelete));
          if (!elementsToDelete.isEmpty()) {

            CapellaDeleteCommand command = new CapellaDeleteCommand(executionManager, elementsToDelete, true, false,
                true) {

              @Override
              public Collection<?> getAffectedObjects() {

                Set<?> allElementsToDelete = getAllElementsToDelete();
                if (allElementsToDelete == null)
                  return Collections.emptySet();
                return allElementsToDelete;
              }
            };

            if (command.canExecute()) {
              // execute the command
              doExecuteNonDirtyingCommand(command);
            }
          }
        }

        subMonitor.subTask(Messages.FilteringExtractionJob_8);
        subMonitor.split(8);

        // Refresh all representations
        FilteringUtils.refreshAllRepresentations(session, executionManager);

        subMonitor.subTask(Messages.FilteringExtractionJob_9);

        // Save and close session
        session.save(subMonitor.split(3));
        SessionHelper.closeUiSessions(Collections.singletonList(clonedProject));

        // Refresh workspace
        clonedProject.refreshLocal(IResource.DEPTH_INFINITE, subMonitor.split(1));
      }

    } catch (CoreException | IOException e) {
      e.printStackTrace();

    } finally {
      // Finally monitor done
      monitor.done();
    }
  }

  protected void doExecuteNonDirtyingCommand(final Command realCommand) {

    executionManager.execute(new AbstractNonDirtyingCommand() {

      @Override
      public void run() {
        realCommand.execute();
      }

      @Override
      public Collection<?> getAffectedObjects() {
        Collection<?> affectedObjects = realCommand.getAffectedObjects();
        if (affectedObjects == null)
          return Collections.emptyList();
        return affectedObjects;
      }
    });

  }

  /**
   * @param elements
   * @param current
   */
  boolean isAlreadyContained(Set<EObject> elements, EObject current) {
    if (!elements.contains(current)) {
      for (EObject elts : elements) {
        for (Iterator<EObject> iterator = elts.eAllContents(); iterator.hasNext();) {
          EObject elt = iterator.next();
          if (elt.equals(current)) {
            return true;
          }
        }
      }
      return false;
    }
    return true;
  }

  /**
   * associate new features of cloned project with old features of initial project in mappingTable
   * 
   * @param newFeatures
   * @param oldFeatures
   * @param mappingTable
   */
  private void mapFeatures(List<FilteringCriterion> newFeatures, List<FilteringCriterion> oldFeatures,
      Map<FilteringCriterion, FilteringCriterion> mappingTable) {

    assert (oldFeatures.size() == newFeatures.size());

    for (Object newElement : newFeatures) {
      for (Object oldElement : oldFeatures) {
        if (newElement instanceof FilteringCriterion && oldElement instanceof FilteringCriterion) {

          FilteringCriterion newFeature = (FilteringCriterion) newElement;
          FilteringCriterion oldFeature = (FilteringCriterion) oldElement;

          if (newFeature.getName().equals(oldFeature.getName())) {
            mappingTable.put(newFeature, oldFeature);
          }
        }
      }
    }
  }

  /**
   * @param elem
   * @param mappingTable
   * @return
   */
  private boolean isPartOfDerivation(EObject elem) {
    if (elem instanceof ModelElement) {
      String fullLabel = ((ModelElement) elem).getFullLabel();
      return configurationDerivationNames.contains(removeFirst2Segments(fullLabel));
    }
    return false;
  }

  /**
   * TODO Copied From CapellaViewpointUncontrolHandler without UI and modifications.
   * 
   * @param semanticRoot
   */
  public void performUncontrol(final EObject semanticRoot) {
    DesignerControlAction dca = new DesignerControlAction();
    CapellaSiriusUncontrolCommand vuc = dca.new CapellaSiriusUncontrolCommand(semanticRoot, true);

    // Disable resourceSetSync notification to avoid unload / reload of
    // fragmented resources during the unfragmentation.
    setResourceSetSyncNotificationEnabled(false);
    try {
      doExecuteNonDirtyingCommand(vuc);
    } finally {
      Display.getDefault().asyncExec(new Runnable() {
        @Override
        public void run() {
          // Re-enable the notification.
          setResourceSetSyncNotificationEnabled(true);
        }
      });
    }
  }

  /**
   * TODO Copied from DesignerControlAction Set whether or not the resourceSetSync related to current TED emits
   * notifications to its clients.
   * 
   * @param notificationEnabled
   */
  public void setResourceSetSyncNotificationEnabled(boolean notificationEnabled) {
    if (editingDomain != null) {
      ResourceSetSync.getOrInstallResourceSetSync(editingDomain).setNotificationIsRequired(notificationEnabled);
    }
  }
}
