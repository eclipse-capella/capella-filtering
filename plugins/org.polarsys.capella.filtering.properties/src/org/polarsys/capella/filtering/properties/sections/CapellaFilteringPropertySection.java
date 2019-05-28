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
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.sections.AbstractSection;
import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.capella.filtering.properties.CapellaElementCriteria;
import org.polarsys.capella.filtering.properties.CapellaElementImplicitCriteria;
import org.polarsys.capella.filtering.properties.fields.IndirectCapellaElementCriteriaMultipleSemanticField;
import org.polarsys.capella.filtering.properties.fields.ReadOnlyMultipleSemanticField;
import org.polarsys.capella.filtering.tools.helpers.ViewpointHelper;

/**
 * The tab Filtering is enabled on all CapellaElements and none of Filtering ones except Union/Exception/Intersection
 */
public class CapellaFilteringPropertySection extends AbstractSection implements IFilter {

  private IndirectCapellaElementCriteriaMultipleSemanticField criteria;
  private ReadOnlyMultipleSemanticField implicitCriteria;

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObj = CapellaAdapterHelper.resolveSemanticObject(toTest);
    if (!(eObj instanceof CapellaElement)) {
      return false;
    }
    if (eObj != null && eObj.eClass() != null && FilteringPackage.eINSTANCE.equals(eObj.eClass().getEPackage())) {
      return false;
    }
    return ViewpointHelper.isViewpointActive(eObj);
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
   *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    rootParentComposite.setLayout(new GridLayout());
    rootParentComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    criteria = new IndirectCapellaElementCriteriaMultipleSemanticField(getReferencesGroup(), Messages.filteringLabel,
        getWidgetFactory(), new CapellaElementCriteria());
    criteria.setDisplayedInWizard(isDisplayedInWizard());

    implicitCriteria = new ReadOnlyMultipleSemanticField(getReferencesGroup(), Messages.implicitCriteriaLabel,
        getWidgetFactory(), new CapellaElementImplicitCriteria());
    implicitCriteria.setEnabled(false);
    implicitCriteria.setDisplayedInWizard(isDisplayedInWizard());
  }

  /**
   * load the form data from given capella element.<br>
   * Default implementation registers an EMF adapter to listen to model changes if displayed in a wizard.
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    // The second parameter is the semanticFeature but we do not include
    // anyone.
    // Filtering criterion is not direct attribute of CapellaElement
    criteria.loadData(capellaElement, null);
    implicitCriteria.loadData(capellaElement, null);
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
   *      org.eclipse.jface.viewers.ISelection)
   */
  @Override
  public void setInput(IWorkbenchPart part, ISelection selection) {
    if (selection instanceof StructuredSelection) {
      EObject selected = CapellaAdapterHelper
          .resolveSemanticObject(((StructuredSelection) selection).getFirstElement());
      if (selected instanceof CapellaElement) {
        if (selected.eClass().equals(CsPackage.eINSTANCE.getPart())) {
          boolean allowMultiplePart = TriStateBoolean.True
              .equals(CapellaProjectHelper.isReusableComponentsDriven((Part) selected));
          if (!allowMultiplePart) {
            AbstractType type = ((Part) selected).getAbstractType();
            if ((type != null) && !(type instanceof ConfigurationItem)) {
              super.setInput(part, new StructuredSelection(type));
              loadData((CapellaElement) type);
              return;
            }
          }
        }
        loadData((CapellaElement) selected);
      }
    }
    super.setInput(part, selection);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<>();

    fields.add(criteria);
    fields.add(implicitCriteria);

    return fields;
  }
}
