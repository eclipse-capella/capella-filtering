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
package org.polarsys.capella.filtering.transition.rules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.rules.AbstractUpdateRule;
import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 * 
 * 
 */
public class AssociatedCriterionSetRule extends AbstractUpdateRule {

  /**
   * {@inheritDoc}
   */
  @Override
  protected EClass getSourceType() {
    return FilteringPackage.Literals.ASSOCIATED_FILTERING_CRITERION_SET;
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    result.addAll(((AssociatedFilteringCriterionSet) source).getFilteringCriteria());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void apply(EObject element, IContext context) throws Exception {
    super.apply(element, context);
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    // If we are going to use attachRelated we need this method too.
    super.premicesRelated(element, needed);
    needed.addAll(createDefaultPrecedencePremices(element,
        FilteringPackage.Literals.FILTERING_CRITERION_SET__FILTERING_CRITERIA));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result,
        FilteringPackage.Literals.FILTERING_CRITERION_SET__FILTERING_CRITERIA, context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EClass getTargetType(EObject element, IContext context) {
    return FilteringPackage.Literals.ASSOCIATED_FILTERING_CRITERION_SET;
  }

}
