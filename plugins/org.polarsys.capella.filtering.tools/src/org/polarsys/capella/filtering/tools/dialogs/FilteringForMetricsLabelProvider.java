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
package org.polarsys.capella.filtering.tools.dialogs;

import java.text.DecimalFormat;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;

/**
 * Product Line for metrics label provider.
 * 
 * 
 */
public class FilteringForMetricsLabelProvider extends FilteringLabelProvider {

  /**
   * @param viewer
   * @param foregroundColorForReferencingElements
   */
  public FilteringForMetricsLabelProvider(TreeViewer viewer, int foregroundColorForReferencingElements) {
    super(viewer, foregroundColorForReferencingElements);
  }

  Double varMetric;

  @Override
  public String getColumnText(Object element, int columnIndex) {
    String text = null;
    varMetric = null;

    // Element name
    if (0 == columnIndex) {
      text = getText(element);
      // Filtering rate
    } else if (1 == columnIndex && element instanceof ExtensibleElement) {
      Double metric = getFilteringRate((EObject) element);
      varMetric = metric;
      DecimalFormat df = new DecimalFormat();
      df.setMaximumFractionDigits(2);
      text = df.format(metric);
    }

    return text;
  }

  /**
   * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
   */
  @Override
  public Color getBackground(Object element) {
    // Background color will be white to green scale
    if (varMetric == null) {
      return null;
    }
    if (varMetric == 0) {
      return null;
    }
    // We always add 5 to include more contrast. Otherwise it is almost
    // white for small values
    varMetric = varMetric + 5;
    // We check that it is no more than 100
    Math.min(varMetric, 100);
    // We get the inverse
    Double result = Math.abs(varMetric - 100);
    // We calculate green level
    Integer greenValue = result.intValue() * 255 / 100;
    // Create the color maintaining G and setting R and B values
    return new Color(Display.getCurrent(), greenValue, 255, greenValue);
  }

  /**
   * Get Variability Rate
   * 
   * @param element
   * @return
   */
  public static Double getFilteringRate(EObject element) {
    // Return 100 if it contains associated features
    if (FilteringUtils.hasAssociatedCriteria(element)) {
      return 100.0;
      // else Return 0 directly if it does not contain childs
    } else if (!(element).eAllContents().hasNext()) {
      return 0.0;
    }
    // Calculate in other cases
    double numberOfChilds = 0;
    double numberOfOptionalChilds = 0;
    Iterator<EObject> i = ((ExtensibleElement) element).eAllContents();
    while (i.hasNext()) {
      EObject elt = i.next();
      if (!FilteringUtils.isInstanceOfFilteringExcludedElements(elt)) {
        numberOfChilds++;
        if (FilteringUtils.hasAssociatedCriteria(elt)) {
          numberOfOptionalChilds++;
        }
      }
    }
    if (numberOfChilds > 0) {
      return (numberOfOptionalChilds / numberOfChilds) * 100;
    }
    return 0.0;
  }
}
