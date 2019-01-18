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
package org.polarsys.capella.filtering.transition.rules;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.transition.common.rules.AbstractUpdateRule;
import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * AssociatedFilteringCriterionSet transition needs rules for FilteringModel and FilteringCriterion for creating the
 * filtering features references.
 * 
 * 
 * 
 */
public class FilteringCriterionRule extends AbstractUpdateRule {

  public FilteringCriterionRule() {
    registerUpdatedAttribute(ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EClass getSourceType() {
    return FilteringPackage.Literals.FILTERING_CRITERION;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void apply(EObject element_p, IContext context_p) throws Exception {
    super.apply(element_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EClass getTargetType(EObject element_p, IContext context_p) {
    return FilteringPackage.Literals.FILTERING_CRITERION;
  }

}
