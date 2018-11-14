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
package org.polarsys.capella.filtering.semantic.queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringCriterion;

public class FilteringCriterionQuery implements IQuery {

	@Override
	public List<Object> compute(Object object) {
		Set<Object> res = new HashSet<>();

		if (object instanceof FilteringCriterion) {
			FilteringCriterion filteringCriterion = (FilteringCriterion) object;

			ECrossReferenceAdapter crossReferencer = ((SemanticEditingDomain) TransactionHelper
					.getEditingDomain(filteringCriterion)).getCrossReferencer();
			Collection<EObject> referencingElements = EcoreUtil2.getReferencingElements(filteringCriterion, crossReferencer);

			for (EObject referencingElement : referencingElements) {

				if (referencingElement instanceof AssociatedFilteringCriterionSet) {
					res.add(referencingElement.eContainer());
				}
			}
		}
		return new ArrayList<>(res);
	}
}
