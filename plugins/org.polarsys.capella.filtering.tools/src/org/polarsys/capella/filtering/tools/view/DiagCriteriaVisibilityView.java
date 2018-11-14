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
package org.polarsys.capella.filtering.tools.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.CheckBox;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.StyledString.Fragment;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryLabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import org.polarsys.capella.common.platform.sirius.ted.DataNotifier;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringFactory;
import org.polarsys.capella.filtering.FilteringResult;
import org.polarsys.capella.filtering.tools.FilteringToolsPlugin;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;
import org.polarsys.capella.filtering.tools.utils.ui.CriteriaContentProvider;

import com.google.common.collect.Lists;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;

public class DiagCriteriaVisibilityView extends ViewPart implements ISelectionProvider, ISelectionListener {
	private DataBindingContext m_bindingContext;
	private static final String MESSAGE = "Visible Diagram Filtering Criteria are checked";
	private static final String MSG_CURRENT_RESULT = "Selected Result:";

	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Composite selectionButtonComposite;

	ICheckStateListener viewerCheckStateListener;

	ContainerCheckedTreeViewer treeViewer;
	CriteriaContentProvider contentProvider = new CriteriaContentProvider();
	private Collection<Project> projects = null;

	/**
	 * Collection of elements currently checked by user.
	 */
	private Collection<Object> checkedElements = null;
	private Collection<Object> uncheckedElements = null;
	private Collection<Object> initialCheckedElements = null;
	private Collection<Object> initialUndefinedElements = null;
	private Collection<Object> undefinedElements = null;

	private Label configLabel;

	private Combo filteringResultCombo;

	protected List<FilteringResult> filteringResults;

	private Button unchekAllButton;
	private Button checkAllButton;

	private List<Control> allViewControls;
	private List<Control> viewControls;
	private Label currentSelectedResultLabel;
	private Button enableCheckBtn;
	private Composite currentSelectionComposite;

	public DiagCriteriaVisibilityView() {
		checkedElements = new HashSet<>();
		uncheckedElements = new HashSet<>();
		initialCheckedElements = new HashSet<>();
		initialUndefinedElements = new HashSet<>();
		undefinedElements = new HashSet<>();

	}

	public Collection<Object> getCheckedElements() {
		return checkedElements;
	}

	public void setCheckedElements(Collection<Object> checkedElements) {
		this.checkedElements = checkedElements;
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public ISelection getSelection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSelection(ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createPartControl(Composite parent) {
		GridLayout gl_parent = new GridLayout(1, true);
		gl_parent.horizontalSpacing = 1;
		gl_parent.verticalSpacing = 1;
		gl_parent.marginHeight = 2;
		parent.setLayout(gl_parent);

		// Message Area
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, true, false, 1, 1));
		composite.setLayout(new GridLayout(1, false));
		Label lblEee = formToolkit.createLabel(composite, MESSAGE, SWT.SHADOW_NONE);

		enableCheckBtn = new Button(composite, SWT.CHECK);
		formToolkit.adapt(enableCheckBtn, true, true);
		enableCheckBtn.setText("ON");
		// enableCheckBtn.addListener(SWT.ALL, new Listener() {
		// public void handleEvent(Event e) {
		// switch (e.type) {
		// case SWT.CHECK:
		// FilteringToolsPlugin.getGlobalFilteringCache().enable();
		// break;
		// }
		// }
		// });

		enableCheckBtn.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent event) {
				Button btn = (Button) event.getSource();
				boolean enabled = btn.getSelection();
				FilteringToolsPlugin.getGlobalFilteringCache().setEnabled(enabled);
				if (!enabled) {
					viewControls.forEach(c -> c.setEnabled(false));
				}
			}
		});

		currentSelectionComposite = new Composite(composite, SWT.NONE);
		currentSelectionComposite.setLayout(new GridLayout(2, false));
		currentSelectionComposite.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, true, false, 1, 1));

		Label currentSelectedResultInfo = formToolkit.createLabel(currentSelectionComposite, MSG_CURRENT_RESULT,
				SWT.HORIZONTAL | SWT.SHADOW_NONE);
		currentSelectedResultLabel = formToolkit.createLabel(currentSelectionComposite, "",
				SWT.HORIZONTAL | SWT.SHADOW_NONE);
		currentSelectedResultLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		currentSelectedResultLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		// Button and message area
		selectionButtonComposite = createSelectionButtonsAfterMessageArea(parent);

		// GridData gd_selectionButtonComposte = new GridData(SWT.LEFT,
		// SWT.CENTER, false, false, 1, 1);
		// gd_selectionButtonComposte.heightHint = 34;
		// selectionButtonComposite.setLayoutData(gd_selectionButtonComposte);
		// formToolkit.adapt(selectionButtonComposite);
		// formToolkit.paintBordersFor(selectionButtonComposite);

		// Tree Viewer Area
		treeViewer = new ContainerCheckedTreeViewer(parent, SWT.BORDER);
		Tree tree = treeViewer.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		// Set alphabetic sorter
		treeViewer.setComparator(new ViewerComparator());

		// GridData gd_tree = new GridData(SWT.FILL, SWT.FILL, true, true);
		// tree.setLayoutData(gd_tree);
		treeViewer.setContentProvider(contentProvider);
		treeViewer.setAutoExpandLevel(TreeViewer.ALL_LEVELS);
		treeViewer.setInput(treeViewer.getInput());
		// formToolkit.paintBordersFor(tree);

		// TODO: should register to Capella project explorer view only
		// => register to one view
		getViewSite().getPage().addSelectionListener(this);

		viewControls = Arrays.asList(unchekAllButton, checkAllButton, configLabel, currentSelectedResultLabel,
				filteringResultCombo, treeViewer.getTree());
		
		allViewControls = new ArrayList<>(viewControls);
		
		allViewControls.add(enableCheckBtn);

		// Lazy creation pattern.
		if (null == viewerCheckStateListener) {
			viewerCheckStateListener = createCheckStateListener();
		}
		// Register listener
		treeViewer.addCheckStateListener(viewerCheckStateListener);
		
		//configure data binding
		m_bindingContext = initDataBindings();

	}

	private ICheckStateListener createCheckStateListener() {
		return new ICheckStateListener() {

			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {

				Object source = event.getSource();
				Object[] checkedElements2 = ((ContainerCheckedTreeViewer) source).getCheckedElements();
				List<Object> checkedList = Arrays.asList(checkedElements2);

				Object treeRoot = treeViewer.getInput();

				if (treeRoot instanceof CapellaElement) {
					Project project = CapellaProjectHelper.getProject((EObject) treeRoot);

					List<FilteringCriterion> filCriterionToCache = new ArrayList<>();
					checkedList.forEach(obj -> {
						if (obj instanceof FilteringCriterion)
							filCriterionToCache.add((FilteringCriterion) obj);
					});
					// no criterion is selected =>
					// flush the cache for current project
					if (filCriterionToCache.isEmpty()) {
						FilteringToolsPlugin.getGlobalFilteringCache().remove(project);
						return;
					}

					FilteringResult filtResult = FilteringFactory.eINSTANCE.createFilteringResult();

					filtResult.getFilteringCriteria().addAll(filCriterionToCache);

					GlobalFiteringCache globalFilteringCache = FilteringToolsPlugin.getGlobalFilteringCache();

					globalFilteringCache.setCurrentFilteringResult(project, filtResult);

					// Update label for selected results with "MODIFIED" tag

					setCurrentResultLabelAsModified();
				}
			}

		};
	}

	private void setCurrentResultLabelAsModified() {
		String oldText = currentSelectedResultLabel.getData().toString();
		currentSelectedResultLabel.setText(oldText + " (MODIFIED)");
	}

	/**
	 * Creates selection buttons.
	 * 
	 * @param composite
	 *            the parent composite
	 * @return the selection buttons composite
	 */
	protected Composite createSelectionButtonsAfterMessageArea(Composite composite) {
		GridData data = new GridData(GridData.END | GridData.GRAB_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		composite.setData(data);

		Composite buttonComposite = new Composite(composite, SWT.TRAIL);
		buttonComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		GridLayout layout = new GridLayout();
		layout.verticalSpacing = 1;
		layout.marginWidth = 1;
		layout.numColumns = 5;
		buttonComposite.setLayout(layout);
		buttonComposite.setFont(composite.getFont());

		// Disabled if no options
		boolean enabledButtons = projects != null && !projects.isEmpty();

		checkAllButton = addButton(buttonComposite, "Check All",
				ImageDescriptor.createFromFile(FilteringToolsPlugin.class, "/icons/checkAll.gif").createImage(), //$NON-NLS-1$
				new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						for (Object element : contentProvider.getElements(treeViewer.getInput())) {
							setTreeChecked(element, true);
							undefinedElements.remove(element);
						}
						setCurrentResultLabelAsModified();
					}
				}, enabledButtons);

		unchekAllButton = addButton(buttonComposite, "Uncheck All",
				ImageDescriptor.createFromFile(FilteringToolsPlugin.class, "/icons/uncheckAll.gif").createImage(), //$NON-NLS-1$
				new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						for (Object element : contentProvider.getElements(treeViewer.getInput())) {
							setTreeChecked(element, false);
							undefinedElements.remove(element);
						}
						setCurrentResultLabelAsModified();
					}
				}, enabledButtons);

		configLabel = new Label(buttonComposite, SWT.NONE);
		configLabel.setImage(ImageDescriptor
				.createFromFile(FilteringToolsPlugin.class, "/icons/selectCriteriaFromResult.png") //$NON-NLS-1$
				.createImage());
		configLabel.setEnabled(enabledButtons);

		filteringResultCombo = new Combo(buttonComposite, SWT.READ_ONLY);
		GridData gd_configCombo = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_configCombo.widthHint = 135;
		filteringResultCombo.setLayoutData(gd_configCombo);

		filteringResultCombo.addListener(SWT.Selection, createFilteringResultComboListener());

		new Label(buttonComposite, SWT.NONE);
		new Label(buttonComposite, SWT.NONE);

		return buttonComposite;
	}

	private Listener createFilteringResultComboListener() {
		return new Listener() {

			@Override
			@SuppressWarnings("synthetic-access")
			public void handleEvent(Event e) {
				// index 0 is the title of the combo
				if (filteringResultCombo.getSelectionIndex() == 0) {
					// update label for current selected result
					updateCurrentSelectedResultLabel("");
					return;
				}

				// -1 for not to count the title of the combo
				int realIndex = filteringResultCombo.getSelectionIndex() - 1;

				// TODO combo only allows the selection of one element... If
				// we allow multiple selection the SuperSet calculation will
				// make sense
				FilteringResult filteringResult = filteringResults.get(realIndex);

				Project project = CapellaProjectHelper.getProject(filteringResult);

				FilteringToolsPlugin.getGlobalFilteringCache().setCurrentFilteringResult(project, filteringResult);

				Object[] selectedConfigurations = new Object[] { filteringResult };
				List<EObject> featureSetList = new ArrayList<>();
				for (Object object : selectedConfigurations) {
					featureSetList.add((EObject) object);
				}
				List<FilteringCriterion> superSet = FilteringUtils.getSuperSet(featureSetList);

				// Uncheck all
				for (Object element : contentProvider.getElements(projects)) {
					treeItemChecked(element, false);
				}

				// Update the checkboxes
				for (FilteringCriterion vf : superSet) {
					treeItemChecked(vf, true);
					undefinedElements.remove(vf);
				}

				// update label for current selected result
				updateCurrentSelectedResultLabel(FilteringUtils.formatFilteringItemName(filteringResult));

			}

			private void updateCurrentSelectedResultLabel(String formatFilteringItemName) {
				currentSelectedResultLabel.setData(formatFilteringItemName);
				currentSelectedResultLabel.setText(formatFilteringItemName);

			}
		};
	}

	private void updateControls() {

		filteringResults = FilteringUtils.getFilteringResults(projects);

		// No filtering Result is available in current selected project
		if (filteringResults.isEmpty()) {
			filteringResultCombo.removeAll();
			filteringResultCombo.add("No FilteringResults");
			currentSelectedResultLabel.setData("");
			currentSelectedResultLabel.setText("");

			allViewControls.forEach(c -> c.setEnabled(false));

		} else {// Filtering results available
			filteringResultCombo.removeAll();
			filteringResultCombo.add("Select a FilteringResult");
			for (FilteringResult filteringResult : filteringResults) {
				filteringResultCombo.add(FilteringUtils.formatFilteringItemName(filteringResult));
			}
			allViewControls.forEach(c -> c.setEnabled(true));
		}
		filteringResultCombo.select(0);
		filteringResultCombo.pack();

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

		// update tree content
		if (selection instanceof ITreeSelection) {
			Object firstElement = ((ITreeSelection) selection).getFirstElement();

			if (firstElement instanceof CapellaElement) {
				focusOn(firstElement);
				projects = FilteringUtils.getMainAndReferencedVariantProjects((EObject) firstElement);
				// update all controls
				updateControls();
			} else if (firstElement instanceof DRepresentationDescriptor) {
				firstElement = ((DRepresentationDescriptor) firstElement).getTarget();
				if (firstElement instanceof CapellaElement) {
					// TODO refactor to updateViewContent()
					focusOn(firstElement);
					projects = FilteringUtils.getMainAndReferencedVariantProjects((EObject) firstElement);
					// update all controls
					updateControls();

				}

			} else {
				// set selection to empty
			}
			// update combo box
			updateControls();
		}
	}

	private void focusOn(Object firstElement) {
		CapellaElement capellaElement = (CapellaElement) firstElement;
		Session session = SessionManager.INSTANCE.getSession(capellaElement);
		TransactionalEditingDomain transactionalEditingDomain = session.getTransactionalEditingDomain();
		TransactionalAdapterFactoryLabelProvider labelProvider = new TransactionalAdapterFactoryLabelProvider(
				transactionalEditingDomain,
				((AdapterFactoryEditingDomain) transactionalEditingDomain).getAdapterFactory());
		treeViewer.setLabelProvider(labelProvider);
		treeViewer.setInput(FilteringUtils.getSystemEngineering(capellaElement));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

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
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeEnabledEnableCheckBtnObserveWidget = WidgetProperties.enabled().observe(enableCheckBtn);
		IObservableValue observeEnabledCurrentSelectionCompositeObserveWidget = WidgetProperties.enabled().observe(currentSelectionComposite);
		bindingContext.bindValue(observeEnabledEnableCheckBtnObserveWidget, observeEnabledCurrentSelectionCompositeObserveWidget, null, null);
		//
		return bindingContext;
	}
}
