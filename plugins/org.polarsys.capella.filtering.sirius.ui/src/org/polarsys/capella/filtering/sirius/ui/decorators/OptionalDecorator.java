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
package org.polarsys.capella.filtering.sirius.ui.decorators;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DecorationEditPolicy.DecoratorTarget;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoration;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;
import org.polarsys.capella.filtering.sirius.ui.Activator;
import org.polarsys.capella.filtering.sirius.ui.FilteringSiriusUtils;
import org.polarsys.capella.filtering.tools.FilteringToolsPlugin;
import org.polarsys.capella.filtering.tools.preferences.FilteringPreferencesPage;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

/**
 * Optional Decorator
 * 
 * 
 */
public class OptionalDecorator implements IDecorator {

  // the object to be decorated
  DecoratorTarget decoratorTarget;

  // the decorators being displayed
  IDecoration labelDecoration;
  IDecoration imageDecoration;

  private CriteriaChangeListener changeListener;

  protected EObject notifier;

  /**
   * Constructor
   * 
   * @param decoratorTarget
   */
  public OptionalDecorator(IDecoratorTarget decoratorTarget) {
    this.decoratorTarget = (DecoratorTarget) decoratorTarget;
    this.changeListener = new CriteriaChangeListener(this);
  }

  /**
   * Activate {@inheritDoc}
   */
  @Override
  public void activate() {
    IGraphicalEditPart editPart = (IGraphicalEditPart) decoratorTarget.getAdapter(IGraphicalEditPart.class);
    EObject element = editPart.resolveSemanticElement();
    if (element instanceof DDiagramElement) {
      TransactionalEditingDomain editingDomain = TransactionHelper.getExecutionManager(element).getEditingDomain();
      // Add the notifier to each melody element in the diagram
      for (CapellaElement melodyElement : FilteringSiriusUtils.getCapellaElements((DDiagramElement) element)) {
        notifier = melodyElement;
        DiagramEventBroker.getInstance(editingDomain).addNotificationListener(notifier, changeListener);
        // Include also the notifier to their associated feature sets
        AssociatedFilteringCriterionSet associatedFeatureSet = FilteringUtils
            .getAssociatedFilteringCriterionSet(melodyElement);
        if (associatedFeatureSet != null) {
          DiagramEventBroker.getInstance(editingDomain).addNotificationListener(associatedFeatureSet, changeListener);
        }
      }
    }
  }

  /**
   * Deactivate {@inheritDoc}
   */
  @Override
  public void deactivate() {
    if (notifier != null) {
      TransactionalEditingDomain editingDomain = TransactionHelper.getEditingDomain(notifier);
      if (editingDomain != null) {
        DiagramEventBroker.getInstance(editingDomain).removeNotificationListener(notifier, changeListener);
      }
      notifier = null;
    }
    removeDecoration();
  }

  /**
   * Refresh {@inheritDoc}
   */
  @Override
  public void refresh() {

    // Get the editPart and get the semantic element behind the gmf edit
    // part
    IGraphicalEditPart editPart = (IGraphicalEditPart) decoratorTarget.getAdapter(IGraphicalEditPart.class);
    EObject element = editPart.resolveSemanticElement();

    // In our case, In viewpoint the semantic element should be a
    // DDiagramElement
    if (element instanceof DDiagramElement) {

      // Check if it we have Melody Elements
      if (FilteringSiriusUtils.containsCapellaElements((DDiagramElement) element)) {

        // Remove all the decorations
        removeDecoration();

        // Check if it contains at least one Optional Melody Element
        if (FilteringSiriusUtils.containsExplicitAssociatedCriteria((DDiagramElement) element)) {

          // Get preferences
          IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode(FilteringToolsPlugin.getDefault().getPluginId());
          boolean showLabelDecoration = prefs.getBoolean(FilteringPreferencesPage.FEATURES_DIAGRAM, false);
          boolean showImageDecoration = prefs.getBoolean(FilteringPreferencesPage.OPTIONAL_DIAGRAM, true);
          boolean saveDecoration = prefs.getBoolean(FilteringPreferencesPage.SAVE_DECORATORS_DIAGRAM, true);

          // It is volatile if we don't want to save the decorators
          // when saving the diagram as image
          boolean isVolatile = !saveDecoration;

          // Label decorator
          if (showLabelDecoration) {
            Label label = new Label();
            String text = FilteringSiriusUtils.getEnterSeparatedExplicitFeatures((DDiagramElement) element, true);
            label.setText(text);
            Font font = Display.getCurrent().getSystemFont();
            label.setFont(font);
            label.setForegroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
            // TODO Problem with label size when many entries.
            // The problem was only with the height. Workaround was
            // to add 2 pixels for each line, 1 was not enough.
            Dimension labelPreferredSize = label.getPreferredSize();

            int numberOfLines = text.split("\n").length; //$NON-NLS-1$
            int adjustement = numberOfLines * 2;
            label.setSize(labelPreferredSize.width, labelPreferredSize.height + adjustement);

            if ((element instanceof AbstractDNode) || (element instanceof NodeFigure)) {
              labelDecoration = decoratorTarget.addShapeDecoration(label, IDecoratorTarget.Direction.EAST, 5,
                  isVolatile);
            } else {
              labelDecoration = decoratorTarget.addConnectionDecoration(label, 67, isVolatile);
            }
          }

          // Image decorator
          if (showImageDecoration) {
            if ((element instanceof AbstractDNode) || (element instanceof NodeFigure)) {
              imageDecoration = decoratorTarget.addShapeDecoration(Activator.getDecoratorImage(),
                  IDecoratorTarget.Direction.NORTH_EAST, -1, isVolatile);
            } else {
              imageDecoration = decoratorTarget.addConnectionDecoration(Activator.getDecoratorImage(), 50, isVolatile);
            }
          }
        }
      }
    }
  }

  /**
   * Remove decorations and set them to null
   */
  private void removeDecoration() {

    // Image decorator
    if (imageDecoration != null) {
      decoratorTarget.removeDecoration(imageDecoration);
      imageDecoration = null;
    }

    // Label decorator
    if (labelDecoration != null) {
      decoratorTarget.removeDecoration(labelDecoration);
      labelDecoration = null;
    }
  }

}