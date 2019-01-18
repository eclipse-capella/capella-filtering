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
package org.polarsys.capella.filtering.tools.actions;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.filtering.AbstractFilteringResult;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringResult;
import org.polarsys.capella.filtering.tools.Messages;
import org.polarsys.capella.filtering.tools.FilteringToolsPlugin;
import org.polarsys.capella.filtering.tools.preferences.FilteringPreferencesPage;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

/**
 * Product Line Extraction Action.
 */
public class FilteringExtractionAction implements IActionDelegate {

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

    // Check that the project was found
    if (project == null) {
      // Problems found getting the project
      MessageDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(),
          Messages.FilteringExtractionAction_0, Messages.FilteringExtractionAction_1);
      return;
    }

    // Get domain project id
    Project domainMelodyProject = CapellaProjectHelper.getProject(filteringResult);
    String domainId = domainMelodyProject.getId();

    // Check domain project has no reference to library, otherwise, user
    // must check corresponding option in Variability preference page
    if (!PREFS.getBoolean(FilteringPreferencesPage.APPLICATION_PROJECT_WITH_DIFFERENT_ID, false)
        && !FilteringUtils.getReferencedLibraries(domainMelodyProject).isEmpty()) {
      MessageDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(),
          Messages.FilteringExtractionAction_0, Messages.FilteringExtractionAction_projectHasReferenceToLibrary);
      return;
    }

    // Check that the project contains a melodymodeller
    // This is important for CDO as the project does not contain the
    // semantic model
    try {
      if (FilteringUtils.getSemanticModels(project).isEmpty()) {
        MessageDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(),
            Messages.FilteringExtractionAction_0, Messages.FilteringExtractionAction_melodymodellerFileNotFound);
        return;
      }
    } catch (CoreException exception) {
      return;
    }

    // Open wizard to select features and new project name
    final FilteringProjectWizard wizard = new FilteringProjectWizard(project, filteringResult);
    wizard.init(PlatformUI.getWorkbench(), StructuredSelection.EMPTY);
    Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
    WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), wizard);
    if (Window.OK == dialog.open()) {
      // Get selected features and cloned project and start the Extraction
      // job
      final List<FilteringCriterion> selectedFeatures = wizard.getSelectedFeatures();
      final IProject clonedProject = wizard.getResult();
      if (clonedProject != null) {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        ProgressMonitorDialog pmd = new ProgressMonitorDialog(shell);
        try {
          // We create the runnable
          FilteringExtractionJob job = new FilteringExtractionJob(project, clonedProject, selectedFeatures,
              filteringResult, domainId);
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
