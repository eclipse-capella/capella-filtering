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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.rules.AbstractUpdateRule;
import org.polarsys.capella.filtering.FilteringModel;
import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 * AssociatedFilteringCriterionSet transition needs rules for FilteringModel and FilteringCriterion for creating the
 * filtering features references.
 * 
 * 
 * 
 */
public class FilteringModelRule extends AbstractUpdateRule {

  /**
   * {@inheritDoc}
   */
  @Override
  protected EClass getSourceType() {
    return FilteringPackage.Literals.FILTERING_MODEL;
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    result_p.addAll(((FilteringModel) source_p).getOwnedFilteringCriteria());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    return (Project) context_p.get(ITransitionConstants.TRANSFORMATION_TARGET_ROOT);
  }

  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    return (Project) context_p.get(ITransitionConstants.TRANSFORMATION_TARGET_ROOT);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void apply(EObject element_p, IContext context_p) throws Exception {
    super.apply(element_p, context_p);
  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p,
        FilteringPackage.Literals.FILTERING_CRITERION_SET__FILTERING_CRITERIA, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EClass getTargetType(EObject element_p, IContext context_p) {
    return FilteringPackage.Literals.FILTERING_MODEL;
  }

}
