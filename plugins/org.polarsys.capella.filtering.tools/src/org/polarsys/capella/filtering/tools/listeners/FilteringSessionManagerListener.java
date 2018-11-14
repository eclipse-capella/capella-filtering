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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.polarsys.capella.common.platform.sirius.ted.DataNotifier;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringModel;

public class FilteringSessionManagerListener extends SessionManagerListener.Stub {

	static Adapter adapter;

	public FilteringSessionManagerListener() {

		if (adapter != null) {
			return;
		}

		adapter = new FilteringDataListener();
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

		DataNotifier dataNotifier = ((SemanticEditingDomain) updated.getTransactionalEditingDomain()).getDataNotifier();
		switch (notification) {
		case SessionListener.OPENED:
			dataNotifier.addAdapter(FilteringCriterion.class, adapter);
			dataNotifier.addAdapter(FilteringModel.class, adapter);
			dataNotifier.addAdapter(AssociatedFilteringCriterionSet.class, adapter);
			break;
		case SessionListener.CLOSED:
			dataNotifier.remove(adapter);
			break;
		}
	}
}
