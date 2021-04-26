/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.filtering.migration.contribution;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.capella.filtering.FilteringFactory;
import org.polarsys.capella.filtering.FilteringPackage;

/**
 * This class ensures Filtering migration towards Capella versions.
 */
public class FilteringMigrationContribution extends AbstractMigrationContribution {

  @Override
  public EFactory getEFactory(String prefix, Resource resource, MigrationContext context) {
    if (FilteringPackage.eNS_PREFIX.equals(prefix)) {
      return FilteringFactory.eINSTANCE;
    }
    return super.getEFactory(prefix, resource, context);
  }

}