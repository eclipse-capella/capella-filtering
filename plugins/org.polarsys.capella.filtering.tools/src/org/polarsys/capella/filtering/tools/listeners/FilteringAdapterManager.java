/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.filtering.tools.listeners;

import org.eclipse.emf.common.notify.Adapter;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.platform.sirius.ted.DataNotifier;
import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringModel;

public class FilteringAdapterManager {
  private Adapter adapter;
  private static FilteringAdapterManager instance;

  private FilteringAdapterManager() {
    if (adapter == null) {
      adapter = new FilteringDataListener();
    }
  }

  public static FilteringAdapterManager getInstance() {
    if (instance == null) {
      instance = new FilteringAdapterManager();
    }
    return instance;
  }

  public void attachAdapter(DataNotifier dataNotifier) {
    dataNotifier.addAdapter(FilteringCriterion.class, adapter);
    dataNotifier.addAdapter(FilteringModel.class, adapter);
    dataNotifier.addAdapter(AssociatedFilteringCriterionSet.class, adapter);
    dataNotifier.addAdapter(ModelElement.class, adapter);
  }
  
  public void removeAdapter(DataNotifier dataNotifier) {
    dataNotifier.remove(adapter);
  }
}
