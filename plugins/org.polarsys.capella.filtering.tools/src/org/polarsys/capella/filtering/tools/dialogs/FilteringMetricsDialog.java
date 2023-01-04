/*******************************************************************************
 * Copyright (c) 2018, 2023 THALES GLOBAL SERVICES.
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeColumn;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.ui.toolkit.dialogs.AbstractExportDialog;
import org.polarsys.capella.common.ui.toolkit.viewers.AbstractRegExpViewer;
import org.polarsys.capella.common.ui.toolkit.viewers.IViewerStyle;
import org.polarsys.capella.common.ui.toolkit.viewers.TreeAndListViewer;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataContentProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringModel;
import org.polarsys.capella.filtering.tools.Messages;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

public class FilteringMetricsDialog extends AbstractExportDialog {

  private CCombo combo;
  private EObject root = null;

  /**
   * @param parentShell
   * @param dialogTitle
   * @param dialogMessage
   * @param root
   * @param elements
   */
  public FilteringMetricsDialog(Shell parentShell, String dialogTitle, String dialogMessage, EObject root,
      List<? extends EObject> elements) {
    super(parentShell, dialogTitle, dialogMessage, dialogTitle);

    this.root = root;
    setData(new TreeData(elements, null));
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractViewerDialog#doCreateDialogArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void doCreateDialogArea(Composite parent) {
    // Create a composing composite.
    Composite containingComposite = new Composite(parent, SWT.NONE);
    containingComposite.setLayout(new GridLayout(1, true));
    containingComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

    createStatusArea(containingComposite);

    // Create the viewer area.
    super.doCreateDialogArea(containingComposite);

    // Collapse all
    ((TreeViewer) getViewer()).collapseAll();
    // Show System Engineering packages
    ((TreeViewer) getViewer()).expandToLevel(3);
  }

  /**
   * @param parent
   */
  protected void createStatusArea(Composite parent) {
    final int initialSelection = 0;

    Composite comp = new Composite(parent, SWT.NONE);
    comp.setLayout(new GridLayout(2, false));
    comp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    Label lbl = new Label(comp, SWT.NONE);
    lbl.setText(Messages.filtering_dialog_combo_lbl);

    combo = new CCombo(comp, SWT.NONE | SWT.READ_ONLY | SWT.BORDER);

    GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
    combo.setLayoutData(gd);

    combo.add(Messages.FilteringMetricsDialog_0);
    combo.setData(String.valueOf(initialSelection), getData());

    combo.add(Messages.FilteringMetricsDialog_1);
    combo.setData(String.valueOf(1), getData());

    // Get feature model and create the widgets if it exists
    List<FilteringModel> filteringModels = FilteringUtils.getFilteringModels(root, true);

    if (FilteringUtils.hasFilteringFeatures(filteringModels)) {
      int i = 1;
      int i0 = 0;
      for (FilteringCriterion criterion : FilteringUtils.getOwnedFilteringCriteria(filteringModels)) {
        combo.add(FilteringUtils.formatFilteringItemName(criterion));
        combo.setData(String.valueOf(++i), criterion);
      }

      combo.select(i0);
      combo.addSelectionListener(new SelectionAdapter() {
        @SuppressWarnings({ "synthetic-access", "unchecked" })
        @Override
        public void widgetSelected(SelectionEvent e) {

          // Get valid elements
          List<? extends EObject> allTaggedElements = (List<? extends EObject>) ((TreeData) getData())
              .getValidElements();

          List<EObject> returnData = new ArrayList<>();

          // Check if there is a feature selection
          int comboFilterIndex = combo.getSelectionIndex();
          FilteringCriterion selectedFeatureFilter = null;
          if (comboFilterIndex > 1) {
            selectedFeatureFilter = (FilteringCriterion) combo.getData(String.valueOf(comboFilterIndex));
          }

          // Loop through valid elements
          for (EObject eobj : allTaggedElements) {
            // We always filter AssociatedFilteringCriterionSet. We
            // don't want to see it
            if (!(eobj instanceof AssociatedFilteringCriterionSet)) {
              if (comboFilterIndex == 1) {
                if (!FilteringUtils.getAssociatedCriteria(eobj).isEmpty()) {
                  returnData.add(eobj);
                }
                // No filter so just add it
              } else if (selectedFeatureFilter == null) {
                returnData.add(eobj);

              } else {
                // Filter based on feature selection
                for (FilteringCriterion feature : FilteringUtils.getAssociatedCriteria(eobj)) {
                  if (feature.equals(selectedFeatureFilter)) {
                    returnData.add(eobj);
                  }
                }
              }
            }
          }

          // Fill the viewer with our list
          getViewer().setInput(new TreeData(returnData, null));
        }
      });
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractMessageDialogWithViewer#createViewer(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected AbstractRegExpViewer createViewer(Composite parent) {
    // Create tree viewer.
    // Don't use the status bar of the viewer b
    TreeAndListViewer treeViewer = new TreeAndListViewer(parent, false, IViewerStyle.SHOW_STATUS_BAR);
    TreeViewer viewer = treeViewer.getClientViewer();
    TreeViewerColumn columnViewer = new TreeViewerColumn(viewer, SWT.LEFT | SWT.FILL);
    TreeColumn column = columnViewer.getColumn();
    column.setText(Messages.filtering_dialog_header_col0);
    column.setWidth(300);

    columnViewer = new TreeViewerColumn(viewer, SWT.LEFT | SWT.FILL);
    column = columnViewer.getColumn();
    column.setText(Messages.FilteringMetricsDialog_2);
    column.setToolTipText(Messages.FilteringMetricsDialog_3);
    column.setWidth(150);

    viewer.getTree().setLinesVisible(true);
    viewer.getTree().setHeaderVisible(true);

    DataContentProvider dataContentProvider = new DataContentProvider();
    dataContentProvider.setExpandingNewContent(false);
    viewer.setContentProvider(dataContentProvider);

    viewer.setLabelProvider(new FilteringForMetricsLabelProvider(viewer, SWT.COLOR_BLUE));
    // Set layout data.
    viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
    viewer.setSorter(new FilteringSorter());
    return treeViewer;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected List<String[]> getExportableData() {
    List<String[]> result = super.getExportableData(EcoreUtil2.getProject(root).getName());

    @SuppressWarnings("unchecked")
    List<? extends EObject> allTaggedElements = (List<? extends EObject>) ((TreeData) getData()).getValidElements();

    for (EObject current : allTaggedElements) {
      CapellaElement me = (CapellaElement) current;
      // create the result
      List<String> row = new ArrayList<>();
      row.add(current.eClass().getName());
      row.add(me.getFullLabel());
      row.add(me.getLabel());
      // Add the metric
      Double metric = FilteringForMetricsLabelProvider.getFilteringRate(me);
      DecimalFormat df = new DecimalFormat();
      df.setMaximumFractionDigits(2);
      row.add(df.format(metric));
      // add the result
      result.add(row.toArray(new String[row.size()]));
    }

    return result;
  }
  
  @Override
  protected String getDefaultFileName() {
    return Messages.filtering_metrics_dialog_default_filename;
  }
}
