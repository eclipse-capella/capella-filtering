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
package org.polarsys.capella.filtering.tools.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ui.actions.AbstractTigAction;
import org.polarsys.capella.filtering.tools.dialogs.FilteringMetricsDialog;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

public class FilteringMetricsAction extends AbstractTigAction implements IActionDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run(IAction action) {

		List<EObject> result = new ArrayList<>();

		// Provide all the elements to the dialog
		TreeIterator<EObject> it = getSelectedElement().eAllContents();
		EObject current = null;
		while (it.hasNext()) {
			current = it.next();
			if (!FilteringUtils.isInstanceOfFilteringExcludedElements(current)) {
				result.add(current);
			}
		}

		// Create the dialog
		FilteringMetricsDialog dialog = new FilteringMetricsDialog(
				PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.FilteringMetricsAction_0, // $NON-NLS-1$
				Messages.FilteringMetricsAction_1, this.getSelectedElement(), result); // $NON-NLS-1$

		// Open it
		dialog.open();
	}
}