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
package org.polarsys.capella.filtering.sirius.ui.diagram.actions;

import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.menus.UIElement;
import org.polarsys.capella.filtering.sirius.ui.FilteringSiriusUtils;

/**
 * 
 */
public class SelectCriteriaCommandHandler extends AbstractHandler implements IElementUpdater {

	private SelectCriteriaAction action;

	public SelectCriteriaCommandHandler() {
		this.action = new SelectCriteriaAction(FilteringSiriusUtils.getActivePage());
	}

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		action.getCommand().execute();
		return null;
	}

	@Override
	public void updateElement(UIElement element, Map parameters) {
		action.setWorkbenchPart(FilteringSiriusUtils.getActivePart());
	}
}
