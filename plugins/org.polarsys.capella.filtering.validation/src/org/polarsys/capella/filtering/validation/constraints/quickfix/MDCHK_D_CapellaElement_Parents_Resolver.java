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
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;
import org.polarsys.capella.filtering.validation.constraints.ConstraintsUtil;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;

/**
 * 
 */
public class MDCHK_D_CapellaElement_Parents_Resolver extends AbstractCapellaMarkerResolution {

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(IMarker marker_p) {

    // Get the element from the marker
    Iterator<EObject> it = getModelElements(marker_p).iterator();
    if (it.hasNext()) {
      EObject first = it.next();
      if (first instanceof ExtensibleElement) {
        final ExtensibleElement element = (ExtensibleElement) first;

        // Loop through childs to collect their Filtering Criteria
        List<FilteringCriterion> childsVarFeatures = getChildsFeatures(element);

        // Calculate which features are missing
        List<FilteringCriterion> elementVarFeatures = FilteringUtils.getAssociatedCriteria(element);
        final List<FilteringCriterion> featuresToAdd = ConstraintsUtil.missingFilteringCriteria(childsVarFeatures,
            elementVarFeatures);

        // Execute the modification
        AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
          public void run() {
            FilteringUtils.addAssociatedCriteria(element, featuresToAdd);
          }
        };
        FilteringUtils.executeCommand(command, element);
      }
    }
  }

  /**
   * Loop through childs to collect their Filtering Criteria
   */
  public static List<FilteringCriterion> getChildsFeatures(EObject element) {
    List<FilteringCriterion> childsVarFeatures = new ArrayList<FilteringCriterion>();
    Iterator<EObject> childs = element.eAllContents();
    while (childs.hasNext()) {
      EObject child = childs.next();
      if (child instanceof ExtensibleElement) {
        if (!FilteringUtils.isInstanceOfFilteringExcludedElements(child)) {
          for (FilteringCriterion var : FilteringUtils.getAssociatedCriteria(child)) {
            if (!childsVarFeatures.contains(var)) {
              childsVarFeatures.add(var);
            }
          }
        }
      }
    }
    return childsVarFeatures;
  }
}
