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
package org.polarsys.capella.filtering.properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

/**
 * 
 * 
 */
public class CapellaElementImplicitCriteria extends AbstractMultipleSemanticFieldController {
	/**
	 * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#loadData(org.polarsys.capella.core.data.information.datatype.BooleanType,
	 *      org.eclipse.emf.ecore.EReference)
	 */
	@Override
	public List<EObject> loadValues(EObject semanticElement, EStructuralFeature semanticFeature) {
		return getValues(semanticElement);
	}

	/**
	 * @param semanticElement
	 * @param semanticFeature
	 * @return
	 */
	private List<EObject> getValues(EObject semanticElement) {
		List<EObject> result = new ArrayList<>();
		// Get implicit
		result.addAll(FilteringUtils.getImplicitAssociatedCriteria(semanticElement));
		return result;
	}

	/**
	 * @see org.polarsys.capella.core.ui.properties.controllers.custom.properties.controllers.AbstractMultipleSemanticFieldController#getReadOpenValuesQuery(org.polarsys.capella.core.data.capellacore.CapellaElement)
	 */
	@Override
	protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
		return null;
	}

	/**
	 * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#readOpenValues()
	 */
	@Override
	public List<EObject> readOpenValues(EObject semanticElement, EStructuralFeature semanticFeature,
			boolean availableElements) {
		return Collections.emptyList();
	}
}
