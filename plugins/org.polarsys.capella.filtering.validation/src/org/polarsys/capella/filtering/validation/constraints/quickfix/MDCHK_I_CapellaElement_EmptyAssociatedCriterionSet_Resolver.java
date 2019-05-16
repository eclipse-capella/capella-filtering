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
package org.polarsys.capella.filtering.validation.constraints.quickfix;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

/**
 * 
 */
public class MDCHK_I_CapellaElement_EmptyAssociatedCriterionSet_Resolver extends MDCHK_DeleteElement_Resolver {

  /**
   * Remove AssociatedFilteringCriterionSet
   */
  @Override
  public List<EObject> getElementsToDelete(EObject markerEObject) {
    List<EObject> toDelete = new ArrayList<EObject>();
    if (markerEObject instanceof CapellaElement) {
      AssociatedFilteringCriterionSet featureSet = FilteringUtils
          .getAssociatedFilteringCriterionSet((CapellaElement) markerEObject);
      if (featureSet != null) {
        toDelete.add(featureSet);
      }
    }
    return toDelete;
  }
}