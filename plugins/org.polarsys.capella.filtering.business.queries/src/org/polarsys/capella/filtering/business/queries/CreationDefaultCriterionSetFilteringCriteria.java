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

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.filtering.FilteringPackage;

public class CreationDefaultCriterionSetFilteringCriteria extends CriterionSetFilteringCriteria {

  @Override
  public EClass getEClass() {
    return FilteringPackage.Literals.CREATION_DEFAULT_FILTERING_CRITERION_SET;
  }

}