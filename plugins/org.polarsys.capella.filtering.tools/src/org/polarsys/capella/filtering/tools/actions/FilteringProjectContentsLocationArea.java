/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.filtering.tools.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.CapellaProjectContentsLocationArea;

public class FilteringProjectContentsLocationArea extends CapellaProjectContentsLocationArea {

  public FilteringProjectContentsLocationArea(IErrorMessageReporter reporter, Composite composite) {
    super(reporter, composite);
  }
  
  @Override
  public String checkValidLocation() {
    String isValid = super.checkValidLocation();
    if(isValid == null) {
      IProject project = getSelectedProject();
      if(project != null) {
        String selectedPrjloc = project.getLocation().toOSString();
        String trgtPrjLoc = getProjectLocation();
        if(trgtPrjLoc.startsWith(selectedPrjloc)) {
          return "Generation is not allowed inside the source project";
        }
      }
    }
    return isValid;
  }
  
  private IProject getSelectedProject() {
    ISelectionService selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
    ISelection sel = selectionService.getSelection("capella.project.explorer");
    Object selectedObject = sel;
    if (sel instanceof IStructuredSelection) {
      selectedObject = ((IStructuredSelection) sel).getFirstElement();
      if(selectedObject instanceof EObject) {
        return EcoreUtil2.getProject((EObject)selectedObject);
      }
      else if (selectedObject instanceof IAdaptable) {
        IResource res = (IResource) ((IAdaptable) selectedObject).getAdapter(IResource.class);
        return res.getProject();
      }
    }
    return null;
  }
}
