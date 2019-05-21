package org.polarsys.capella.filtering.model.helpers;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.filtering.FilteringResultSet;

/**
 * @generated
 */
public class FilteringResultSetHelper {

	private static final FilteringResultSetHelper instance = new FilteringResultSetHelper();

	/**
	 * @generated
	 */
	public static FilteringResultSetHelper getInstance() {
		return instance;
	}

	/**
	 * @generated
	 */
	public Object doSwitch(FilteringResultSet object, EStructuralFeature feature) {
		// handle derivated feature

		// delegate to parent helper
		return org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper.getInstance()
				.doSwitch(object, feature);

	}

}