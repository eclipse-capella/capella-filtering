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
package org.polarsys.capella.filtering.transition.handlers.traceability;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.TraceabilityConfiguration;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 * 
 */
public class ExtensionTraceabilityConfiguration extends TraceabilityConfiguration {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initHandlers(IContext fContext) {
		super.initHandlers(fContext);
		addHandler(fContext, new ExtensionTraceabilityHandler());
	}

	@Override
	public boolean useHandlerForAttachment(EObject source, EObject target, ITraceabilityHandler handler,
			IContext context) {
		// TODO remove FilteringModel and FilteringCriterion TransfoLinks. Now
		// it creates links to perform the diff but it doesnt make sense to have
		// these elements
		// after that. AssociatedFilteringCriterionSet transfo links make sense
		// to maintain them.
		return true;
	}

}
