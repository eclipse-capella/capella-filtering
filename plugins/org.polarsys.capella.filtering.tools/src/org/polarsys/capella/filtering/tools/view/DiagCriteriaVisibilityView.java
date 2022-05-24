/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.filtering.tools.view;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.filtering.AbstractFilteringResult;
import org.polarsys.capella.filtering.ComposedFilteringResult;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringFactory;
import org.polarsys.capella.filtering.FilteringResult;
import org.polarsys.capella.filtering.tools.FilteringToolsPlugin;
import org.polarsys.capella.filtering.tools.IImageKeys;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;
import org.polarsys.capella.filtering.tools.utils.ui.CriteriaContentProvider;
import org.polarsys.capella.filtering.tools.utils.ui.FilteringResultsContentProvider;

public class DiagCriteriaVisibilityView extends ViewPart implements ISelectionListener {

  private static final String MESSAGE = "Diagrams reflect the filtering result";

  private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

  ICheckStateListener viewerCheckStateListener;

  ContainerCheckedTreeViewer treeViewer;

  ITreeContentProvider contentProvider = new CriteriaContentProvider();

  private Project mainProject;
  private Collection<Project> projects = null;

  /**
   * Collection of elements currently checked by user.
   */
  private Collection<Object> checkedElements = null;
  private Collection<Object> uncheckedElements = null;
  private Collection<Object> undefinedElements = null;
  private Composite selectionButtonComposite;
  protected List<AbstractFilteringResult> filteringResults;

  private Button unchekAllButton;
  private Button checkAllButton;
  private Button refreshButton;

  private Combo filteringResultCombo;
  private Set<Control> allViewControls;
  private Set<Control> viewSubControls;
  private Label modifiedResultLabel;
  private Button enableCheckBtn;
  private Composite topControlsComposite;
  private Composite buttonComposite;

  public DiagCriteriaVisibilityView() {
    checkedElements = new HashSet<>();
    uncheckedElements = new HashSet<>();
    undefinedElements = new HashSet<>();
  }

  public Collection<Object> getCheckedElements() {
    return checkedElements;
  }

  public void setCheckedElements(Collection<Object> checkedElements) {
    this.checkedElements = checkedElements;
  }

  @Override
  public void createPartControl(Composite parent) {

    GridLayout glParent = new GridLayout(1, false);
    glParent.marginWidth = 2;
    glParent.horizontalSpacing = 2;
    glParent.verticalSpacing = 1;
    glParent.marginHeight = 1;
    parent.setLayout(glParent);

    // Message Area
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, true, false, 1, 1));
    composite.setLayout(new GridLayout(1, false));

    topControlsComposite = new Composite(composite, SWT.NONE);
    GridLayout glTopControlsComposite = new GridLayout(2, false);
    glTopControlsComposite.verticalSpacing = 2;
    topControlsComposite.setLayout(glTopControlsComposite);
    topControlsComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

    formToolkit.createLabel(topControlsComposite, MESSAGE, SWT.SHADOW_NONE);

    enableCheckBtn = new Button(topControlsComposite, SWT.CHECK);
    formToolkit.adapt(enableCheckBtn, true, true);

    enableCheckBtn.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        Button btn = (Button) event.getSource();
        boolean enabled = btn.getSelection();
        FilteringToolsPlugin.getGlobalFilteringCache().setEnabled(mainProject, enabled);
        viewSubControls.forEach(c -> c.setEnabled(enabled));
        Object input = treeViewer.getInput();
        if (input instanceof ComposedFilteringResult) {
          treeViewer.getTree().setEnabled(false);
        }
        updateCheckUncheckAllEnableState();
        refreshActiveDiagram();
      }
    });

    // Button and message area
    viewSubControls = new HashSet<Control>();
    selectionButtonComposite = createSelectionButtonsAfterMessageArea(parent);
    new Label(buttonComposite, SWT.NONE);
    new Label(buttonComposite, SWT.NONE);
    new Label(buttonComposite, SWT.NONE);
    allViewControls = new HashSet<>(viewSubControls);
    allViewControls.add(enableCheckBtn);

    // Tree Viewer Area
    treeViewer = new ContainerCheckedTreeViewer(parent, SWT.BORDER);
    Tree tree = treeViewer.getTree();
    tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
    // Set alphabetic sorter
    treeViewer.setComparator(new ViewerComparator());

    treeViewer.setContentProvider(contentProvider);
    treeViewer.setAutoExpandLevel(TreeViewer.ALL_LEVELS);
    treeViewer.setInput(treeViewer.getInput());

    if (null == viewerCheckStateListener) {
      viewerCheckStateListener = createCheckStateListener();
    }
    // Register listener
    treeViewer.addCheckStateListener(viewerCheckStateListener);
    viewSubControls.add(treeViewer.getTree());

    // register this view as selection listener
    getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(this);

    createActions();
  }

  /**
   * Creates selection buttons.
   * 
   * @param composite
   *          the parent composite
   * @return the selection buttons composite
   */
  protected Composite createSelectionButtonsAfterMessageArea(Composite composite) {
    GridData data = new GridData(GridData.END | GridData.GRAB_HORIZONTAL);
    data.grabExcessHorizontalSpace = true;
    composite.setData(data);

    buttonComposite = new Composite(composite, SWT.LEFT);
    buttonComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

    GridLayout layout = new GridLayout();
    layout.marginLeft = 2;
    layout.marginWidth = 2;
    layout.horizontalSpacing = 4;
    layout.marginHeight = 1;
    layout.verticalSpacing = 1;
    layout.numColumns = 7;
    buttonComposite.setLayout(layout);
    buttonComposite.setFont(composite.getFont());

    // Disabled if no options
    boolean enabledButtons = projects != null && !projects.isEmpty();

    createCheckAllButton(buttonComposite, enabledButtons);
    createUnchekAllButton(buttonComposite, enabledButtons);

    createRefreshButton(buttonComposite, enabledButtons);

    filteringResultCombo = new Combo(buttonComposite, SWT.READ_ONLY);
    filteringResultCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

    filteringResultCombo.addListener(SWT.Selection, createFilteringResultComboListener());

    formToolkit.createLabel(buttonComposite, "", SWT.HORIZONTAL | SWT.SHADOW_NONE);
    modifiedResultLabel = formToolkit.createLabel(buttonComposite, "", SWT.HORIZONTAL | SWT.SHADOW_NONE);

    Font result = JFaceResources.getFontRegistry().getBold(JFaceResources.getFontRegistry().defaultFont().getFontData()[0].getName());
    modifiedResultLabel.setFont(result);
    modifiedResultLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));

    viewSubControls.addAll(
        Arrays.asList(unchekAllButton, checkAllButton, refreshButton, modifiedResultLabel, filteringResultCombo));

    return buttonComposite;
  }

  private void createUnchekAllButton(Composite parent, boolean enabledButtons) {
    unchekAllButton = addButton(parent, "Uncheck All",
        ImageDescriptor.createFromFile(FilteringToolsPlugin.class, "/icons/uncheckAll.gif").createImage(), //$NON-NLS-1$
        new SelectionAdapter() {
          @Override
          public void widgetSelected(SelectionEvent e) {
            Object treeViewerInput = treeViewer.getInput();
            for (Object element : contentProvider.getElements(treeViewerInput)) {
              setTreeChecked(element, false);
              undefinedElements.remove(element);
            }
            // Clear filtering global cache for current project
            if (treeViewerInput instanceof EObject) {
                Project project = CapellaProjectHelper.getProject((EObject) treeViewerInput);
                FilteringResult allCriteriaResult = FilteringFactory.eINSTANCE.createFilteringResult();
                FilteringToolsPlugin.getGlobalFilteringCache().setCurrentFilteringResult(project, allCriteriaResult);
            }
            updateControls();
          }
        }, enabledButtons);
  }

  private void createRefreshButton(Composite parent, boolean enabledButtons) {
    refreshButton = addButton(parent, "Refresh Diagram",
        ImageDescriptor.createFromFile(FilteringToolsPlugin.class, "/icons/refresh.gif").createImage(), //$NON-NLS-1$
        new SelectionAdapter() {
          @Override
          public void widgetSelected(SelectionEvent e) {
            refreshActiveDiagram();
          }
        }, enabledButtons);
  }

  private void createCheckAllButton(Composite parent, boolean enabled) {
    checkAllButton = addButton(parent, "Check All",
        ImageDescriptor.createFromFile(FilteringToolsPlugin.class, "/icons/checkAll.gif").createImage(), //$NON-NLS-1$
        new SelectionAdapter() {
          @Override
          public void widgetSelected(SelectionEvent e) {
            Object treeViewerInput = treeViewer.getInput();

            for (Object element : contentProvider.getElements(treeViewerInput)) {
              setTreeChecked(element, true);
              undefinedElements.remove(element);
            }
            // set filtering global cache for current project as all
            // its Filtering Criterion
            if (treeViewerInput instanceof EObject) {
              Project project = CapellaProjectHelper.getProject((EObject) treeViewerInput);
              Collection<FilteringCriterion> allFilteringCriteria = FilteringUtils.getAllFilteringCriteria(project);
              FilteringResult allCriteriaResult = FilteringFactory.eINSTANCE.createFilteringResult();
              allCriteriaResult.getFilteringCriteria().addAll(allFilteringCriteria);
              FilteringToolsPlugin.getGlobalFilteringCache().setCurrentFilteringResult(project, allCriteriaResult);
            }
            updateControls();
          }
        }, enabled);

  }

  private void createActions() {
    IActionBars actionBars = getViewSite().getActionBars();
    IToolBarManager toolBarManager = actionBars.getToolBarManager();

    // Add refresh action.
    IAction refreshAction = new Action(null,
        FilteringToolsPlugin.getDefault().getImageDescriptor(IImageKeys.IMG_REFRESH)) {
      @Override
      public void run() {
        refreshActiveDiagram();
      }

    };
    toolBarManager.add(refreshAction);

  }

  public void refreshActiveDiagram() {

    try {
      PlatformUI.getWorkbench().getProgressService().run(false, false, new IRunnableWithProgress() {

        @Override
        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
          IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
              .getActiveEditor();

          if (editorPart instanceof DDiagramEditor) {
            DRepresentation representation = ((DDiagramEditor) editorPart).getRepresentation();

            TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(representation);
            domain.getCommandStack().execute(new RecordingCommand(domain) {
              @Override
              protected void doExecute() {
                DialectManager.INSTANCE.refresh(representation, new NullProgressMonitor());
              }
            });
          }

        }
      });
    } catch (InvocationTargetException | InterruptedException e) {
      e.printStackTrace();
    }

  }

  /**
   * Return the DDiagram which is the parent of the given Sirius element, or the Sirius element itself if it is a
   * DDiagram
   */
  private static DDiagram getDiagram(EObject context) {
    DDiagram result = null;
    if (context instanceof DDiagram) {
      result = (DDiagram) context;
    } else if (context instanceof DDiagramElement) {
      result = ((DDiagramElement) context).getParentDiagram();
    }
    return result;
  }

  /**
   * 
   * @return
   */
  private ICheckStateListener createCheckStateListener() {

    return new ICheckStateListener() {

      @Override
      public void checkStateChanged(CheckStateChangedEvent event) {

        Object source = event.getSource();
        Object[] checkedElements = ((ContainerCheckedTreeViewer) source).getCheckedElements();
        List<Object> checkedList = Arrays.asList(checkedElements);

        checkedList = filterNonFilteringElements(checkedList);

        Object treeRoot = treeViewer.getInput();

        if (treeRoot instanceof CapellaElement) {
          Project project = CapellaProjectHelper.getProject((EObject) treeRoot);

          GlobalFiteringCache globalFilteringCache = FilteringToolsPlugin.getGlobalFilteringCache();

          // no criterion is selected =>
          // flush the cache for current project
          if (checkedList.isEmpty()) {
              FilteringResult noCriteriaResult = FilteringFactory.eINSTANCE.createFilteringResult();
              FilteringToolsPlugin.getGlobalFilteringCache().setCurrentFilteringResult(project, noCriteriaResult);
            updateModifiedResultsLabel(noCriteriaResult);
            return;
          }

          Object firstElt = checkedList.get(0);

          if (!checkedList.isEmpty()) {

            List<FilteringCriterion> filCriterionToCache = new ArrayList<>();

            checkedList.forEach(obj -> {
              if (obj instanceof FilteringCriterion)
                filCriterionToCache.add((FilteringCriterion) obj);
            });

            // no criterion is selected =>
            // flush the cache for current project
            if (filCriterionToCache.isEmpty()) {
                FilteringResult noCriteriaResult = FilteringFactory.eINSTANCE.createFilteringResult();
                FilteringToolsPlugin.getGlobalFilteringCache().setCurrentFilteringResult(project, noCriteriaResult);
              updateModifiedResultsLabel(noCriteriaResult);
              return;
            }

            FilteringResult filtResult = FilteringFactory.eINSTANCE.createFilteringResult();

            filtResult.getFilteringCriteria().addAll(filCriterionToCache);

            globalFilteringCache.setCurrentFilteringResult(project, filtResult);

            updateModifiedResultsLabel(filtResult);
          }

          if (firstElt instanceof ComposedFilteringResult) {
            checkedList.stream().filter(obj -> obj instanceof AbstractFilteringResult).findFirst().ifPresent(obj -> {
              globalFilteringCache.setCurrentFilteringResult(project, (AbstractFilteringResult) obj);
              resetModifiedResultLabel();
            });
          }
        }
      }

	private void updateModifiedResultsLabel(FilteringResult filtResult) {
		int realIndex = filteringResultCombo.getSelectionIndex() - 1;
		if (realIndex < 0) {
			resetModifiedResultLabel();
			return;
		}
		AbstractFilteringResult filteringResult = filteringResults.get(realIndex);
		EList<FilteringCriterion> selectedFilteringResultCriteria = filteringResult.computeFilteringCriterionSet().getFilteringCriteria();
		EList<FilteringCriterion> checkedFilteringCriteria = filtResult.getFilteringCriteria();
		if (selectedFilteringResultCriteria.containsAll(checkedFilteringCriteria) && checkedFilteringCriteria.containsAll(selectedFilteringResultCriteria)) {
			resetModifiedResultLabel();
		} else {
			tagModifiedResultLabel();
		}
	}

      private List<Object> filterNonFilteringElements(List<Object> checkedList) {
        return checkedList.stream().filter(obj -> FilteringUtils.isInstanceOfFilteringExcludedElements(obj))
            .collect(Collectors.toList());

      }

    };
  }

  /**
   * Update label for selected results with "MODIFIED" tag
   */
  private void tagModifiedResultLabel() {
    setModifiedResultLabel("(MODIFIED)");
  }

  private void resetModifiedResultLabel() {
    setModifiedResultLabel("");
  }

  private void setModifiedResultLabel(String text) {
    modifiedResultLabel.setText(text);
    modifiedResultLabel.getParent().pack(true);
  }

  private Listener createFilteringResultComboListener() {
    return new Listener() {

      @Override
      @SuppressWarnings("synthetic-access")
      public void handleEvent(Event e) {
        doSelectionInComboBox();
        filteringResultCombo.pack();
        filteringResultCombo.getParent().layout();

      }

    };
  }

  private void updateControls() {

    filteringResults = FilteringUtils.getFilteringResults(projects);

    // No filtering Result is available in current selected project
    if (filteringResults.isEmpty()) {
      filteringResultCombo.removeAll();
      filteringResultCombo.add("No FilteringResults");
      modifiedResultLabel.setData("");
      modifiedResultLabel.setText("");
      modifiedResultLabel.getParent().pack(true);
      treeViewer.setInput(null);
      viewSubControls.forEach(c -> c.setEnabled(false));
    } else {// Filtering results available
      filteringResultCombo.removeAll();
      filteringResultCombo.add("Select a FilteringResult");

      boolean filterEnabled = FilteringToolsPlugin.getGlobalFilteringCache().isEnabled(mainProject);
      AbstractFilteringResult selectedFilterResult = FilteringToolsPlugin.getGlobalFilteringCache().getCurrentFilteringResult(mainProject);
      enableCheckBtn.setSelection(filterEnabled);
      // if a current result has already been selected (put into
      // global filtering cache) then we should focus on corresponding
      // filtering result in the combo box
      int selectedFiltResultIndex = 0;
      int index = 1;
      for (AbstractFilteringResult filteringResult : filteringResults) {
        if (filteringResult.equals(selectedFilterResult))
          selectedFiltResultIndex = index;
        filteringResultCombo.add(FilteringUtils.formatFilteringItemName(filteringResult));
        index++;
      }
      filteringResultCombo.select(selectedFiltResultIndex);
      doSelectionInComboBox();
      viewSubControls.forEach(c -> c.setEnabled(enableCheckBtn.getSelection()));
    }

    filteringResultCombo.pack();

    updateTreeViewerEnableState();
    updateCheckUncheckAllEnableState();

  }

  /**
   * Disable the tree if the input is a {@link ComposedFilteringResult}.
   * 
   */
  private void updateTreeViewerEnableState() {
    Object input = treeViewer.getInput();
    treeViewer.refresh();
    treeViewer.getTree().setEnabled(!(input instanceof ComposedFilteringResult));
  }

  /**
   * Disable check/uncheckAll buttons if the input is a {@link ComposedFilteringResult}.
   * 
   */
  private void updateCheckUncheckAllEnableState() {
    Object input = treeViewer.getInput();
    if (input instanceof ComposedFilteringResult) {
      checkAllButton.setEnabled(false);
      unchekAllButton.setEnabled(false);
    }
    if (input instanceof SystemEngineering) {
      checkAllButton.setEnabled(true);
      unchekAllButton.setEnabled(true);
    }

  }

  private Button addButton(Composite parent, String toolTipText, Image image, SelectionListener action,
      boolean enabled) {
    Button button = new Button(parent, SWT.PUSH);
    button.setToolTipText(toolTipText);
    button.setImage(image);
    button.addSelectionListener(action);
    button.setEnabled(enabled);
    return button;
  }

  @Override
  public void selectionChanged(IWorkbenchPart part, ISelection selection) {
    projects = null;
    mainProject = null;

    // update tree content
    if (selection instanceof IStructuredSelection) {
      Object firstElement = ((IStructuredSelection) selection).getFirstElement();
      EObject selectedEObject = null;
      // try to extract an EObject from selection
      if (firstElement instanceof DiagramEditPart) {
        selectedEObject = ((DiagramEditPart) firstElement).resolveSemanticElement();

      } else if (firstElement instanceof EObject) {
        selectedEObject = (EObject) firstElement;

      }

      handleSelection(selectedEObject);
      updateControls();

    }
  }

  private void handleSelection(EObject eObject) {

    if (eObject instanceof DSemanticDecorator)
      eObject = ((DSemanticDecorator) eObject).getTarget();
    if (eObject instanceof DRepresentationDescriptor)
      eObject = ((DRepresentationDescriptor) eObject).getTarget();

    focusOn(eObject);

    if (eObject != null) {
      mainProject = CapellaProjectHelper.getProject(eObject);
      projects = FilteringUtils.getMainAndReferencedVariantProjects(eObject);
    }
  }

  private void focusOn(EObject eObj) {
    if (eObj == null) {
      // focus on null object should clear to tree
      treeViewer.setInput(null);
    }

    Session session = SessionManager.INSTANCE.getSession(eObj);

    if (session != null) {
      TransactionalEditingDomain transactionalEditingDomain = session.getTransactionalEditingDomain();
      TransactionalAdapterFactoryLabelProvider labelProvider = new TransactionalAdapterFactoryLabelProvider(
          transactionalEditingDomain, ((AdapterFactoryEditingDomain) transactionalEditingDomain).getAdapterFactory());
      treeViewer.setLabelProvider(labelProvider);
      treeViewer.setInput(FilteringUtils.getSystemEngineering(eObj));
    }

  }

  protected void setTreeChecked(Object treeElement, boolean state) {
    treeViewer.setChecked(treeElement, state);
    treeViewer.setGrayed(treeElement, false);
    updateCheckUncheckedElements(treeElement, state);
    // Now logically check/uncheck all children
    if (contentProvider.hasChildren(treeElement)) {
      Object[] children = contentProvider.getChildren(treeElement);
      for (int i = 0; i < children.length; ++i) {
        setTreeChecked(children[i], state);
      }
    }
  }

  @Override
  public void dispose() {
    super.dispose();
    getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(this);
    FilteringToolsPlugin.getGlobalFilteringCache().clear();
  }

  @Override
  public void init(IViewSite site) throws PartInitException {

    super.init(site);
  }

  protected void updateCheckUncheckedElements(Object element, boolean state) {
    if (state) {
      checkedElements.add(element);
      uncheckedElements.remove(element);
    } else {
      checkedElements.remove(element);
      uncheckedElements.add(element);
    }
  }

  protected void treeItemChecked(Object treeElement, boolean state) {
    updateCheckUncheckedElements(treeElement, state);

    // Recursively adjust all child tree elements appropriately
    setTreeChecked(treeElement, state);

    Object parent = contentProvider.getParent(treeElement);

    if (parent == null) {
      return;
    }

    // Now update upwards in the tree hierarchy
    if (state) {
      grayCheckHierarchy(parent);
    } else {
      ungrayCheckHierarchy(parent);
    }

    // Update the hierarchy but do not white select the parent
    grayUpdateHierarchy(parent);
  }

  /**
   * Logically un-gray-check all ancestors of treeItem if appropriate.
   */
  protected void ungrayCheckHierarchy(Object treeElement) {
    if (!determineShouldBeAtLeastGrayChecked(treeElement)) {
      checkedElements.remove(treeElement);
      uncheckedElements.add(treeElement);
    }

    Object parent = contentProvider.getParent(treeElement);
    if (parent != null) {
      ungrayCheckHierarchy(parent);
    }

  }

  protected boolean determineShouldBeAtLeastGrayChecked(Object treeElement) {
    // If any children of treeElement are still gray-checked then
    // treeElement
    // must remain gray-checked as well.
    if (contentProvider.hasChildren(treeElement)) {
      Object[] children = contentProvider.getChildren(treeElement);
      for (int i = 0; i < children.length; ++i) {
        if (checkedElements.contains(children[i])) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Set the checked state of self and all ancestors appropriately.
   */
  private void grayUpdateHierarchy(Object treeElement) {
    boolean shouldBeAtLeastGray = determineShouldBeAtLeastGrayChecked(treeElement);

    updateCheckUncheckedElements(treeElement, shouldBeAtLeastGray);
    treeViewer.setGrayChecked(treeElement, shouldBeAtLeastGray);

    // Proceed up the tree element hierarchy
    Object parent = contentProvider.getParent(treeElement);
    if (parent != null) {
      grayUpdateHierarchy(parent);
    }
  }

  /**
   * Logically gray-check all ancestors of treeItem.
   */
  protected void grayCheckHierarchy(Object treeElement) {
    // If this tree element is already gray then its ancestors all are as
    // well
    if (checkedElements.contains(treeElement)) {
      return; // No need to proceed upwards from here
    }

    Object parent = contentProvider.getParent(treeElement);
    if (parent != null) {
      grayCheckHierarchy(parent);
    }
  }

  private void doSelectionInComboBox() {

    // index 0 is the title of the combo
    if (filteringResultCombo.getSelectionIndex() == 0) {
      resetModifiedResultLabel();
      return;
    }

    // -1 to take into account the added title of the combo as combo item
    int realIndex = filteringResultCombo.getSelectionIndex() - 1;

    // TODO combo only allows the selection of one element... If
    // we allow multiple selection the SuperSet calculation will
    // make sense
    AbstractFilteringResult filteringResult = filteringResults.get(realIndex);

    Project project = CapellaProjectHelper.getProject(filteringResult);

    FilteringToolsPlugin.getGlobalFilteringCache().setCurrentFilteringResult(project, filteringResult);

    if (filteringResult instanceof FilteringResult) {
      contentProvider = new CriteriaContentProvider();
      treeViewer.setContentProvider(contentProvider);
      treeViewer.setInput(FilteringUtils.getSystemEngineering(filteringResult));
      uncheckedElements.clear();
      checkedElements.clear();

      updateTreeViewerEnableState();
      updateCheckUncheckAllEnableState();
    }

    if (filteringResult instanceof ComposedFilteringResult) {
      contentProvider = new FilteringResultsContentProvider();
      treeViewer.setContentProvider(contentProvider);
      treeViewer.setInput(filteringResult);
      uncheckedElements.clear();
      checkedElements.clear();

      updateTreeViewerEnableState();
      updateCheckUncheckAllEnableState();
    }

    // Uncheck all
    for (Object element : contentProvider.getElements(projects)) {
      treeItemChecked(element, false);
    }

    // Update the checkboxes
    for (FilteringCriterion vf : filteringResult.computeFilteringCriterionSet().getFilteringCriteria()) {
      treeItemChecked(vf, true);
      undefinedElements.remove(vf);
    }

    // reset the modified status
    resetModifiedResultLabel();
  }

  @Override
  public void setFocus() {
    // TODO Auto-generated method stub

  }

}
