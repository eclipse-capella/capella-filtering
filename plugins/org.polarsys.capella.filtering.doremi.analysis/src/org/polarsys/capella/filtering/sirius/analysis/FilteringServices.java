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
package org.polarsys.capella.filtering.sirius.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringModel;
import org.polarsys.capella.filtering.FilteringResult;
import org.polarsys.capella.filtering.FilteringResults;
import org.polarsys.capella.filtering.tools.FilteringToolsPlugin;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;
import org.polarsys.capella.filtering.tools.view.GlobalFiteringCache;

/**
 * Services used in the odesign file as expressions
 */
public class FilteringServices {

	// Needed code for odesign to get the service
	private static FilteringServices service = null;

	public static FilteringServices getService() {
		if (service == null) {
			service = new FilteringServices();
		}
		return service;
	}

	public Collection<FilteringCriterion> getAllFilteringCriteria(EObject modelElement) {
		List<FilteringModel> filteringModels = FilteringUtils.getFilteringModels(modelElement, true);
		return FilteringUtils.getOwnedFilteringCriteria(filteringModels);
	}

	public Collection<FilteringResult> getAllFilteringResults(EObject modelElement) {
		FilteringResults configs = FilteringUtils.getFilteringResults(modelElement);
		return FilteringUtils.getAllFilteringResults(configs);

	}

	/*
	 * Used to filter diagram at refresh
	 */
	public boolean isFilteredByGlobalFilteringCache(EObject element) {

		GlobalFiteringCache globalFilteringCache = FilteringToolsPlugin.getGlobalFilteringCache();
		if (globalFilteringCache.isEnabled()) {

			Project project = CapellaProjectHelper.getProject(element);
			FilteringCriterionSet globalCriteria = globalFilteringCache.get(project);

			// get element associated criteria
			List<FilteringCriterion> associatedCriteria = FilteringUtils.getAssociatedCriteria(element);

			// element is a common element (no filtering criteria assigned)
			if (associatedCriteria == null || associatedCriteria.isEmpty()) {
				return true;

				// element is an optional element => apply global filter if any
			} else if (globalCriteria == null
					|| intersectionOf(globalCriteria.getFilteringCriteria(), associatedCriteria).isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public <T> List<T> intersectionOf(List<T> list1, List<T> list2) {
		List<T> list = new ArrayList<T>();

		for (T t : list1) {
			if (list2.contains(t)) {
				list.add(t);
			}
		}

		return list;
	}

	public Collection<FilteringResult> hasFilteringCriterion(FilteringCriterion filteringCriterion) {
		List<FilteringResult> configurations = new ArrayList<>();
		FilteringResults configs = FilteringUtils.getFilteringResults(filteringCriterion);

		if (configs != null) {
			for (FilteringResult config : FilteringUtils.getAllFilteringResults(configs)) {
				if ((config.getFilteringCriteria() != null)
						&& config.getFilteringCriteria().contains(filteringCriterion)) {
					configurations.add(config);
				}
			}
		}
		return configurations;
	}

	public Collection<FilteringResult> getAllFilteringResults(FilteringCriterion feature) {
		List<FilteringResult> configurations = new ArrayList<>();
		FilteringResults configs = FilteringUtils.getFilteringResults(feature);
		if (configs != null) {
			for (FilteringResult config : configs.getFilteringResults()) {
				configurations.add(config);
			}
		}
		return configurations;
	}

	public EObject getFilteringModel(EObject modelElement) {
		return FilteringUtils.getFilteringModel(modelElement);
	}

	public EObject getFilteringResults(EObject modelElement) {
		return FilteringUtils.getFilteringResults(modelElement);
	}

	public EObject removeFilteringCriterionFromFilteringResult(EObject context, FilteringResult config,
			FilteringCriterion feat) {
		if (config.getFilteringCriteria() != null) {
			config.getFilteringCriteria().remove(feat);
		}
		return context;
	}

	public EObject addFilteringCriterionToResult(EObject context, FilteringResult config, FilteringCriterion feat) {
		if ((config.getFilteringCriteria() != null) && !config.getFilteringCriteria().contains(feat)) {
			config.getFilteringCriteria().add(feat);
		}
		return context;
	}

}
