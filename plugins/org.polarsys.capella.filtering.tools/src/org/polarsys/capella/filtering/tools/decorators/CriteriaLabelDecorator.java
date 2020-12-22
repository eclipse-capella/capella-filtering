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
package org.polarsys.capella.filtering.tools.decorators;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.sirius.ui.business.api.descriptor.ComposedImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.filtering.tools.FilteringToolsPlugin;
import org.polarsys.capella.filtering.tools.helpers.ViewpointHelper;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

public class CriteriaLabelDecorator implements ILabelDecorator {

  /**
   * Image descriptor for Filtering overlay.
   */
  public static final ImageDescriptor FILTERING_OVERLAY_DESC = AbstractUIPlugin
      .imageDescriptorFromPlugin(FilteringToolsPlugin.ID, "icons/ovr_optional.png"); //$NON-NLS-1$

  @Override
  public void addListener(ILabelProviderListener listener) {
    //
  }

  @Override
  public void dispose() {
    //
  }

  @Override
  public boolean isLabelProperty(Object element, String property) {
    return false;
  }

  @Override
  public void removeListener(ILabelProviderListener listener) {
    //
  }

  @Override
  public Image decorateImage(Image image, Object element) {
    if (!(element instanceof CapellaElement) || !ViewpointHelper.isViewpointActive((CapellaElement) element)
        || image == null) {
      return null;
    }
    if (isOptional((CapellaElement) element)) {
      ComposedImage img = decorateFiltering(image);
      ImageDescriptor descriptor = new ComposedImageDescriptor(img);
      return FilteringToolsPlugin.getDefault().getImage(descriptor);
    }

    // Null means no decoration
    return null;
  }

  private ComposedImage decorateFiltering(Image image) {
    List<Object> images = new ArrayList<>(2);
    images.add(image);
    images.add(FilteringToolsPlugin.getDefault().getImage(FILTERING_OVERLAY_DESC));

    return new ComposedImage(images) {

      @Override
      public List<Point> getDrawPoints(Size size) {
        List<Point> results = new ArrayList<>();
        results.add(new Point());
        Point overlay = new Point();
        overlay.x = 0;
        overlay.y = 7;
        results.add(overlay);
        return results;
      }
    };
  }

  @Override
  public String decorateText(String text, Object element) {
    if (element instanceof CapellaElement && ViewpointHelper.isViewpointActive((CapellaElement) element)
        && isOptional((CapellaElement) element)) {
      String decorator = FilteringUtils.getCommaSeparatedExplicitFeatures((CapellaElement) element);
      if (decorator != null) {
        return text + " [" + decorator + "]";
      }
    }
    return text;
  }

  private boolean isOptional(CapellaElement element) {
    return !FilteringUtils.getExplicitAssociatedCriteria(element).isEmpty();
  }
}
