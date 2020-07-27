/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.ProjectContentsLocationArea;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.WizardNewProjectCreationPage;
import org.polarsys.capella.core.platform.sirius.ui.project.operations.ProjectSessionCreationHelper;
import org.polarsys.capella.core.platform.sirius.ui.project.operations.SessionCreationHelper;
import org.polarsys.capella.filtering.AbstractFilteringResult;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringModel;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

class FilteringProjectWizard extends BasicNewResourceWizard implements IFilteringProjectWizard {

  protected IProject currentProject;
  protected IProject clonedProject;
  protected AbstractFilteringResult filteringResult;
  protected List<FilteringCriterion> selectedFeatures;

  private CheckboxTableViewer viewer;
  private WizardNewProjectCreationPage localProjectDescriptionPage;

  private static final Logger logger = ReportManagerRegistry.getInstance()
      .subscribe(IReportManagerDefaultComponents.UI);

  /**
   * @param project
   * @param filteringResult
   */
  FilteringProjectWizard(IProject project, AbstractFilteringResult result) {
    currentProject = project;
    this.filteringResult = result;
  }

  /**
   * @return
   */
  public IProject getResult() {
    return clonedProject;
  }

  /**
   * @return
   */
  public List<FilteringCriterion> getSelectedFeatures() {
    return selectedFeatures;
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
   * {@inheritDoc}
   */

  protected WizardNewProjectCreationPage createLocalProjectDescriptionPage() {
    WizardNewProjectCreationPage mainPage = new WizardNewProjectCreationPage("MelodyProjNewPage") {//$NON-NLS-1$
      /**
       * Overrided to use the Capella one as in the New Capella Project wizard {@inheritDoc}
       */
      @Override
      protected ProjectContentsLocationArea handleDefaultProjectLocation(Composite parent) {
        return new FilteringProjectContentsLocationArea(getErrorReporter(), parent);
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
        featureSelectionGroup.setText(Messages.FilteringProjectWizard_CriteriaelectionGroup_title); // $NON-NLS-1$

        createCheckboxTableViewer(featureSelectionGroup);
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
    String newProjectName = filteringResult.getName();
    mainPage.setInitialProjectName(newProjectName);

    // If project already exists get a non existent project name
    int i = 2;
    while (FilteringUtils.resourceExists(mainPage.getProjectHandle())) {
      newProjectName = filteringResult.getName() + "_" + i; //$NON-NLS-1$
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
  protected void createCheckboxTableViewer(Group featureSelectionGroup) {

    viewer = CheckboxTableViewer.newCheckList(featureSelectionGroup, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);

    // TODO Improve disabling this read only TableViewer.
    // _viewer.getControl().setEnabled(false); is ok but when the list of
    // elements is long it disables also the scroll.
    // SWT.READ_ONLY seems also not to be enough.
    // For the moment we do a fake disable changing background color and
    // overriding selection
    // Another solution is to use icons similar to checkboxes instead of
    // checkboxes. Maybe this way users don't think
    // that they can click on them.
    viewer.getTable().setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
    viewer.getTable().addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        if (e.item instanceof TableItem) {
          TableItem tableItem = (TableItem) e.item;
          if ((e.item.getData() != null) && (e.item.getData() instanceof FilteringCriterion)) {
            tableItem
                .setChecked(FilteringUtils.isCriterionInResult(filteringResult, (FilteringCriterion) e.item.getData()));
          }
        }
      }
    });

    viewer.setContentProvider(new ArrayContentProvider() {
      /**
       * {@inheritDoc}
       */
      @Override
      public Object[] getElements(Object inputElement) {
        if (inputElement instanceof EObject) {
          // Show all the features. The label provider will include
          // the checked states
          List<FilteringModel> filteringModels = FilteringUtils.getFilteringModels((EObject) inputElement, true);
          return FilteringUtils.getOwnedFilteringCriteria(filteringModels).toArray();
        }
        return super.getElements(inputElement);
      }
    });
    viewer.setLabelProvider(new LabelProvider() {
      /**
       * {@inheritDoc}
       */
      @Override
      public String getText(Object element) {
        if (element instanceof NamedElement) {
          return FilteringUtils.formatFilteringItemName((NamedElement) element);
        }
        return super.getText(element);
      }
    });
    viewer.setCheckStateProvider(new ICheckStateProvider() {
      @Override
      public boolean isGrayed(Object element) {
        return false;
      }

      @Override
      public boolean isChecked(Object element) {
        // Check the features selected in the filteringResult
        if (filteringResult == null)
          return false;

        FilteringCriterionSet criterionSet = filteringResult.computeFilteringCriterionSet();

        if (criterionSet != null && criterionSet.getFilteringCriteria() != null) {
          return criterionSet.getFilteringCriteria().contains(element);
        }
        return false;
      }
    });

    // Alphabetic ordering
    viewer.setComparator(new ViewerComparator());
    viewer.setInput(filteringResult);
    viewer.getTable().setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean performFinish() {

    // We use the viewer information to get the selected features...
    selectedFeatures = new ArrayList<>();
    for (Object obj : viewer.getCheckedElements()) {
      if (obj instanceof FilteringCriterion) {
        selectedFeatures.add((FilteringCriterion) obj);
      }
    }

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