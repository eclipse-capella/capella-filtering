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

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.filtering.tools.actions.FilteringMultipleCriteriaAssigningAction;

/**
 * 
 */
public class MultipleCriteriaAssigningCommandHandler extends AbstractHandler {

  private FilteringMultipleCriteriaAssigningAction action;

  public MultipleCriteriaAssigningCommandHandler() {
    this.action = new FilteringMultipleCriteriaAssigningAction();
  }

  @Override
  public Object execute(final ExecutionEvent event) throws ExecutionException {
    // Update the selection
    DDiagramEditor diagramEditor = (DDiagramEditor) HandlerUtil.getActiveEditor(event);
    ISelection selection = diagramEditor.getEditorSite().getSelectionProvider().getSelection();
    action.selectionChanged(null, selection);
    // Run the action
    action.run(null);
    return null;
  }
}