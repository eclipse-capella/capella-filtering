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
package org.polarsys.capella.filtering.properties.fields;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.core.ui.properties.controllers.IMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * Read Only Multiple Semantic Field
 * 
 * 
 */
public class ReadOnlyMultipleSemanticField extends MultipleSemanticField {

  /**
   * @param parent
   * @param label
   * @param widgetFactory
   * @param controller
   */
  public ReadOnlyMultipleSemanticField(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory,
      IMultipleSemanticFieldController controller) {
    // Span the widget to fill all the horizontal space now that there are
    // no buttons
    super(parent, label, widgetFactory, 4, controller, false);
  }

  /**
   * Create Edit button.
   * 
   * @param parent
   */
  @Override
  protected void createEditButton(Composite parent) {
    // Do not create it
  }

  /**
   * Create Open button.
   * 
   * @param parent
   */
  @Override
  protected void createOpenButton(Composite parent) {
    // Do not create it
  }

  /**
   * Create Add button.
   * 
   * @param parent
   */
  @Override
  protected void createAddButton(Composite parent) {
    // Do not create it
  }

  /**
   * Create Delete button.
   * 
   * @param parent
   */
  @Override
  protected void createDeleteButton(Composite parent) {
    // Do not create it
  }

}
