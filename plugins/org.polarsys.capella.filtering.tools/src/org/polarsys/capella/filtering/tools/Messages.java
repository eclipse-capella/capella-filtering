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

  public static String FilteringExtractionAction_melodymodellerFileNotFound;

  public static String FilteringExtractionAction_projectHasReferenceToLibrary;

  public static String FilteringVariabilityMetricsDialog_0;

  public static String FilteringVariabilityMetricsDialog_1;

  public static String FilteringVariabilityMetricsDialog_2;

  public static String FilteringVariabilityMetricsDialog_3;

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
