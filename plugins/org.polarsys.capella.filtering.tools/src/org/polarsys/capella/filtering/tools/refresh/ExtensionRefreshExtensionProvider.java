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

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtensionProvider;

public class ExtensionRefreshExtensionProvider implements IRefreshExtensionProvider {

  private static final ExtensionRefreshExtension REFRESH = new ExtensionRefreshExtension();

  @Override
  public IRefreshExtension getRefreshExtension(DDiagram viewPoint) {
    return REFRESH;
  }

  @Override
  public boolean provides(DDiagram viewPoint) {
    return true;
  }

}