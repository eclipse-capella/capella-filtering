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
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.core.properties.sections.DefaultNamedElementSection;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.filtering.ExclusionFilteringResultSet;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.capella.filtering.IntersectionFilteringResultSet;
import org.polarsys.capella.filtering.UnionFilteringResultSet;
import org.polarsys.capella.filtering.properties.CriterionSetFilteringCriteriaController;
import org.polarsys.capella.filtering.properties.fields.CriteriaMultipleSemanticField;

/**
 * Class to extend by FilteringCriterionSet supertypes
 */
public class CriterionSetPropertySection extends DefaultNamedElementSection {

  private CriteriaMultipleSemanticField criteria;

  @Override
  public boolean select(Object toTest) {
    EObject obj = selection(toTest);
    if (obj instanceof ExclusionFilteringResultSet || obj instanceof IntersectionFilteringResultSet
        || obj instanceof UnionFilteringResultSet) {
      return false;
    }
    return obj instanceof FilteringCriterionSet;
  }

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    rootParentComposite.setLayout(new GridLayout());
    rootParentComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    criteria = new CriteriaMultipleSemanticField(getReferencesGroup(), Messages.filteringLabel, getWidgetFactory(),
        new CriterionSetFilteringCriteriaController());
    criteria.setDisplayedInWizard(isDisplayedInWizard());
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);
    criteria.loadData(capellaElement, FilteringPackage.eINSTANCE.getFilteringCriterionSet_FilteringCriteria());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<>();
    fields.addAll(super.getSemanticFields());
    fields.add(criteria);
    return fields;
  }
}
