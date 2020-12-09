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
package org.polarsys.capella.filtering.sirius.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin {

  // The plug-in ID
  public static final String ID = "org.polarsys.capella.filtering.sirius.ui"; //$NON-NLS-1$

  // The shared instance
  private static Activator plugin;
  private static Image decoratorImage;

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework. BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework. BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    // dispose image
    if ((decoratorImage != null) && !decoratorImage.isDisposed()) {
      decoratorImage.dispose();
    }
    super.stop(context);
  }

  /**
   * Returns the shared instance
   * 
   * @return the shared instance
   */
  public static Activator getDefault() {
    return plugin;
  }

  public static ImageDescriptor getBundledImageDescriptor(String path) {
    return AbstractUIPlugin.imageDescriptorFromPlugin(ID, path);
  }

  public static Image getDecoratorImage() {
    if (decoratorImage == null) {
      decoratorImage = getBundledImageDescriptor("icons/optionalDecorator.png").createImage(); //$NON-NLS-1$
    }
    return decoratorImage;
  }

}
