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
package org.polarsys.capella.filtering.model.helpers;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringFactory;

/**
 * @generated
 */
public class FilteringCriterionSetHelper {

	private static final FilteringCriterionSetHelper instance = new FilteringCriterionSetHelper();

	/**
	 * @generated
	 */
	public static FilteringCriterionSetHelper getInstance() {
		return instance;
	}

	/**
	 * 
	 * @param filteringCriterionSet
	 * @return
	 */
	public static FilteringCriterionSet unionOf(Collection<FilteringCriterionSet> filteringCriterionSet) {
		return unionOf(filteringCriterionSet.toArray(new FilteringCriterionSet[] {}));
	}

	/**
	 * 
	 * @param filteringCriterionSet
	 * @return
	 */
	public static FilteringCriterionSet unionOf(FilteringCriterionSet... filteringCriterionSet) {
		AssociatedFilteringCriterionSet unionCriterionSet = FilteringFactory.eINSTANCE
				.createAssociatedFilteringCriterionSet();
		// removes null objects
		filteringCriterionSet = Arrays.stream(filteringCriterionSet).filter(Objects::nonNull)
				.toArray(FilteringCriterionSet[]::new);

		if (filteringCriterionSet.length == 0)
			return unionCriterionSet;

		if (filteringCriterionSet.length == 1)
			return filteringCriterionSet[0];

		for (FilteringCriterionSet criterionSet : filteringCriterionSet) {
			unionCriterionSet.getFilteringCriteria().addAll(criterionSet.getFilteringCriteria());
		}

		return unionCriterionSet;
	}

	/**
	 * 
	 * @param filteringCriterionSet
	 * @return
	 */
	public static FilteringCriterionSet intersectionOf(FilteringCriterionSet... filteringCriterionSet) {
		AssociatedFilteringCriterionSet interesectionCriterionSet = FilteringFactory.eINSTANCE
				.createAssociatedFilteringCriterionSet();

		// removes null objects
		filteringCriterionSet = Arrays.stream(filteringCriterionSet).filter(Objects::nonNull)
				.toArray(FilteringCriterionSet[]::new);

		if (filteringCriterionSet.length == 0)
			return interesectionCriterionSet;

		if (filteringCriterionSet.length == 1)
			return filteringCriterionSet[0];

		FilteringCriterionSet firstCriterionSet = filteringCriterionSet[0];

		for (FilteringCriterion criterion : firstCriterionSet.getFilteringCriteria()) {
			boolean existsInAllOtherCriterionSet = true;

			for (int i = 1; i < filteringCriterionSet.length; i++) {
				if (!filteringCriterionSet[i].getFilteringCriteria().contains(criterion)) {
					existsInAllOtherCriterionSet = false;
					break;
				}

			}
			if (existsInAllOtherCriterionSet) {
				interesectionCriterionSet.getFilteringCriteria().add(criterion);
			}
		}

		return interesectionCriterionSet;
	}

	/**
	 * 
	 * @param filteringCriterionSet
	 * @return
	 */
	public static FilteringCriterionSet intersectionOf(Collection<FilteringCriterionSet> filteringCriterionSet) {
		return intersectionOf(filteringCriterionSet.toArray(new FilteringCriterionSet[] {}));
	}

	/**
	 * @generated
	 */
	public Object doSwitch(FilteringCriterionSet object, EStructuralFeature feature) {
		// handle derivated feature

		// delegate to parent helper
		return org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper.getInstance()
				.doSwitch(object, feature);

	}

}