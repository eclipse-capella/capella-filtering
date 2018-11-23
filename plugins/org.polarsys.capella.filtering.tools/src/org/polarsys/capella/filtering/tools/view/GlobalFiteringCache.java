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
package org.polarsys.capella.filtering.tools.view;

import java.util.HashMap;
import java.util.Map;

import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringResult;

public class GlobalFiteringCache {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 4483023425603579476L;

	private boolean isEnabled;
	private Map<Project, FilteringResult> cache = new HashMap<>();
	

	public GlobalFiteringCache() {
		// TODO Auto-generated constructor stub
	}

	public void setCurrentFilteringResult(Project project, FilteringResult filteringResult) {
		cache.put(project, filteringResult);

	}

	public void clear() {
		cache.clear();

	}

	public FilteringCriterionSet get(Project project) {
		return cache.get(project);
	}

	public void remove(Project project) {
		cache.remove(project);
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void enable() {
		this.isEnabled = true;
	}

	public void disable() {
		this.isEnabled = false;
	}

	public void setEnabled(boolean enabled) {
		this.isEnabled = enabled;

	}
	@Override
	public String toString() {
		return cache.toString();
	}

}
