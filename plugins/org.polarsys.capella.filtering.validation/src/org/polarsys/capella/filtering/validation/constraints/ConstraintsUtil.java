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
package org.polarsys.capella.filtering.validation.constraints;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

/**
 * 
 */
public class ConstraintsUtil {

  private ConstraintsUtil() {
    //
  }

  public static String getNameForMessage(EObject o) {
    String name = ""; //$NON-NLS-1$
    if (o instanceof NamedElement) {
      name = ((NamedElement) o).getName();
    }
    return name + " (" + o.eClass().getName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * Which features from listOne are missing in listTwo. Examples: listOne [A] listTwo [B] return [A]. listOne [A]
   * listTwo [A, B] return []. listOne [A, B] listTwo [A] return [B]
   * 
   * @param listOne
   * @param listTwo
   * @return
   */
  public static List<FilteringCriterion> missingFilteringCriteria(List<FilteringCriterion> listOne,
      List<FilteringCriterion> listTwo) {
    List<FilteringCriterion> missingFeatures = new ArrayList<>();
    // check they are missing
    if (!listTwo.containsAll(listOne)) {
      // prepare the list
      for (FilteringCriterion var : listOne) {
        if (!listTwo.contains(var)) {
          missingFeatures.add(var);
        }
      }
    }
    return missingFeatures;
  }

  /**
   * @param eObj
   * @param currentConstraintData
   * @return
   */
  public static List<FilteringCriterion> getAssociatedCriteria(EObject eObj,
      Map<EObject, List<FilteringCriterion>> currentConstraintData) {
    if (currentConstraintData.containsKey(eObj)) {
      return currentConstraintData.get(eObj);
    }
    List<FilteringCriterion> associatedCriteria = FilteringUtils.getAssociatedCriteria(eObj);
    currentConstraintData.put(eObj, associatedCriteria);
    return associatedCriteria;
  }
}
