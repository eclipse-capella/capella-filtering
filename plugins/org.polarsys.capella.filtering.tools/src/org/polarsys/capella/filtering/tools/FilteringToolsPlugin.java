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
package org.polarsys.capella.filtering.tools;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.BundleContext;
import org.polarsys.capella.common.ui.services.AbstractUIActivator;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringResult;
import org.polarsys.capella.filtering.tools.preferences.FilteringPreferencesPage;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;
import org.polarsys.capella.filtering.tools.view.DiagCriteriaVisibilityView;
import org.polarsys.capella.filtering.tools.view.GlobalFiteringCache;
import org.polarsys.kitalpha.ad.common.utils.URIFix;

/**
 * The activator class controls the plug-in life cycle
 */
public class FilteringToolsPlugin extends AbstractUIActivator {

  /**
   * The plug-in ID
   */
  public static final String ID = "org.polarsys.capella.filtering.tools"; //$NON-NLS-1$
  public static final String FILTERING_ODESIGN = "/org.polarsys.capella.filtering.sirius.analysis/description/FilteringResults.odesign";
  public static final URI FILTER_URI = URIFix.createPlatformPluginURI(FILTERING_ODESIGN
      + "#//@ownedViewpoints[name='Filtering']/@ownedRepresentations[name='Filtering']/@filters[name='VisibleDiagramFilteringCriteria']",
      false);

  /**
   * The singleton instance.
   */
  private static FilteringToolsPlugin plugin;

  private Map<ImageDescriptor, Image> descriptorsToImages;

  private static Map<FilteringCriterion, Collection<?>> implicitImpactCache = new HashMap<>();
  /**
   * Caches current selected filtering selection for each Capella project.</br>
   * The filtering selection is provided by {@link DiagCriteriaVisibilityView}
   */
  private static GlobalFiteringCache globalFilteringCache = new GlobalFiteringCache();

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    descriptorsToImages = new HashMap<>();
    plugin = this;

    // We add a listener to update automatically the diagrams when we click
    // apply in the preferences page
    FilteringToolsPlugin.getDefault().getPreferenceStore().addPropertyChangeListener(new IPropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent event) {
        if (event.getProperty().equals(FilteringPreferencesPage.FEATURES_DIAGRAM)
            || event.getProperty().equals(FilteringPreferencesPage.OPTIONAL_DIAGRAM)
            || event.getProperty().equals(FilteringPreferencesPage.SAVE_DECORATORS_DIAGRAM)) {
          FilteringUtils.forceRefreshAllDiagrams();
        }
      }
    });
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    /*
     * Disposing the images
     */
    Iterator<Image> it = descriptorsToImages.values().iterator();
    while (it.hasNext()) {
      Image img = it.next();
      if (img != null) {
        img.dispose();
      }
    }
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the singleton instance
   * 
   * @return
   */
  public static FilteringToolsPlugin getDefault() {
    return plugin;
  }

  /**
   * @return the implicitImpactCache
   */
  public static Map<FilteringCriterion, Collection<?>> getImplicitImpactCache() {
    return implicitImpactCache;
  }

  public static GlobalFiteringCache getGlobalFilteringCache() {
    return globalFilteringCache;
  }

  /**
   * 
   * @param desc
   *          an image descriptor.
   * @return an Image instance
   */
  public Image getImage(ImageDescriptor desc) {
    if (!descriptorsToImages.containsKey(desc)) {
      descriptorsToImages.put(desc, desc.createImage());
    }
    return descriptorsToImages.get(desc);
  }

}
