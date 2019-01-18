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
import org.polarsys.capella.filtering.FilteringCriterionSet;

/**
 * Query that retrieves criteria associated to a FilteringCriterionSet.
 * 
 * 
 */
public class CriteriaSetFilteringCriteriaQuery implements IQuery {

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<>();
    if (object instanceof FilteringCriterionSet) {
      for (Object o : ((FilteringCriterionSet) object).getFilteringCriteria()) {
        result.add(o);
      }
    }
    return result;
  }
}