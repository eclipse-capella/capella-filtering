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
package org.polarsys.capella.filtering.transition.domain;

import java.util.Set;

import org.eclipse.emf.ecore.EPackage;
import org.polarsys.capella.core.transition.system.domain.CapellaTransitionDomainHelper;
import org.polarsys.capella.filtering.FilteringPackage;

public class FilteringDomainHelper extends CapellaTransitionDomainHelper {
  @Override
  protected Set<EPackage> getEPackages() {
    // TODO Remove this method if super get the extensions
    Set<EPackage> ePackages = super.getEPackages();
    ePackages.add(FilteringPackage.eINSTANCE);
    return ePackages;
  }

  /**
   * @see org.polarsys.capella.core.transition.system.domain.CapellaTransitionDomainHelper#isDomainFor(Object)
   */
  @Override
  public boolean isDomainFor(Object object) {
    return true;
  }
}