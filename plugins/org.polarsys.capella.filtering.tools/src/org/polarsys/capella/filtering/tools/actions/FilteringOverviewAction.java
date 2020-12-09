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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ui.actions.AbstractTigAction;
import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;
import org.polarsys.capella.filtering.tools.Messages;
import org.polarsys.capella.filtering.tools.dialogs.FilteringOverviewDialog;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

public class FilteringOverviewAction extends AbstractTigAction implements IActionDelegate {

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(IAction action) {
    // Filter AssociatedFilteringCriterionSet elements in the Dialog

    ModelElement selectedElement = getSelectedElement();
    Set<EObject> elementsWithAssociatedCriteria = FilteringUtils.getAllElementsWithAssociatedCriteria(selectedElement);
    List<EObject> associatedFeatureSetElements = new ArrayList<>();
    for (EObject eObject : elementsWithAssociatedCriteria) {
      if (eObject instanceof AssociatedFilteringCriterionSet) {
        associatedFeatureSetElements.add(eObject);
      }
    }
    elementsWithAssociatedCriteria.removeAll(associatedFeatureSetElements);

    // Create the dialog
    FilteringOverviewDialog dialog = new FilteringOverviewDialog(
        PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.filtering_dialog_title,
        Messages.filtering_dialog_msg, this.getSelectedElement(), elementsWithAssociatedCriteria);
    // Open it
    dialog.open();
  }
}
