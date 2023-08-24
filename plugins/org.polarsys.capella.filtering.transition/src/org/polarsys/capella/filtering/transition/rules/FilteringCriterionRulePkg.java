/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.filtering.transition.rules;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.transition.common.rules.AbstractUpdateRule;
import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class FilteringCriterionRulePkg extends AbstractUpdateRule {

  public FilteringCriterionRulePkg() {
    registerUpdatedAttribute(ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME);
  }
  
  @Override
  protected EClass getSourceType() {
    return FilteringPackage.Literals.FILTERING_CRITERION_PKG;
  }

  @Override
  public EClass getTargetType(EObject element, IContext context) {
    return FilteringPackage.Literals.FILTERING_CRITERION_PKG;
  }
  

}
