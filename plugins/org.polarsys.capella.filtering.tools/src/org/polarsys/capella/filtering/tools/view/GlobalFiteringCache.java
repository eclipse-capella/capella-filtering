/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.filtering.tools.view;

import java.util.HashMap;
import java.util.Map;

import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.filtering.AbstractFilteringResult;

public class GlobalFiteringCache {
  @SuppressWarnings("unused")
  private static final long serialVersionUID = 4483023425603579476L;
  private Map<Project, FilteringResultState> cache = new HashMap<>();

  public GlobalFiteringCache() {
    // TODO Auto-generated constructor stub
  }

  public void setCurrentFilteringResult(Project project, AbstractFilteringResult filteringResult) {
    FilteringResultState filteringResultState = cache.get(project);
    if (filteringResultState != null) {
      filteringResultState.setFilteringResult(filteringResult);
    } else {
      cache.put(project, new FilteringResultState(true, filteringResult));
    }
  }
  
  public AbstractFilteringResult getCurrentFilteringResult(Project project) {
    FilteringResultState filteringResultState = cache.get(project);
    if (filteringResultState != null) {
      return filteringResultState.getFilteringResult();
    }
    return null;
  }

  public void clear() {
    cache.clear();
  }

  public FilteringResultState get(Project project) {
    return cache.get(project);
  }

  public void remove(Project project) {
    cache.remove(project);
  }

  public boolean isEnabled(Project project) {
    if (cache.get(project) == null) {
      return false;
    }
    return cache.get(project).isEnabled();
  }

  public void enable(Project project) {
   setEnabled(project, true);
  }

  public void disable(Project project) {
    setEnabled(project, false);
  }

  public void setEnabled(Project project, boolean enabled) {
    FilteringResultState filteringResultState = cache.get(project);
    if (filteringResultState != null) {
      filteringResultState.setEnabled(enabled);
    } else {
      cache.put(project, new FilteringResultState(enabled, null));
    }
  }

  @Override
  public String toString() {
    return cache.toString();
  }

}
