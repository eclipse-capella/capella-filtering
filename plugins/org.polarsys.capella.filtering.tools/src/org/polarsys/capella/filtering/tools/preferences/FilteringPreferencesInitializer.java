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
package org.polarsys.capella.filtering.tools.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.polarsys.capella.filtering.tools.FilteringToolsPlugin;

/**
 * 
 */
public class FilteringPreferencesInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = FilteringToolsPlugin.getDefault().getPreferenceStore();
		store.setDefault(FilteringPreferencesPage.FEATURES_DIAGRAM, false);
		store.setDefault(FilteringPreferencesPage.OPTIONAL_DIAGRAM, true);
		store.setDefault(FilteringPreferencesPage.SAVE_DECORATORS_DIAGRAM, true);
		store.setDefault(FilteringPreferencesPage.APPLICATION_PROJECT_WITH_DIFFERENT_ID, false);
	}

}
