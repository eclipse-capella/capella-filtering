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
package org.polarsys.capella.filtering.business.queries;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.filtering.FilteringPackage;

public class CreationDefaultCriterionSetFilteringCriteria extends CriterionSetFilteringCriteria {

  @Override
  public EClass getEClass() {
    return FilteringPackage.Literals.CREATION_DEFAULT_FILTERING_CRITERION_SET;
  }

}