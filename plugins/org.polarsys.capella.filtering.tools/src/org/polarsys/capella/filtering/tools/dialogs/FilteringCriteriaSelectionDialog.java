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
package org.polarsys.capella.filtering.tools.dialogs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.CheckedTreeSelectionDialog;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.filtering.AbstractFilteringResult;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringResult;
import org.polarsys.capella.filtering.tools.FilteringToolsPlugin;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;
import org.polarsys.capella.filtering.tools.utils.ui.CriteriaContentProvider;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public class FilteringCriteriaSelectionDialog extends CheckedTreeSelectionDialog {

  CriteriaContentProvider contentProvider;

  private Collection<Project> projects = null;
  /**
   * Collection of elements currently checked by user.
   */
  private Collection<Object> checkedElements = null;
  private Collection<Object> uncheckedElements = null;
  private Collection<Object> initialCheckedElements = null;
  private Collection<Object> initialUndefinedElements = null;
  private Collection<Object> undefinedElements = null;

  public FilteringCriteriaSelectionDialog(Shell parent, ILabelProvider labelProvider,
      CriteriaContentProvider contentProvider, Project project) {
    this(parent, labelProvider, contentProvider, Arrays.asList(project));
  }

  /**
   * @param parent
   * @param labelProvider
   * @param contentProvider
   * @param projects
   */
  public FilteringCriteriaSelectionDialog(Shell parent, ILabelProvider labelProvider,
      CriteriaContentProvider contentProvider, Collection<Project> projects) {
    super(parent, labelProvider, contentProvider);
    this.contentProvider = contentProvider;
    this.projects = projects;
    checkedElements = new HashSet<>();
    uncheckedElements = new HashSet<>();
    initialCheckedElements = new HashSet<>();
    initialUndefinedElements = new HashSet<>();
    undefinedElements = new HashSet<>();
    // Set alphabetic sorter
    this.setComparator(new ViewerComparator());
  }

  @Override
  protected CheckboxTreeViewer createTreeViewer(Composite parent) {
    CheckboxTreeViewer treeViewer = super.createTreeViewer(parent);
    treeViewer.setAutoExpandLevel(TreeViewer.ALL_LEVELS);
    treeViewer.setInput(treeViewer.getInput());
    return treeViewer;
  }

  /**
   * This method has been overridden to be able to insert selection buttons between the top label and the tree viewer.
   * {@inheritDoc}
   */
  @Override
  protected Label createMessageArea(Composite composite) {
    Label createMessageArea = super.createMessageArea(composite);
    createSelectionButtonsAfterMessageArea(composite);
    return createMessageArea;
  }

  /**
   * Creates selection buttons.
   * 
   * @param composite
   *          the parent composite
   * @return the selection buttons composite
   */
  protected Composite createSelectionButtonsAfterMessageArea(Composite composite) {
    Composite buttonComposite = new Composite(composite, SWT.RIGHT);
    GridLayout layout = new GridLayout();
    layout.numColumns = 7;
    layout.makeColumnsEqualWidth = false;
    buttonComposite.setLayout(layout);
    buttonComposite.setFont(composite.getFont());

    GridData data = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_HORIZONTAL);
    data.grabExcessHorizontalSpace = true;
    composite.setData(data);

    data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
    data.grabExcessHorizontalSpace = true;

    // Disabled if no options
    boolean enabledButtons = projects != null && !projects.isEmpty();

    addButton(buttonComposite, "Check All",
        ImageDescriptor.createFromFile(FilteringToolsPlugin.class, "/icons/checkAll.gif").createImage(), //$NON-NLS-1$
        new SelectionAdapter() {
          @Override
          public void widgetSelected(SelectionEvent e) {
            for (Object element : contentProvider.getElements(getTreeViewer().getInput())) {
              setTreeChecked(element, true);
              undefinedElements.remove(element);
            }
          }
        }, enabledButtons).setLayoutData(data);

    addButton(buttonComposite, "Uncheck All",
        ImageDescriptor.createFromFile(FilteringToolsPlugin.class, "/icons/uncheckAll.gif").createImage(), //$NON-NLS-1$
        new SelectionAdapter() {
          @Override
          public void widgetSelected(SelectionEvent e) {
            for (Object element : contentProvider.getElements(getTreeViewer().getInput())) {
              setTreeChecked(element, false);
              undefinedElements.remove(element);
            }
          }
        }, enabledButtons).setLayoutData(data);

    Label configLabel = new Label(buttonComposite, SWT.NONE);
    configLabel
        .setImage(ImageDescriptor.createFromFile(FilteringToolsPlugin.class, "/icons/selectCriteriaFromResult.png") //$NON-NLS-1$
            .createImage());
    configLabel.setEnabled(enabledButtons);

    final Combo configCombo = new Combo(buttonComposite, SWT.READ_ONLY | SWT.MULTI | SWT.DROP_DOWN);
    final List<AbstractFilteringResult> filteringResults = FilteringUtils.getFilteringResults(projects);
    if (filteringResults.isEmpty()) {
      configCombo.add("No FilteringResults");
      configLabel.setEnabled(false);
      configCombo.setEnabled(false);
    } else {
      configCombo.add("Select a FilteringResult");
      for (AbstractFilteringResult filteringResult : filteringResults) {
        configCombo.add(FilteringUtils.formatFilteringItemName(filteringResult));
      }
      configCombo.addListener(SWT.Selection, new Listener() {
        @Override
        @SuppressWarnings("synthetic-access")
        public void handleEvent(Event e) {
          // index 0 is the title of the combo
          if (configCombo.getSelectionIndex() == 0) {
            return;
          }

          // -1 for not to count the title of the combo
          int realIndex = configCombo.getSelectionIndex() - 1;

          AbstractFilteringResult selectedFilteringResult = filteringResults.get(realIndex);

          // Uncheck all
          for (Object element : contentProvider.getElements(projects)) {
            treeItemChecked(element, false);
          }

          // Update the checkboxes
          for (FilteringCriterion vf : selectedFilteringResult.computeFilteringCriterionSet().getFilteringCriteria()) {
            treeItemChecked(vf, true);
            undefinedElements.remove(vf);
          }
        }
      });
    }
    configCombo.select(0);
    return buttonComposite;
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
  protected Composite createSelectionButtons(Composite composite) {
    Composite buttonComposite = new Composite(composite, SWT.RIGHT) {
      /**
       * This method has been overridden to have an "empty" size for this composite. {@inheritDoc}
       * 
       * @see org.eclipse.swt.widgets.Composite#computeSize(int, int, boolean)
       */
      @Override
      public Point computeSize(int wHint, int hHint, boolean b) {
        return super.computeSize(0, 0, b);
      }
    };
    buttonComposite.setVisible(false);
    return buttonComposite;
  }

  @Override
  public void setInitialSelections(Object[] selectedElements) {
    setInitialElementSelections(Arrays.asList(selectedElements));
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void setInitialElementSelections(List selectedElements) {
    // Augment the list with the Project(s)
    Set<Project> prjts = new HashSet<>();
    for (Object selected : selectedElements) {
      if (selected instanceof FilteringCriterion) {
        prjts.add(CapellaProjectHelper.getProject((FilteringCriterion) selected));
      }
    }
    selectedElements.addAll(prjts);
    super.setInitialElementSelections(selectedElements);
    checkedElements.addAll(selectedElements);
  }

  /**
   * Returns the elements currently checked by user.
   * 
   * @return the elements currently checked by user
   */
  public Collection<Object> getCheckedElements() {
    return Collections2.filter(checkedElements, new Predicate<Object>() {

      @Override
      public boolean apply(Object input) {
        return input instanceof FilteringCriterion;
      };
    });
  }

  public Collection<Object> getUnCheckedElements() {
    return Collections2.filter(uncheckedElements, new Predicate<Object>() {

      @Override
      public boolean apply(Object input) {
        return input instanceof FilteringCriterion;
      };
    });
  }

  public Collection<Object> getInitialUndefinedElements() {
    return Collections2.filter(initialUndefinedElements, new Predicate<Object>() {

      @Override
      public boolean apply(Object input) {
        return input instanceof FilteringCriterion;
      };
    });
  }

  public Collection<Object> getUndefinedElements() {
    return Collections2.filter(undefinedElements, new Predicate<Object>() {

      @Override
      public boolean apply(Object input) {
        return input instanceof FilteringCriterion;
      };
    });
  }

  public void setInitialUndefinedElements(Collection<Object> undefinedElements) {
    initialUndefinedElements = undefinedElements;
    this.undefinedElements = undefinedElements;
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.eclipse.jface.dialogs.Dialog#createContents(org.eclipse.swt.widgets.Composite)
   */
  @Override
  @SuppressWarnings("unchecked")
  protected Control createContents(Composite parent) {
    Control result = super.createContents(parent);

    initialCheckedElements.addAll(getInitialElementSelections());
    getTreeViewer().setCheckStateProvider(new ICheckStateProvider() {

      public boolean isGrayed(Object element) {
        return initialUndefinedElements.contains(element) || determineShouldBeAtLeastGrayChecked(element);
      }

      public boolean isChecked(Object element) {
        return initialCheckedElements.contains(element);
      }
    });

    // User modifications
    getTreeViewer().addCheckStateListener(new ICheckStateListener() {
      public void checkStateChanged(CheckStateChangedEvent event) {
        if (getTreeViewer().getGrayed(event.getElement())) {
          undefinedElements.remove(event.getElement());
          getTreeViewer().setGrayed(event.getElement(), false);
        }
        treeItemChecked(event.getElement(), event.getChecked());
      }
    });

    return result;
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

  protected void updateCheckUncheckedElements(Object element, boolean state) {
    if (state) {
      checkedElements.add(element);
      uncheckedElements.remove(element);
    } else {
      checkedElements.remove(element);
      uncheckedElements.add(element);
    }
  }

  protected void setTreeChecked(Object treeElement, boolean state) {
    getTreeViewer().setChecked(treeElement, state);
    getTreeViewer().setGrayed(treeElement, false);
    updateCheckUncheckedElements(treeElement, state);
    // Now logically check/uncheck all children
    if (contentProvider.hasChildren(treeElement)) {
      Object[] children = contentProvider.getChildren(treeElement);
      for (int i = 0; i < children.length; ++i) {
        setTreeChecked(children[i], state);
      }
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

  /**
   * Set the checked state of self and all ancestors appropriately.
   */
  private void grayUpdateHierarchy(Object treeElement) {
    boolean shouldBeAtLeastGray = determineShouldBeAtLeastGrayChecked(treeElement);

    updateCheckUncheckedElements(treeElement, shouldBeAtLeastGray);
    getTreeViewer().setGrayChecked(treeElement, shouldBeAtLeastGray);

    // Proceed up the tree element hierarchy
    Object parent = contentProvider.getParent(treeElement);
    if (parent != null) {
      grayUpdateHierarchy(parent);
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
   * Refreshes this dialog's viewer.
   */
  public void refresh() {
    getTreeViewer().refresh();
  }

}
