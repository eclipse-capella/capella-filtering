/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.filtering.menu.contribution;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.menu.dynamic.CreationHelper;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.filtering.FilteringPackage;

public class UnionFilteringResultSetItemContribution implements IMDEMenuItemContribution {

  @Override
  public EClass getMetaclass() {
    return FilteringPackage.Literals.UNION_FILTERING_RESULT_SET;
  }

  @Override
  public Command executionContribution(EditingDomain editingDomain, ModelElement containerElement,
      ModelElement createdElement, EStructuralFeature feature) {
	  if (createdElement instanceof AbstractNamedElement) {
	      String name = ((AbstractNamedElement) createdElement).getName();
	      if ((name == null) || name.startsWith(createdElement.eClass().getName())) {
	        return CreationHelper.getNamingCommand(editingDomain, (AbstractNamedElement) createdElement, containerElement, feature,
	            Messages.ItemContribution_Union);
	      }
	    }
    return null;
  }

  @Override
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    return true;
  }
}
