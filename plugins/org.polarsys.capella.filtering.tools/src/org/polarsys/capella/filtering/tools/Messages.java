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
package org.polarsys.capella.filtering.tools;

import org.eclipse.osgi.util.NLS;

/**
 * Messages for the product line plugin.
 * 
 * 
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.filtering.tools.messages"; //$NON-NLS-1$

  //
  // Product Line
  //
  public static String filtering_menu_lbl;
  public static String filtering_overviewAction_lbl;

  public static String filtering_dialog_title;
  public static String filtering_dialog_msg;

  public static String filtering_dialog_combo_lbl;
  public static String filtering_dialog_combo_allStatus;

  public static String filtering_dialog_header_col0;
  public static String filtering_dialog_header_col1;
  public static String filtering_dialog_header_col2;
  public static String filtering_dialog_header_col3;

  public static String filtering_selectAll;
  public static String filtering_deselectAll;

  public static String FilteringExtractionAction_0;

  public static String FilteringExtractionAction_1;

  public static String FilteringExtractionAction_2;

  public static String FilteringExtractionAction_3;

  public static String FilteringExtractionAction_connectedProject;

  public static String FilteringExtractionAction_projectHasReferenceToLibrary;

  public static String FilteringMetricsDialog_0;

  public static String FilteringMetricsDialog_1;

  public static String FilteringMetricsDialog_2;

  public static String FilteringMetricsDialog_3;

  public static String FilteringDirtyReferencedModels_dialog_title;
  public static String FilteringDirtyReferencedModels_dialog_message;
  public static String FilteringDirtyReferencedModels_dialog_continue_without_saving;
  public static String FilteringDirtyReferencedModels_dialog_continue_with_saving;
  
  public static String filtering_error_image_path_change;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  /**
   * Constructor.
   */
  private Messages() {
    // Do nothing.
  }
}
