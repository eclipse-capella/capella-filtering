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

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.filtering.AbstractFilteringResult;
import org.polarsys.capella.filtering.ComposedFilteringResult;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringResult;
import org.polarsys.capella.filtering.tools.FilteringToolsPlugin;
import org.polarsys.capella.filtering.tools.Messages;
import org.polarsys.capella.filtering.tools.preferences.FilteringPreferencesPage;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

/**
 * Product Line Extraction Action.
 */
public class FilteringExtractionAction implements IActionDelegate {

  private static final int DERIVATION_DO_NOT_SAVE_AND_CONTINUE = 0;
  private static final int DERIVATION_SAVE_AND_CONTINUE = 1;

  // Preferences
  private static final IEclipsePreferences PREFS = InstanceScope.INSTANCE
      .getNode(FilteringToolsPlugin.getDefault().getPluginId());

  // The selected filteringResult
  private AbstractFilteringResult filteringResult = null;

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(IAction action) {

    // Get the project from the selected filteringResult
    IProject project = FilteringUtils.getEclipseProject(filteringResult);
    Shell activeShell = PlatformUI.getWorkbench().getDisplay().getActiveShell();

    // Check that the project was found
    if (project == null) {
      // Problems found getting the project
      MessageDialog.openError(activeShell, Messages.FilteringExtractionAction_0, Messages.FilteringExtractionAction_1);
      return;
    }

    // Get domain project id
    Project capellaProject = CapellaProjectHelper.getProject(filteringResult);
    String domainId = capellaProject.getId();

    // Check domain project has no reference to library, otherwise, user
    // must check corresponding option in Filtering preference page
    if (!PREFS.getBoolean(FilteringPreferencesPage.APPLICATION_PROJECT_WITH_DIFFERENT_ID, false)
        && !FilteringUtils.getReferencedLibraries(capellaProject).isEmpty()) {
      MessageDialog.openError(activeShell, Messages.FilteringExtractionAction_0,
          Messages.FilteringExtractionAction_projectHasReferenceToLibrary);
      return;
    }

    // Check that the project contains a capella
    // This is important for CDO as the project does not contain the
    // semantic model
    try {
      if (FilteringUtils.getSemanticModels(project).isEmpty()) {
        MessageDialog.openError(activeShell, Messages.FilteringExtractionAction_0,
            Messages.FilteringExtractionAction_connectedProject);
        return;
      }
    } catch (CoreException exception) {
      return;
    }

    // Check the presence of any dirty referenced models.
    // If any exist, invite the user to save them or to continue without saving.
    Map<IModel, Session> dirtyReferencedModels = FilteringUtils.getDirtyReferencedModels(capellaProject);

    if (dirtyReferencedModels.isEmpty()) {
      doAction(filteringResult, project, domainId);
    } else {
      String dirtyModelNames = FilteringUtils.extractModelNames(dirtyReferencedModels.keySet());
      String dialogTitle = Messages.FilteringDirtyReferencedModels_dialog_title;
      String dialogMessage = NLS.bind(Messages.FilteringDirtyReferencedModels_dialog_message, dirtyModelNames);
      String saveOptionTitle = Messages.FilteringDirtyReferencedModels_dialog_continue_with_saving;
      String noSaveOptionTitle = Messages.FilteringDirtyReferencedModels_dialog_continue_without_saving;

      MessageDialog dialog = new MessageDialog(activeShell, dialogTitle, null, dialogMessage, MessageDialog.WARNING,
          new String[] { noSaveOptionTitle, saveOptionTitle }, 1);
      int result = dialog.open();

      switch (result) {

      case DERIVATION_SAVE_AND_CONTINUE:
        saveAndContinue(filteringResult, project, domainId, dirtyReferencedModels.values());
        break;

      case DERIVATION_DO_NOT_SAVE_AND_CONTINUE:
        doAction(filteringResult, project, domainId);
        break;

      default:
        break;
      }

    }
  }

  private void saveAndContinue(AbstractFilteringResult filteringResult, IProject project, String domainId,
      Collection<Session> dirtySessions) {
    IProgressMonitor progressMonitor = new NullProgressMonitor();

    for (Session dirtySession : dirtySessions) {
      dirtySession.save(progressMonitor);
    }

    doAction(filteringResult, project, domainId);
  }

  /**
   * Dispatches the 'Open wizard to select features and new project' action to the appropriate handler.
   * 
   * @param filteringResult
   * @param project
   * @param domainId
   */
  private void doAction(AbstractFilteringResult filteringResult, IProject project, String domainId) {
    if (filteringResult instanceof ComposedFilteringResult)
      doAction((ComposedFilteringResult) filteringResult, project, domainId);
    else if (filteringResult instanceof FilteringResult) {
      doAction((FilteringResult) filteringResult, project, domainId);
    }

  }

  private void doAction(FilteringResult filteringResult, IProject project, String domainId) {

    final IFilteringProjectWizard wizard = new FilteringProjectWizard(project, filteringResult);

    ((BasicNewResourceWizard) wizard).init(PlatformUI.getWorkbench(), StructuredSelection.EMPTY);

    Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();

    WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), (IWizard) wizard);

    if (Window.OK == dialog.open()) {

      // Get selected features and cloned project and start the Extraction
      // job

      List<FilteringCriterion> selectedFeatures = wizard.getSelectedFeatures();

      final IProject clonedProject = wizard.getResult();

      if (clonedProject != null) {

        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        ProgressMonitorDialog progressMonitorDialog = new ProgressMonitorDialog(shell);

        try {
          // We create the runnable
          FilteringExtractionJob job = new FilteringExtractionJob(project, clonedProject, selectedFeatures,
              filteringResult, domainId);

          // We open the dialog
          progressMonitorDialog.setBlockOnOpen(false);
          progressMonitorDialog.setCancelable(true);
          progressMonitorDialog.open();
          // Once it is open then the workspace runnable start
          // It is executed as workspaceRunnable to perform an atomic
          // resources changes
          // See FilteringExtractionJob comments
          workspace.run(job, progressMonitorDialog.getProgressMonitor());

        } catch (CoreException exception) {
          // Error performing derivation
          MessageDialog.openError(shell, Messages.FilteringExtractionAction_2, Messages.FilteringExtractionAction_3);
        } finally {
          // Close the progress monitor dialog
          progressMonitorDialog.close();
        }
      }
    }
  }

  private void doAction(ComposedFilteringResult filteringResult, IProject project, String domainId) {

    final IFilteringProjectWizard wizard = new ComposedFilteringProjectWizard(project, filteringResult);

    ((BasicNewResourceWizard) wizard).init(PlatformUI.getWorkbench(), StructuredSelection.EMPTY);

    Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();

    WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), (IWizard) wizard);

    if (Window.OK == dialog.open()) {

      // Get selected features and cloned project and start the Extraction
      // job

      List<FilteringCriterion> selectedFeatures = ((ComposedFilteringProjectWizard) wizard).getSelectedComposedResult()
          .computeFilteringCriterionSet().getFilteringCriteria();

      final IProject clonedProject = wizard.getResult();

      if (clonedProject != null) {

        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        ProgressMonitorDialog pmd = new ProgressMonitorDialog(shell);

        try {
          // We create the runnable
          ComposedFilteringExtractionJob job = new ComposedFilteringExtractionJob(project, clonedProject,
              selectedFeatures, filteringResult, domainId);

          // We open the dialog
          pmd.setBlockOnOpen(false);
          pmd.setCancelable(true);
          pmd.open();
          // Once it is open then the workspace runnable start
          // It is executed as workspaceRunnable to perform an atomic
          // resources changes
          // See FilteringExtractionJob comments
          workspace.run(job, pmd.getProgressMonitor());

        } catch (CoreException exception) {
          // Error performing derivation
          MessageDialog.openError(shell, Messages.FilteringExtractionAction_2, Messages.FilteringExtractionAction_3);
        } finally {
          // Close the progress monitor dialog
          pmd.close();
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    if (selection instanceof StructuredSelection) {
      filteringResult = (AbstractFilteringResult) ((StructuredSelection) selection).getFirstElement();
    }
  }
}
