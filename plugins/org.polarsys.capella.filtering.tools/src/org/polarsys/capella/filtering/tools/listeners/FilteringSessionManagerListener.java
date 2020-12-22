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
package org.polarsys.capella.filtering.tools.listeners;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.polarsys.capella.common.platform.sirius.ted.DataNotifier;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticResourceSet;
import org.polarsys.capella.filtering.tools.helpers.ViewpointHelper;
import org.polarsys.kitalpha.ad.services.manager.ViewpointManager;
import org.polarsys.kitalpha.resourcereuse.model.Resource;

public class FilteringSessionManagerListener extends SessionManagerListener.Stub {
  public FilteringSessionManagerListener() {
    // Do nothing
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void notify(Session updated, int notification) {
    super.notify(updated, notification);

    if (updated == null) {
      return;
    }

    TransactionalEditingDomain transactionalEditingDomain = updated.getTransactionalEditingDomain();
    if (transactionalEditingDomain instanceof SemanticEditingDomain) {
      SemanticEditingDomain semanticED = (SemanticEditingDomain) transactionalEditingDomain;
      DataNotifier dataNotifier = semanticED.getDataNotifier();
      SemanticResourceSet rs = semanticED.getResourceSet();
      switch (notification) {
      case SessionListener.OPENED:
        ViewpointManager.getInstance(rs).addListener(new ViewpointManager.Listener2() {
          @Override
          public void handleActivation(Resource vp) {
            FilteringAdapterManager.getInstance().attachAdapter(dataNotifier);
          }

          @Override
          public void handleInactivation(Resource vp) {
            FilteringAdapterManager.getInstance().removeAdapter(dataNotifier);
          }

          @Override
          public void handleReferencing(Resource vp) {
            FilteringAdapterManager.getInstance().attachAdapter(dataNotifier);
          }

          @Override
          public void handleUnReferencing(Resource vp) {
            // Removing adapter will be done with session closed event
          }
        });
        if (ViewpointHelper.isViewpointActive(rs)) {
          FilteringAdapterManager.getInstance().attachAdapter(dataNotifier);
        }
        break;
      case SessionListener.CLOSED:
        FilteringAdapterManager.getInstance().removeAdapter(dataNotifier);
        break;
      }
    }
  }

}
