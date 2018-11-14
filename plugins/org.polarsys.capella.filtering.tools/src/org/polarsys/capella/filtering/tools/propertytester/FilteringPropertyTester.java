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
package org.polarsys.capella.filtering.tools.propertytester;

import java.util.Collection;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.filtering.tools.helpers.ViewpointHelper;

public class FilteringPropertyTester extends PropertyTester {

	public static final String VIEWPOINT_ACTIVATED = "isViewPointActivated"; //$NON-NLS-1$

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean test(Object receiver_p, String property_p, Object[] args_p, Object expectedValue_p) {
		if (property_p.equals(VIEWPOINT_ACTIVATED)) {
			return isViewpointActivated(receiver_p);
		}
		return false;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public final boolean isViewpointActivated(final Object receiver) {
		if (receiver instanceof EObject)
			return ViewpointHelper.isViewpointActive((EObject) receiver) && receiver instanceof CapellaElement;
		else if (receiver instanceof Collection)
			return ((Collection) receiver).stream().allMatch(element -> isViewpointActivated(element));
		return false;
	}
}
