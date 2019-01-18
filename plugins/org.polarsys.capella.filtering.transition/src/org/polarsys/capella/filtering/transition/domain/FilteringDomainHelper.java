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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EPackage;
import org.polarsys.capella.core.transition.system.domain.CapellaTransitionDomainHelper;
import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.kitalpha.transposer.transformation.emf.TransposerEMFPlugin;

public class FilteringDomainHelper extends CapellaTransitionDomainHelper {
  @Override
  public Class<?> getDomainMetaclass(String name) {
    try {
      return Class.forName(name);
    } catch (ClassNotFoundException e) {
      TransposerEMFPlugin.getDefault().getLog()
          .log(new Status(IStatus.ERROR, TransposerEMFPlugin.PLUGIN_ID, "No Domain Class called : " + name, e)); //$NON-NLS-1$
    }
    return null;
  }

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