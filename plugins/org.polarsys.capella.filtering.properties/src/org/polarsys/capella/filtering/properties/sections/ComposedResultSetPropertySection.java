/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.filtering.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.core.properties.sections.DefaultNamedElementSection;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;
import org.polarsys.capella.filtering.ExclusionFilteringResultSet;
import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.capella.filtering.IntersectionFilteringResultSet;
import org.polarsys.capella.filtering.UnionFilteringResultSet;

/**
 * Class for Exclusion/Intersection/Unions
 */
public class ComposedResultSetPropertySection extends DefaultNamedElementSection {

  MultipleSemanticField filteringResults;
  
  @Override
  public boolean select(Object toTest) {
    EObject obj = selection(toTest);
    return (obj instanceof ExclusionFilteringResultSet || obj instanceof IntersectionFilteringResultSet
        || obj instanceof UnionFilteringResultSet);
  }

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    parent.setLayout(new GridLayout());
    parent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    
    filteringResults = new MultipleSemanticField(getReferencesGroup(),
        Messages.PropertySection_filteringResults, getWidgetFactory(), new AbstractMultipleSemanticFieldController() {
          @Override
          protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
            return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), FilteringPackage.Literals.FILTERING_RESULT_SET__FILTERING_RESULTS);
          }
          @Override
          public List<EObject> writeOpenValues(EObject semanticElement, EStructuralFeature semanticFeature,
              List<EObject> values) {
            return super.writeOpenValues(semanticElement, semanticFeature, values);
          }
        });
    filteringResults.setDisplayedInWizard(isDisplayedInWizard());
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);
    filteringResults.loadData(capellaElement, FilteringPackage.Literals.FILTERING_RESULT_SET__FILTERING_RESULTS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<>();
    fields.addAll(super.getSemanticFields());
    fields.add(filteringResults);
    return fields;
  }
}