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

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryLabelProvider;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.CapellaProjectContentsLocationArea;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.ProjectContentsLocationArea;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.WizardNewProjectCreationPage;
import org.polarsys.capella.core.platform.sirius.ui.project.operations.ProjectSessionCreationHelper;
import org.polarsys.capella.core.platform.sirius.ui.project.operations.SessionCreationHelper;
import org.polarsys.capella.filtering.ComposedFilteringResult;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.IntersectionFilteringResultSet;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;
import org.polarsys.capella.filtering.tools.utils.ui.FilteringResultsContentProvider;

class ComposedFilteringProjectWizard extends BasicNewResourceWizard implements IFilteringProjectWizard {

  protected IProject currentProject;
  protected IProject clonedProject;
  protected ComposedFilteringResult selectedComposedResult;

  private ContainerCheckedTreeViewer viewer;
  private WizardNewProjectCreationPage localProjectDescriptionPage;

  private static final Logger logger = ReportManagerRegistry.getInstance()
      .subscribe(IReportManagerDefaultComponents.UI);

  /**
   * @param project
   * @param filteringResult
   */
  ComposedFilteringProjectWizard(IProject project, ComposedFilteringResult result) {
    currentProject = project;
    this.selectedComposedResult = result;
  }

  /**
   * @return
   */
  public IProject getResult() {
    return clonedProject;
  }

  public ComposedFilteringResult getSelectedComposedResult() {
    return selectedComposedResult;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addPages() {
    localProjectDescriptionPage = createLocalProjectDescriptionPage();
    addPage(localProjectDescriptionPage);
  }

  /**
   * In case of a composed filtering result we do not need to use criteria to compute derivation
   * 
   * @return
   */
  public List<FilteringCriterion> getSelectedFeatures() {
    return Collections.emptyList();
  }

  /**
   * {@inheritDoc}
   */

  protected WizardNewProjectCreationPage createLocalProjectDescriptionPage() {
    WizardNewProjectCreationPage mainPage = new WizardNewProjectCreationPage("MelodyProjNewPage") {//$NON-NLS-1$
      /**
       * Overrided to use the Capella one as in the New Capella Project wizard {@inheritDoc}
       */
      @Override
      protected ProjectContentsLocationArea handleDefaultProjectLocation(Composite parent) {
        return new CapellaProjectContentsLocationArea(getErrorReporter(), parent);
      }

      /**
       * Overrided with two objectives. Remove the existing options that are not important in this context and also
       * contribute our information read only viewer of selected criteria. {@inheritDoc}
       */
      @Override
      protected void createProjectApproachGroup(Composite parent) {
        Group featureSelectionGroup = new Group(parent, SWT.NONE);
        featureSelectionGroup.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
        featureSelectionGroup.setLayout(new GridLayout());
        featureSelectionGroup.setText(Messages.ComposedFilteringProjectWizard_ComposedResultelectionGroup_title); // $NON-NLS-1$

        createTreeViewer(featureSelectionGroup);
      }

      @Override
      // TODO Can be highly improved. We override this because the
      // extended MA class does not expect that the project
      // name has an initial value and we get a
      // NPE. Not easy to extend it. Try for example orchestra
      // DefaultProjectLocationProvider with a path that does not
      // exist yet to catch exception.
      protected boolean validatePage() {
        boolean validationResult = false;
        try {
          validationResult = super.validatePage();
        } catch (Exception e) {
          validationResult = false;
        }
        return validationResult;
      }
    };

    // Set the initial project name with the filteringResult name
    // Call the setInitialProjectName to take into account a contribution
    // through defaultProjectLocationProvider
    // extension-point.
    String newProjectName = selectedComposedResult.getName();
    mainPage.setInitialProjectName(newProjectName);

    // If project already exists get a non existent project name
    int i = 2;
    while (FilteringUtils.resourceExists(mainPage.getProjectHandle())) {
      newProjectName = selectedComposedResult.getName() + "_" + i; //$NON-NLS-1$
      mainPage.setInitialProjectName(newProjectName);
      i++;
    }

    mainPage.setDescription(Messages.FilteringProjectWizard_description); // $NON-NLS-1$
    mainPage.setTitle(Messages.FilteringProjectWizard_title); // $NON-NLS-1$
    return mainPage;
  }

  /**
   * @param featureSelectionGroup
   */
  protected void createTreeViewer(Group featureSelectionGroup) {

    viewer = new ContainerCheckedTreeViewer(featureSelectionGroup, SWT.BORDER);
    Tree tree = viewer.getTree();
    viewer.setAutoExpandLevel(TreeViewer.ALL_LEVELS);
    tree.setEnabled(false);
    tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

    // TODO Improve disabling this read only TableViewer.
    // _viewer.getControl().setEnabled(false); is ok but when the list of
    // elements is long it disables also the scroll.
    // SWT.READ_ONLY seems also not to be enough.
    // For the moment we do a fake disable changing background color and
    // overriding selection
    // Another solution is to use icons similar to checkboxes instead of
    // checkboxes. Maybe this way users don't think
    // that they can click on them.
    viewer.getTree().setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));

    viewer.setContentProvider(new FilteringResultsContentProvider());

    Session session = SessionManager.INSTANCE.getSession(selectedComposedResult);
    TransactionalEditingDomain transactionalEditingDomain = session.getTransactionalEditingDomain();
    TransactionalAdapterFactoryLabelProvider labelProvider = new TransactionalAdapterFactoryLabelProvider(
        transactionalEditingDomain, ((AdapterFactoryEditingDomain) transactionalEditingDomain).getAdapterFactory());

    viewer.setLabelProvider(labelProvider);

    // Alphabetic ordering
    viewer.setComparator(new ViewerComparator());
    viewer.setInput(selectedComposedResult);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean performFinish() {

    try {
      getContainer().run(false, false, new IRunnableWithProgress() {
        /**
         * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
         */
        @Override
        @SuppressWarnings("synthetic-access")
        public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

          // Get a project description.
          IPath defaultPath = Platform.getLocation();
          IPath newPath = localProjectDescriptionPage.getLocationPath();
          if (defaultPath.equals(newPath)) {
            newPath = null;
          }

          List<IProject> referencedProjects = Collections.emptyList();
          SessionCreationHelper helper = new ProjectSessionCreationHelper(true, true,
              ProjectApproach.SingletonComponents);

          clonedProject = helper.createNewEclipseProject(localProjectDescriptionPage.getProjectName(), newPath,
              referencedProjects, monitor);

        }
      });
    } catch (InvocationTargetException | InterruptedException exception) {
      logger.error(exception);
      return false;
    }

    if (!FilteringUtils.resourceExists(clonedProject)) {
      clonedProject = null;
      return false;
    }

    return true;

  }

}