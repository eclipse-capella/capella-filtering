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
package org.polarsys.capella.filtering.sirius.ui.decorators;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator;
import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.kitalpha.emde.model.EmdePackage;

/**
 * Listener for changes in CapellaElement Filtering Criteria attribute. It will refresh the decorator when notified.
 */
public class CriteriaChangeListener implements NotificationListener {

  IDecorator decorator;

  public CriteriaChangeListener(IDecorator decorator) {
    this.decorator = decorator;
  }

  public void notifyChanged(Notification evt) {
    // Refresh the decorator if the owned extensions reference that stores
    // AssociatedFilteringCriterionSet is modified, added or deleted
    if ((evt.getFeature() == EmdePackage.eINSTANCE.getExtensibleElement_OwnedExtensions())
        || (evt.getFeature() == FilteringPackage.eINSTANCE.getFilteringCriterionSet_FilteringCriteria())) {
      decorator.refresh();
    }
  }
}
