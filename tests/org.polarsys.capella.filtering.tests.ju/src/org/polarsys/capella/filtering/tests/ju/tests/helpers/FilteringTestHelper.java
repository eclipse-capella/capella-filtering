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

package org.polarsys.capella.filtering.tests.ju.tests.helpers;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.filtering.FilteringModel;
import org.polarsys.capella.filtering.FilteringResults;

public class FilteringTestHelper {

  /**
   * Find the first object of type {@link FilteringResults} in given resources.
   */
  public static FilteringResults getFilteringResults(Collection<Resource> semanticResources) {
    Iterator<?> allContentsIterator = EcoreUtil.getAllContents(semanticResources);
    while (allContentsIterator.hasNext()) {
      Object object = allContentsIterator.next();
      if (object instanceof FilteringResults) {
        return (FilteringResults) object;
      }
    }
    return null;
  }

  /**
   * Find the first object of type {@link FilteringModel} in given resources.
   */
  public static FilteringModel getFilteringModel(Collection<Resource> semanticResources) {
    Iterator<?> allContentsIterator = EcoreUtil.getAllContents(semanticResources);
    while (allContentsIterator.hasNext()) {
      Object object = allContentsIterator.next();
      if (object instanceof FilteringModel) {
        return (FilteringModel) object;
      }
    }
    return null;
  }

  /**
   * Find the first object of type {@link FilteringModel} in given resources.
   */
  public static Project getCapellaProject(Collection<Resource> semanticResources) {
    Iterator<?> allContentsIterator = EcoreUtil.getAllContents(semanticResources);
    while (allContentsIterator.hasNext()) {
      Object object = allContentsIterator.next();
      if (object instanceof Project) {
        return (Project) object;
      }
    }
    return null;
  }

  public static boolean areEqualIgnoreOrder(Collection<?> firstList, Collection<?> secondList) {
    return (firstList.size() == secondList.size()) && (firstList.containsAll(secondList));

  }

}
