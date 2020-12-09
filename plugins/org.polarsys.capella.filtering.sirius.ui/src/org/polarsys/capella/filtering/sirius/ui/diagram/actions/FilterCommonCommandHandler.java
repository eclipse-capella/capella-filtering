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
public class FilterCommonCommandHandler extends AbstractHandler implements IElementUpdater {

  private FilterCommonOptionalAction action;

  public FilterCommonCommandHandler() {
    this.action = new FilterCommonOptionalAction(FilteringSiriusUtils.getActivePage(),
        FilteringSiriusUtils.getActivePart(), true);
  }

  @Override
  @SuppressWarnings("synthetic-access")
  public Object execute(ExecutionEvent event) throws ExecutionException {
    FilteringUtils.executeCommand(new AbstractReadWriteCommand() {

      @Override
      public void run() {
        action.getCommand().execute();
        // Prepare it for the next execution with the inverse
        action.setShow(!action.getShow());
      }

      @Override
      public void commandRolledBack() {
        action.getCommand().undo();
        // Prepare it for the next execution with the inverse
        action.setShow(!action.getShow());
      }

      @Override
      public String toString() {
        return "Filter Common"; //$NON-NLS-1$
      }
    }, action.getSession());
    return null;
  }

  @Override
  public void updateElement(UIElement element, Map parameters) {
    // Update the toggle state
    element.setChecked(action.isAlreadyFiltered());
  }
}
