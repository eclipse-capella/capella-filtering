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
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.filtering.sirius.ui.FilteringSiriusUtils;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

/**
 * 
 */
public class SelectHiddenCriteriaCommandHandler extends AbstractHandler implements IElementUpdater {

	private SelectHiddenCriteriaAction action;

	public SelectHiddenCriteriaCommandHandler() {
		this.action = new SelectHiddenCriteriaAction(FilteringSiriusUtils.getActivePage(),
				FilteringSiriusUtils.getActivePart());
	}

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		FilteringUtils.executeCommand(new AbstractReadWriteCommand() {
			@Override
			@SuppressWarnings("synthetic-access")
			public void run() {
				action.getCommand().execute();
			}

			@SuppressWarnings("synthetic-access")
			@Override
			public void commandRolledBack() {
				action.getCommand().undo();
			}

			@Override
			public String toString() {
				return "Hide features"; //$NON-NLS-1$
			}
		}, action.getSession());
		return null;
	}

	@Override
	public void updateElement(UIElement element, Map parameters) {
		action.setWorkbenchPart(FilteringSiriusUtils.getActivePart());
		element.setIcon(action.getImage());
	}
}
