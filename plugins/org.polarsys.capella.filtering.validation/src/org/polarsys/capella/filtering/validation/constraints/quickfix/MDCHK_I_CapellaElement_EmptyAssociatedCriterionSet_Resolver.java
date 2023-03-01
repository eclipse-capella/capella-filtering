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
package org.polarsys.capella.filtering.validation.constraints.quickfix;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;

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
    if (markerEObject instanceof ExtensibleElement) {
      AssociatedFilteringCriterionSet featureSet = FilteringUtils
          .getAssociatedFilteringCriterionSet((ExtensibleElement) markerEObject);
      if (featureSet != null) {
        toDelete.add(featureSet);
      }
    }
    return toDelete;
  }
}
