/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.filtering.properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.ListExt;
import org.polarsys.capella.filtering.ComposedFilteringResult;
import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.capella.filtering.FilteringResultSet;

public class FilteringResultBusinessQuery implements IBusinessQuery {

  @Override
  public List<EObject> getAvailableElements(EObject element) {
    List<EObject> availableElements = new ArrayList<EObject>();
    SystemEngineering systemEngineering = SystemEngineeringExt.getSystemEngineering((FilteringResultSet) element);
    if (null != systemEngineering) {
      for (EObject elt : EObjectExt.getAll(systemEngineering, FilteringPackage.Literals.ABSTRACT_FILTERING_RESULT)) {
        availableElements.add((CapellaElement) elt);
      }
    }

    // Remove all FilteringResults that are using the element or one of its referencing FilteringResults (recursively)
    HashSet<EObject> toExclude = new HashSet<EObject>();
    EObject container = element.eContainer();
    toExclude.add(container);

    LinkedList<EObject> toVisit = new LinkedList<EObject>();
    HashSet<EObject> visited = new HashSet<EObject>();
    toVisit.add(container);
    while (!toVisit.isEmpty()) {
      EObject current = toVisit.removeFirst();
      if (current instanceof ComposedFilteringResult) {
        List<FilteringResultSet> referencings = EObjectExt.getReferencers(current,
            FilteringPackage.Literals.FILTERING_RESULT_SET__FILTERING_RESULTS);
        for (EObject ref : referencings) {
          EObject refContainer = ref.eContainer();
          toExclude.add(refContainer);
          if (!visited.contains(refContainer)) {
            toVisit.add(refContainer);
          }
        }
      }
    }

    availableElements.removeAll(toExclude);
    return availableElements;
  }

  @Override
  public List<EObject> getCurrentElements(EObject element, boolean onlyGenerated) {
    if (element instanceof FilteringResultSet) {
      return Collections.unmodifiableList(((FilteringResultSet) element).getFilteringResults());
    }
    return Collections.emptyList();
  }

  @Override
  public EClass getEClass() {
    return FilteringPackage.Literals.FILTERING_RESULT_SET;
  }

  @Override
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(FilteringPackage.Literals.FILTERING_RESULT_SET__FILTERING_RESULTS);
  }

}
