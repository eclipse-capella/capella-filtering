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

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.handlers.session.SessionHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 * 
 */
public class ExtensionTraceabilityHandler implements ITraceabilityHandler {

	/**
	 * {@inheritDoc}
	 */
	public void attachTraceability(EObject sourceElement, EObject targetElement, IContext context) {
		// Nothing
	}

	/**
	 * {@inheritDoc}
	 */
	public List<EObject> retrieveTracedElements(EObject source, IContext context) {
		return Collections.emptyList();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<EObject> retrieveSourceElements(EObject source, IContext context) {
		return Collections.emptyList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @deprecated
	 */
	@Deprecated
	public List<EObject> retrieveTracedElements(EObject source, IContext context, EClass clazz) {
		return Collections.emptyList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @deprecated
	 */
	@Deprecated
	public boolean isTraced(EObject element, IContext context) {
		return !retrieveTracedElements(element, context).isEmpty();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @deprecated
	 */
	@Deprecated
	public String getId(EObject element, IContext context) {
		return SessionHandlerHelper.getInstance(context).getId(element, context);
	}

	/**
	 * {@inheritDoc}
	 */
	public IStatus init(IContext context) {
		return Status.OK_STATUS;
	}

	/**
	 * {@inheritDoc}
	 */
	public IStatus dispose(IContext context) {
		return Status.OK_STATUS;
	}
}
