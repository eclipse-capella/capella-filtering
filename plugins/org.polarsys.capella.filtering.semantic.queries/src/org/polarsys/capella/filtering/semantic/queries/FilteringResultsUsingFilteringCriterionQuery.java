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
package org.polarsys.capella.filtering.semantic.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.filtering.AbstractFilteringResult;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringResults;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

/**
 * Query that retrieves the filtering results that contains a given filtering criterion
 * 
 */
public class FilteringResultsUsingFilteringCriterionQuery implements IQuery {

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<>();
    if ((object instanceof FilteringCriterion)) {
      FilteringCriterion filteringCriterion = (FilteringCriterion) object;

      // Loop through filtering Results
      FilteringResults filteringResults = FilteringUtils.getFilteringResults(filteringCriterion);
      if (filteringResults != null) {
        List<AbstractFilteringResult> allFilteringResults = FilteringUtils.getAllFilteringResults(filteringResults);
        for (AbstractFilteringResult abstractFilteringResult : allFilteringResults) {
          FilteringCriterionSet FilteringCriterionSet = abstractFilteringResult.computeFilteringCriterionSet();
          List<FilteringCriterion> filteringCriteria = FilteringCriterionSet.getFilteringCriteria();
          // Add it to the result if it contains the feature
          if ((filteringCriteria != null) && filteringCriteria.contains(filteringCriterion)) {
            result.add(abstractFilteringResult);
          }
        }
      }
    }
    return result;
  }
}