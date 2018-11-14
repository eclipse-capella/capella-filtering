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

public class FilterOptionalsCommandHandler extends AbstractHandler implements IElementUpdater {

	private FilterCommonOptionalAction action;

	public FilterOptionalsCommandHandler() {
		action = new FilterCommonOptionalAction(FilteringSiriusUtils.getActivePage(),
				FilteringSiriusUtils.getActivePart(), false);
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		FilteringUtils.executeCommand(new AbstractReadWriteCommand() {
			@Override
			@SuppressWarnings("synthetic-access")
			public void run() {
				action.getCommand().execute();
				// Prepare it for the next execution with the inverse
				action.setShow(!action.getShow());
			}

			@SuppressWarnings("synthetic-access")
			@Override
			public void commandRolledBack() {
				action.getCommand().undo();
				// Prepare it for the next execution with the inverse
				action.setShow(!action.getShow());
			}

			@Override
			public String toString() {
				return "Filter Optionals"; //$NON-NLS-1$
			}
		}, action.getSession());

		return null;
	}

	@Override
	public void updateElement(UIElement element, Map parameters) {
		action.setWorkbenchPart(FilteringSiriusUtils.getActivePart());
		// Update the toggle state
		element.setChecked(action.isAlreadyFiltered());
	}
}
