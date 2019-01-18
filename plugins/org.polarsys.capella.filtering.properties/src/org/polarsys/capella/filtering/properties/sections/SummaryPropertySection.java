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
package org.polarsys.capella.filtering.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.core.properties.fields.BasicElementGroup;
import org.polarsys.capella.core.data.core.properties.sections.ModelElementSection;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.filtering.FilteringPackage;

/**
 * To override
 * 
 * 
 */
public class SummaryPropertySection extends ModelElementSection {

  protected BasicElementGroup namedElementGroup;

  /**
   * Override this method {@inheritDoc}
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == FilteringPackage.eINSTANCE.getFilteringModel()));
  }

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    namedElementGroup = new BasicElementGroup(rootParentComposite, getWidgetFactory(), false, true);
    namedElementGroup.setDisplayedInWizard(isDisplayedInWizard());
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    namedElementGroup.loadData(capellaElement);
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
   *      org.eclipse.jface.viewers.ISelection)
   */
  @Override
  public void setInput(IWorkbenchPart part, ISelection selection) {
    EObject newEObject = super.setInputSelection(part, selection);
    loadData((CapellaElement) newEObject);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<>();

    fields.add(namedElementGroup);

    return fields;
  }
}
