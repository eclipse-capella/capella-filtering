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
package org.polarsys.capella.filtering.tools.utils.ui;

import org.eclipse.jface.viewers.LabelProvider;
import org.polarsys.capella.core.data.capellacore.NamedElement;

/**
 * Feature Label Provider
 */
public class NamedElementLabelProvider extends LabelProvider {
  @Override
  public String getText(Object element) {
    if (element instanceof NamedElement) {
      return ((NamedElement) element).getName();
    }
    return element == null ? "" : element.toString();//$NON-NLS-1$
  }
}
