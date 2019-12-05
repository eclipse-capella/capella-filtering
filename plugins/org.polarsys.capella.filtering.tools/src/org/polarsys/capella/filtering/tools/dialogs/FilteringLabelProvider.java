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
package org.polarsys.capella.filtering.tools.dialogs;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.utils.EObjectExt2;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

/**
 * Product Line label provider.
 * 
 * 
 */
public class FilteringLabelProvider extends MDEAdapterFactoryLabelProvider implements IColorProvider {
  /**
   * Foreground color for referencing elements.
   */
  private int foregroundColor;
  /**
   * Viewer that uses this label provider.
   */
  private TreeViewer viewer;

  /**
   * Constructor.
   * 
   * @param adapterFactory
   * @param foregroundColorForReferencingElements
   *          must be a {@link SWT#COLOR} constant.
   */
  public FilteringLabelProvider(TreeViewer viewer, int foregroundColorForReferencingElements) {
    super();
    this.foregroundColor = foregroundColorForReferencingElements;
    this.viewer = viewer;
  }

  /**
   * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
   */
  @Override
  public Color getBackground(Object element) {
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
   */
  @Override
  public Color getForeground(Object element) {
    // Select the foreground color for elements that reference the selected
    // one.
    Object input = viewer.getInput();
    if ((input instanceof TreeData) && (((TreeData) input).isValid(element))) {
      Display display = PlatformUI.getWorkbench().getDisplay();
      return display.getSystemColor(foregroundColor);
    }
    return null;
  }

  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object object) {
    String text = super.getText(object);
    return text.replace("%20", ICommonConstants.EMPTY_STRING + ICommonConstants.WHITE_SPACE_CHARACTER); //$NON-NLS-1$
  }

  @Override
  public String getColumnText(Object element, int columnIndex) {
    String text = null;

    if (0 == columnIndex) {
      text = getText(element);
    } else if (1 == columnIndex && element instanceof CapellaElement) {
      text = EObjectExt2.formatValues(FilteringUtils.getExplicitAssociatedCriteria((EObject) element), null,
          ICommonConstants.EMPTY_STRING);
    }

    return text;
  }

  @Override
  public Image getColumnImage(Object element, int columnIndex) {
    Image image = null;
    if (0 == columnIndex) {
      image = getImage(element);
    }
    return image;
  }
}
