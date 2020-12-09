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
package org.polarsys.capella.filtering.tools.dialogs;

import java.text.Collator;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * Product Line viewer sorter.
 * 
 * 
 */
public class FilteringSorter extends ViewerSorter {
  /**
   * Constructor.
   */
  public FilteringSorter() {
    super();
  }

  /**
   * Constructor.
   * 
   * @param collator
   */
  public FilteringSorter(Collator collator) {
    super(collator);
  }

  /**
   * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer, java.lang.Object,
   *      java.lang.Object)
   */
  @Override
  public int compare(Viewer viewer, Object object1, Object object2) {
    // Sorter Melody resource before melody fragment.
    if ((object1 instanceof Resource) && (object2 instanceof Resource)) {
      Resource resource1 = (Resource) object1;
      Resource resource2 = (Resource) object2;

      // Preconditions : must be melody resources.
      if (CapellaResourceHelper.isCapellaResource(resource1) && CapellaResourceHelper.isCapellaResource(resource2)) {
        int result = 0;
        boolean fragment1 = CapellaResourceHelper.isCapellaFragment(resource1.getURI());
        boolean fragment2 = CapellaResourceHelper.isCapellaFragment(resource2.getURI());
        if (fragment1 && !fragment2) {
          result = 1;
        } else if (!fragment1 && fragment2) {
          result = -1;
        }
        return result;
      }
    }
    return super.compare(viewer, object1, object2);
  }
}
