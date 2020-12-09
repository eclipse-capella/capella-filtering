/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

public class DefaultConfigurationItemContribution implements IMDEMenuItemContribution {

  @Override
  public EClass getMetaclass() {
    return FilteringPackage.Literals.CREATION_DEFAULT_FILTERING_CRITERION_SET;
  }

  @Override
  public Command executionContribution(EditingDomain editingDomain, ModelElement containerElement,
      ModelElement createdElement, EStructuralFeature feature) {
    return new IdentityCommand();
  }

  @Override
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    return !(modelElement instanceof SystemEngineering
        && FilteringUtils.getCreationDefaultFeatureSet((SystemEngineering) modelElement) != null);
  }
}
