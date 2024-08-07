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
package org.polarsys.capella.filtering.properties;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.filtering.FilteringPackage;

public class UnionFilteringResultBusinessQuery extends FilteringResultBusinessQuery {

  @Override
  public EClass getEClass() {
    return FilteringPackage.Literals.UNION_FILTERING_RESULT_SET;
  }

}
