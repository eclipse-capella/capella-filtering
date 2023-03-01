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
package org.polarsys.capella.filtering.validation.constraints.quickfix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.preferences.IDeletePreferences;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;

public class MDCHK_DeleteElement_Resolver extends AbstractCapellaMarkerResolution {

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(IMarker marker_p) {

    // Get the element from the marker
    Iterator<EObject> it = getModelElements(marker_p).iterator();
    if (it.hasNext()) {
      EObject first = it.next();
      List<EObject> toDelete = getElementsToDelete(first);

      if ((toDelete != null) && !toDelete.isEmpty()) {
        // Execute the modification
        ExecutionManager executionManager = TransactionHelper.getExecutionManager(toDelete);
        CapellaDeleteCommand command = new CapellaDeleteCommand(executionManager, toDelete, true,
            IDeletePreferences.INSTANCE.isConfirmationRequired(), true);
        if (command.canExecute()) {
          command.execute();
        }
      }
    }
  }

  /**
   * Default implementation return the markerEObject directly. To override if needed
   */
  public List<EObject> getElementsToDelete(EObject markerEObject) {
    List<EObject> toDelete = new ArrayList<EObject>();
    if (markerEObject instanceof ExtensibleElement) {
      ExtensibleElement element = (ExtensibleElement) markerEObject;
      toDelete.add(element);
    }
    return toDelete;
  }
}
