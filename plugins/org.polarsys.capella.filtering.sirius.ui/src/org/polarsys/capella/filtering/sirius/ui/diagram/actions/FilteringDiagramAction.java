/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.filtering.sirius.ui.diagram.actions;

import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public abstract class FilteringDiagramAction extends DiagramAction {

  /**
   * @param workbenchPage
   */
  public FilteringDiagramAction(IWorkbenchPage workbenchPage) {
    super(workbenchPage.getActivePart());
  }

  /**
   * @return
   */
  public Session getSession() {
    IWorkbench workbench = PlatformUI.getWorkbench();
    if (workbench != null) {
      IWorkbenchWindow activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();
      if (activeWorkbenchWindow != null) {
        IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
        if (activePage.getActivePart() instanceof DDiagramEditor) {
          return ((DDiagramEditor) activePage.getActivePart()).getSession();
        }
      }
    }
    return null;
  }
}