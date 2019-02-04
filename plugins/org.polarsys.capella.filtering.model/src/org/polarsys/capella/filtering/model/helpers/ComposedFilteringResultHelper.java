package org.polarsys.capella.filtering.model.helpers;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.filtering.ComposedFilteringResult;

/**
 * @generated
 */
public class ComposedFilteringResultHelper {

	private static final ComposedFilteringResultHelper instance = new ComposedFilteringResultHelper();

	/**
	 * @generated
	 */
	public static ComposedFilteringResultHelper getInstance() {
		return instance;
	}

	/**
	 * @generated
	 */
	public Object doSwitch(ComposedFilteringResult object, EStructuralFeature feature) {
		// handle derivated feature

		// delegate to parent helper
		return org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper.getInstance()
				.doSwitch(object, feature);

	}

}