/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.filtering.AbstractFilteringResult;

/**
 * This class represents whether a filtering result is activated on the Diagram Filtering Criteria Visibility view
 */
public class FilteringResultState {
  private boolean enabled;
  private AbstractFilteringResult filteringResult;
  
  public FilteringResultState (boolean activated, AbstractFilteringResult filteringResult) {
    this.enabled = activated;
    this.filteringResult = filteringResult;
  }
  
  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean activated) {
    this.enabled = activated;
  }

  public AbstractFilteringResult getFilteringResult() {
    return filteringResult;
  }

  public void setFilteringResult(AbstractFilteringResult filteringResult) {
    this.filteringResult = filteringResult;
  }
}
