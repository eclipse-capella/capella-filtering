/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IFilter;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.capella.filtering.FilteringResultSet;

public class DefaultNamedElementFilter implements IFilter {

  @Override
  public boolean select(Object toTest) {
    EObject result = CapellaAdapterHelper.resolveSemanticObject(toTest);

    if (result != null && result.eClass() != null && FilteringPackage.eINSTANCE.equals(result.eClass().getEPackage())) {
      return !(result instanceof FilteringCriterionSet) && !(result instanceof FilteringResultSet);
    }
    return false;
  }

}
