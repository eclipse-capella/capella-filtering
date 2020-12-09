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
package org.polarsys.capella.filtering.business.queries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringModel;
import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

/**
 * FilteringCriterionSet is an abstract EClass. Currently, when the engine is getting a business query contribution that
 * matches EClass and EStructuralFeatures it uses "equals" for EClass so no supertypes will match. Extend this class for
 * SuperTypes of FilteringCriterionSet.
 */
public class CriterionSetFilteringCriteria implements IBusinessQuery {

  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<EObject> getAvailableElements(EObject element) {
    // Check if the element is null
    if (null == element) {
      return Collections.emptyList();
    }

    // Get the filteringModels
    List<FilteringModel> filteringModels = FilteringUtils.getFilteringModels(element, true);

    List<EObject> availableElements = new ArrayList<>();
    for (FilteringCriterion vf : FilteringUtils.getOwnedFilteringCriteria(filteringModels)) {
      // Add features
      availableElements.add(vf);
    }
    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement,
   *      boolean)
   */
  public List<EObject> getCurrentElements(EObject element, boolean onlyGenerated) {
    List<EObject> currentElements = new ArrayList<>();
    if (element instanceof FilteringCriterionSet) {
      FilteringCriterionSet featureSet = (FilteringCriterionSet) element;
      if (featureSet.getFilteringCriteria() != null) {
        currentElements.addAll(featureSet.getFilteringCriteria());
      }
    }
    return currentElements;
  }

  /**
   * Method to override in SuperTypes {@inheritDoc}
   */
  public EClass getEClass() {
    return FilteringPackage.Literals.FILTERING_CRITERION_SET;
  }

  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(FilteringPackage.Literals.FILTERING_CRITERION_SET__FILTERING_CRITERIA);
  }
}
