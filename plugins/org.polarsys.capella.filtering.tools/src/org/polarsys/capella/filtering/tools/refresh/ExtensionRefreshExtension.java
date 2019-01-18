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
package org.polarsys.capella.filtering.tools.refresh;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.business.api.helper.graphicalfilters.CompositeFilterApplicationBuilder;
import org.polarsys.capella.filtering.tools.FilteringToolsPlugin;

public class ExtensionRefreshExtension implements IRefreshExtension {

  private final ResourceSetImpl localSet = new ResourceSetImpl();

  @Override
  public void beforeRefresh(DDiagram diagram) {
    process(diagram);
  }

  @Override
  public void postRefresh(DDiagram diagram) {
    // nothing to do
  }

  public void process(DDiagram diagram) {

    // refresh a already opened diagram
    if (!diagram.getActivatedFilters().isEmpty()) {
      CompositeFilterApplicationBuilder builder = new CompositeFilterApplicationBuilder(diagram);
      builder.computeCompositeFilterApplications();
    }

    // set filter when opening a diagram
    CompositeFilterDescription filterDesc = (CompositeFilterDescription) localSet
        .getEObject(FilteringToolsPlugin.FILTER_URI, true);
    if (!contains(diagram.getActivatedFilters(), filterDesc)) {
      diagram.getActivatedFilters().add(filterDesc);
      if (diagram.getCurrentConcern() != null) {
        diagram.getCurrentConcern().getFilters().add(filterDesc);
      }
    }

  }

  private boolean contains(EList<FilterDescription> activatedFilters, CompositeFilterDescription filterDesc) {
    for (FilterDescription desc : activatedFilters) {
      // name is the id of the element ...
      if (desc.getName().equals(filterDesc.getName())) {
        return true;
      }
    }
    return false;
  }

}