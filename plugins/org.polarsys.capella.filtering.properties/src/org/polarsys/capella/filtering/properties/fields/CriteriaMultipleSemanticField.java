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
package org.polarsys.capella.filtering.properties.fields;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryLabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.ui.properties.controllers.IMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;
import org.polarsys.capella.filtering.tools.dialogs.FilteringCriteriaSelectionDialog;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;
import org.polarsys.capella.filtering.tools.utils.ui.CriteriaContentProvider;

public class CriteriaMultipleSemanticField extends MultipleSemanticField {

  /**
   * @param parent
   * @param label
   * @param widgetFactory
   * @param controller
   */
  public CriteriaMultipleSemanticField(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory,
      IMultipleSemanticFieldController controller) {
    super(parent, label, widgetFactory, controller);
  }

  /**
   * We use our own dialog to select the features. {@inheritDoc}
   */
  @Override
  protected List<EObject> openTransferDialog(Button button, List<EObject> currentElements,
      List<EObject> availableElements, String title, String message) {
    Session session = SessionManager.INSTANCE.getSession(_semanticElement);
    TransactionalEditingDomain transactionalEditingDomain = session.getTransactionalEditingDomain();
    Collection<Project> projects = FilteringUtils.getMainAndReferencedVariantProjects(_semanticElement);
    FilteringCriteriaSelectionDialog dialog = new FilteringCriteriaSelectionDialog(button.getShell(),
        new TransactionalAdapterFactoryLabelProvider(transactionalEditingDomain,
            ((AdapterFactoryEditingDomain) transactionalEditingDomain).getAdapterFactory()),
        new CriteriaContentProvider(), projects);
    dialog.setTitle(title);
    dialog.setMessage(message);
    dialog.setInput(projects);
    dialog.setInitialElementSelections(currentElements);
    if (dialog.open() != Window.OK) {
      // User press cancel or close => OperationCanceledException =>
      // Transaction is rolled back
      throw new OperationCanceledException();
    }
    // Return the selection
    List<EObject> checkedElements = new ArrayList<>();
    for (Object o : dialog.getCheckedElements()) {
      if (o instanceof EObject) {
        checkedElements.add((EObject) o);
      }
    }
    return checkedElements;
  }
}
