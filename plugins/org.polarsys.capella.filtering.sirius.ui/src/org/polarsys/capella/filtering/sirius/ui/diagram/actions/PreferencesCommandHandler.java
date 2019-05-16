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
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.polarsys.capella.filtering.tools.preferences.FilteringPreferencesPage;

/**
 * Open Product Line preferences page command handler
 * 
 * 
 */
public class PreferencesCommandHandler extends AbstractHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    // Create and open the Dialog that shows automatically Product Line
    // preferences
    PreferenceDialog dialog = PreferencesUtil.createPreferenceDialogOn(null, FilteringPreferencesPage.getID(), null,
        null);
    dialog.open();
    return null;
  }

}
