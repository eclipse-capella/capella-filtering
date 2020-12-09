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
package org.polarsys.capella.filtering.sirius.ui.dialogs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.sirius.ui.FilteringSiriusUtils;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;
import org.polarsys.capella.filtering.tools.utils.ui.CriteriaContentProvider;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * A dialog box which allows the user to edit a boolean property/flag of a sub-set of features that have associated
 * elements in a diagram. The dialog presents all the diagram and indicates their state (selected or not). The user can
 * edit this state individually for each element. When the operation is validated by the user (by closing the dialog
 * with the OK button) the specified editing operations are applied to the elements whose state has been changed (i.e.
 * they have been selected or deselected).
 * <p>
 * What the notion of "selected" means can be customized through 3 programmer-specified functions:
 * <ul>
 * <li>a predicate to detect whether an element is selected or not (e.g. "the element is hidden")</li>
 * <li>an action to apply to an element to make it selected (e.g. "set the element as hidden")</li>
 * <li>an action to apply to an element to make it deselected (e.g. "set the element as not-hidden/reveal the
 * element")</li>
 * </ul>
 */
public class DiagramElementsSelectionDialog {

  private static final Function<Object, Void> DO_NOTHING = new Function<Object, Void>() {
    @Override
    public Void apply(Object from) {
      return null;
    }
  };

  /**
   * The internal dialog used by this dialog.
   */
  protected SiriusCriteriaSelectionDialog dialog;

  /**
   * The diagram associated to this dialog.
   */
  protected DDiagram diagram;

  /**
   * The filtering mode currently associated to the tree viewer. Can be :
   * <ul>
   * <li>All elements</li>
   * <li>Only checked elements</li>
   * <li>Only unchecked elements</li>
   * </ul>
   */
  // protected FilteringMode mode = FilteringMode.SHOW_ALL;

  private final String title;

  private final String message;

  private Predicate<Object> isSelected = Predicates.alwaysTrue();

  // Grayed elements will not be selectable.
  private Predicate<Object> isGrayed = Predicates.alwaysFalse();

  private Function<Object, Void> selectedAction = DO_NOTHING;

  private Function<Object, Void> deselectedAction = DO_NOTHING;

  protected CriteriaContentProvider contentProvider;

  /**
   * Constructor.
   * 
   * @param title
   *          the title of the dialog window.
   * @param message
   *          the message for the dialog.
   */
  public DiagramElementsSelectionDialog(String title, String message) {
    this.title = title;
    this.message = message;
  }

  /**
   * Sets the predicate to use to detect which elements of the diagram are selected, in the sense of the criterion to be
   * edited.
   * 
   * @param isSelectedPredicate
   *          the predicate to used to detect selected elements of the diagram.
   */
  public void setSelectionPredicate(Predicate<Object> isSelectedPredicate) {
    this.isSelected = isSelectedPredicate;
  }

  /**
   * Sets the predicate to use to detect which elements of the diagram will be grayed instead of selected, in the sense
   * of the criterion to be edited.
   * 
   * @param isGrayedPredicate
   *          the predicate to used to detect selected elements of the diagram.
   */
  public void setGrayedPredicate(Predicate<Object> isGrayedPredicate) {
    this.isGrayed = isGrayedPredicate != null ? isGrayedPredicate : Predicates.alwaysFalse();
  }

  /**
   * Sets the operation to be applied on elements which are newly selected by the user.
   * 
   * @param selectedAction
   *          the operation to apply to newly selected elements.
   */
  public void setSelectedAction(Function<Object, Void> selectedAction) {
    this.selectedAction = selectedAction;
  }

  /**
   * Sets the operation to be applied on elements which are deselected by the user.
   * 
   * @param deselectedAction
   *          the operation to apply to deselected elements.
   */
  public void setDeselectedAction(Function<Object, Void> deselectedAction) {
    this.deselectedAction = deselectedAction;
  }

  /**
   * Asks the end-user for a list of elements to select/de-select, and applies the corresponding changes.
   * 
   * @param parent
   *          the shell to use to interact with the user, if required.
   * @param ddiagram
   *          the diagram whose elements to edit.
   * @param includeNodeLabel
   *          include node label (if there are on border) in the tree content
   * @return <code>true</code> if the operation was correctly executed, <code>false</code> if it was canceled by the
   *         user.
   */
  public boolean open(Shell parent, DDiagram ddiagram, boolean includeNodeLabel) {
    boolean result = false;
    diagram = ddiagram;

    // get Product Line features
    contentProvider = new CriteriaContentProvider();

    Set<Object> allSelectedElements = Collections.unmodifiableSet(getAllSelectedElements());
    Option<Set<Object>> response = askUserForNewSelection(parent, allSelectedElements);
    if (response.some()) {
      Set<Object> selectedAfter = response.get();
      applyRequestedChanges(allSelectedElements, selectedAfter);
      assert selectedAfter.equals(allSelectedElements);
      result = true;
    }
    diagram = null;
    dialog = null;
    contentProvider = null;
    return result;
  }

  /**
   * Return all selected elements of the diagram that are display in the tree.
   * 
   * @return All selected elements of the diagram that are display in the tree.
   */
  protected Set<Object> getAllSelectedElements() {
    // get the previously selected elements
    Set<Object> set = Sets.newHashSet();
    List<DDiagramElement> treeElements = diagram.getDiagramElements();
    for (DDiagramElement element : treeElements) {
      if ((Predicates.and(isSelected, Predicates.not(isGrayed))).apply(element)) {
        // and it is optional
        if (FilteringSiriusUtils.hasAssociatedCriteria(element)) {
          set.add(element);
        }
      }
    }
    return set;
  }

  /**
   * Asks the user to edit the set of elements which should be selected/match the criterion being edited.
   * 
   * @param parent
   *          the parent shell to use if user interaction requires opening new windows.
   * @param initialSelection
   *          the set of elements to display as checked on dialog opening.
   * @return the new set of all the elements in the diagram which were selected by the user, or
   *         <code>Options.newNone()</code> if the user canceled the operation.
   */
  protected Option<Set<Object>> askUserForNewSelection(Shell parent, Set<Object> initialSelection) {
    setupDialog(parent, initialSelection);
    int result = dialog.open();
    if (result == Window.OK) {
      Set<Object> selectedAfter = getElementsSelectedAfter();
      return Options.newSome(selectedAfter);
    } // else
    return Options.newNone();
  }

  public SiriusCriteriaSelectionDialog createCustomTreeSelectionDialog(Shell parent, EObject diagram) {
    Project project = CapellaProjectHelper.getProject(((DSemanticDiagram) diagram).getTarget());
    return new SiriusCriteriaSelectionDialog(parent, new AdvancedFeatureLabelProvider(), contentProvider, project,
        diagram);
  }

  /**
   * Create an configure a selection dialog which allows the user to select a sub-set of the elements in the diagram.
   * 
   * @param parent
   *          the parent shell.
   * @param initialSelection
   *          the set of elements to display as checked on dialog opening.
   */
  protected void setupDialog(Shell parent, Set<Object> initialSelection) {
    dialog = createCustomTreeSelectionDialog(parent, diagram);
    dialog.setTitle(title);

    String msg = message;
    if (!Predicates.alwaysFalse().equals(isGrayed)) {
      StringBuilder sb = new StringBuilder(message);
      sb.append("\n"); //$NON-NLS-1$
      sb.append("The wizard will have no effect on grayed elements."); //$NON-NLS-1$
      msg = sb.toString();
    }
    dialog.setMessage(msg);
    dialog.setInput(diagram);
    dialog.setInitialElementSelections(new ArrayList<>(initialSelection));
  }

  /**
   * Returns the diagram elements selected when the dialog is about to close.
   * 
   * @return diagram elements
   */
  protected Set<Object> getElementsSelectedAfter() {
    // Initialize the return selectedElements
    Set<Object> selectedElements = Sets.newHashSet();

    // Loop through all diagram elements
    for (DDiagramElement diagramElement : diagram.getDiagramElements()) {
      List<EObject> semantics = FilteringSiriusUtils.getRealSemanticElements(diagramElement);
      for (EObject eObject : semantics) {
        List<FilteringCriterion> featureList = FilteringUtils.getAssociatedCriteria(eObject);
        // We are only going to get optional elements
        if (!featureList.isEmpty()) {
          // It is not common, check selection
          for (FilteringCriterion feature : featureList) {
            if (dialog.getCheckedElements().contains(feature)) {
              if (!selectedElements.contains(diagramElement)) {
                selectedElements.add(diagramElement);
              }
            }
          }
        }
      }
    }
    return selectedElements;
  }

  /**
   * Updates the status of the elements according to the user request.
   * 
   * @param selectedBefore
   *          all (and only) the elements in the diagram which were actually pinned before the action.
   * @param selectedAfter
   *          all (and only) the elements in the diagram which should be pinned as requested by the user.
   */
  protected void applyRequestedChanges(Set<Object> selectedBefore, Set<Object> selectedAfter) {
    for (Object dde : Sets.difference(selectedBefore, selectedAfter)) {
      deselectedAction.apply(dde);
    }
    for (Object dde : Sets.difference(selectedAfter, selectedBefore)) {
      selectedAction.apply(dde);
    }
  }

  /**
   * Label Provider
   */
  class AdvancedFeatureLabelProvider extends LabelProvider implements IColorProvider, IFontProvider {

    @SuppressWarnings("synthetic-access")
    @Override
    public Color getForeground(Object element) {
      Color foreground = null;
      if (isGrayed.apply(element)) {
        foreground = VisualBindingManager.getDefault().getColorFromName("light_gray"); //$NON-NLS-1$
      }
      return foreground;
    }

    @Override
    public String getText(Object element) {
      if (element instanceof NamedElement) {
        return ((NamedElement) element).getName();
      }
      return element == null ? "" : element.toString();//$NON-NLS-1$
    }

    @Override
    public Color getBackground(Object element) {
      return null;
    }

    @Override
    public Font getFont(final Object element) {
      Font result = JFaceResources.getDefaultFont();
      if (element instanceof EnumerationPropertyLiteral) {
        EnumerationPropertyLiteral feature = (EnumerationPropertyLiteral) element;
        if (!FilteringSiriusUtils.allElementsAssociatedToAFeatureAreVisible(diagram, feature)) {
          result = JFaceResources.getFontRegistry().getItalic(JFaceResources.DEFAULT_FONT);
        }
      }
      return result;
    }
  }
}