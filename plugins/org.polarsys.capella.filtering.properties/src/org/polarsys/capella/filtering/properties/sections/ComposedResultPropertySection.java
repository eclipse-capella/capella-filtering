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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.filtering.FilteringPackage;

/**
 * 
 */
public class ComposedResultPropertySection extends NamedElementSection {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null)
        && (eObjectToTest.eClass() == FilteringPackage.eINSTANCE.getComposedFilteringResult()));
  }

  // private CriteriaMultipleSemanticField features;
  //
  // @Override
  // public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
  // super.createControls(parent, aTabbedPropertySheetPage);
  // rootParentComposite.setLayout(new GridLayout());
  // rootParentComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
  // boolean displayedInWizard = isDisplayedInWizard();
  // features = new CriteriaMultipleSemanticField(getReferencesGroup(), Messages.filteringLabel+,
  // getWidgetFactory(), new CriterionSetFilteringCriteriaController());
  // features.setDisplayedInWizard(displayedInWizard);
  // }
  //
  // /**
  // * @see
  // org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
  // */
  // @Override
  // public void loadData(EObject capellaElement) {
  // super.loadData(capellaElement);
  // features.loadData(capellaElement, FilteringPackage.eINSTANCE.getFilteringCriterionSet_FilteringCriteria());
  // }
  //
  // /**
  // * {@inheritDoc}
  // */
  // @Override
  // public List<AbstractSemanticField> getSemanticFields() {
  // List<AbstractSemanticField> fields = new ArrayList<>();
  // fields.addAll(super.getSemanticFields());
  // fields.add(features);
  // return fields;
  // }

}
