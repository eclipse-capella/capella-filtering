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
package org.polarsys.capella.filtering.semantic.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

public class CapellaElementImplicitQuery implements IQuery {

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.query.IQuery#compute(java.lang.Object)
   */
  @Override
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<>();
    // Show for any melody element except FilteringCriterionSet that has his
    // own semantic browser contribution for filtering features
    if ((object instanceof EObject) && !(object instanceof FilteringCriterionSet)) {
      for (Object o : FilteringUtils.getImplicitAssociatedCriteria((EObject) object)) {
        result.add(o);
      }
    }
    return result;
  }
}
