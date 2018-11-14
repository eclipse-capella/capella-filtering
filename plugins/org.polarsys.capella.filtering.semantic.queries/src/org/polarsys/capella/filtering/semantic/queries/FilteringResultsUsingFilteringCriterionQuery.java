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
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringResult;
import org.polarsys.capella.filtering.FilteringResults;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

/**
 * Query that retrieves the configurations that contains a given filtering
 * feature.
 * 
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
			// Loop through configurations
			FilteringResults configurations = FilteringUtils.getFilteringResults(filteringCriterion);
			if (configurations != null) {
				for (FilteringResult configuration : configurations.getFilteringResults()) {
					List<FilteringCriterion> configurationVarFeatures = configuration.getFilteringCriteria();
					// Add it to the result if it contains the feature
					if ((configurationVarFeatures != null) && configurationVarFeatures.contains(filteringCriterion)) {
						result.add(configuration);
					}
				}
			}
		}
		return result;
	}
}