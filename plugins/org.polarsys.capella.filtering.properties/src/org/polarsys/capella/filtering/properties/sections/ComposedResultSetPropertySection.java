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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.filtering.ExclusionFilteringResultSet;
import org.polarsys.capella.filtering.IntersectionFilteringResultSet;
import org.polarsys.capella.filtering.UnionFilteringResultSet;
import org.polarsys.capella.filtering.properties.CapellaElementImplicitCriteria;
import org.polarsys.capella.filtering.properties.fields.ReadOnlyMultipleSemanticField;

/**
 * Class for Exclusion/Intersection/Unions
 */
public class ComposedResultSetPropertySection extends CriterionSetPropertySection {

  private ReadOnlyMultipleSemanticField implicitCriteria;
  
  @Override
  public boolean select(Object toTest) {
    EObject obj = selection(toTest);
    return (obj instanceof ExclusionFilteringResultSet || obj instanceof IntersectionFilteringResultSet
        || obj instanceof UnionFilteringResultSet);
  }

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    implicitCriteria = new ReadOnlyMultipleSemanticField(getReferencesGroup(), Messages.implicitCriteriaLabel,
        getWidgetFactory(), new CapellaElementImplicitCriteria());
    implicitCriteria.setEnabled(false);
    implicitCriteria.setDisplayedInWizard(isDisplayedInWizard());
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);
    implicitCriteria.loadData(capellaElement, null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<>();
    fields.addAll(super.getSemanticFields());
    fields.add(implicitCriteria);
    return fields;
  }
}
