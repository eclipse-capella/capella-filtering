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
package org.polarsys.capella.filtering.sirius.ui.dialogs;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;

/**
 * Overriding DiagramElementsSelectionDialog to add a checkbox
 */
public class DiagramElementsSelectionDialogWithExtraCheckbox extends DiagramElementsSelectionDialog {

	private String checkboxMessage;
	private boolean checkboxSelected;
	private boolean selection;

	/**
	 * @param title
	 * @param message
	 */
	public DiagramElementsSelectionDialogWithExtraCheckbox(String title, String message, String checkboxMessage,
			boolean checkboxSelected) {
		super(title, message);
		this.checkboxMessage = checkboxMessage;
		this.checkboxSelected = checkboxSelected;
	}

	@Override
	public SiriusCriteriaSelectionDialog createCustomTreeSelectionDialog(Shell parent, EObject eObject) {
		Project project = CapellaProjectHelper.getProject(((DSemanticDiagram) eObject).getTarget());
		return new SiriusCriteriaSelectionDialog(parent, new AdvancedFeatureLabelProvider(), super.contentProvider,
				project, eObject);
	}

	public boolean getCheckboxSelection() {
		return selection;
	}
}
