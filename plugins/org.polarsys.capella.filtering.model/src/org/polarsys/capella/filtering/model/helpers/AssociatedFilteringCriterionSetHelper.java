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
package org.polarsys.capella.filtering.model.helpers;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;

/**
 * @generated
 */
public class AssociatedFilteringCriterionSetHelper {

  private static final AssociatedFilteringCriterionSetHelper instance = new AssociatedFilteringCriterionSetHelper();

  /**
   * @generated
   */
  public static AssociatedFilteringCriterionSetHelper getInstance() {
    return instance;
  }

  /**
   * @generated
   */
  public Object doSwitch(AssociatedFilteringCriterionSet object, EStructuralFeature feature) {
    // handle derivated feature

    // delegate to parent helper
    return org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper.getInstance()
        .doSwitch(object, feature);

  }

}