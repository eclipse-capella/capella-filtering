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
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringModel;
import org.polarsys.capella.filtering.sirius.ui.FilteringSiriusUtils;
import org.polarsys.capella.filtering.tools.dialogs.FilteringCriteriaSelectionDialog;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;
import org.polarsys.capella.filtering.tools.utils.ui.CriteriaContentProvider;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class SiriusCriteriaSelectionDialog extends FilteringCriteriaSelectionDialog {

  EObject baseEObject;

  /**
   * @param parent
   * @param labelProvider
   * @param contentProvider
   * @param baseEObject
   */
  public SiriusCriteriaSelectionDialog(Shell parent, ILabelProvider labelProvider,
      CriteriaContentProvider contentProvider, Project project, EObject digaram) {
    super(parent, labelProvider, contentProvider, project);
    this.baseEObject = digaram;
  }

  @Override
  public void setInitialSelections(Object[] selectedElements) {
    setInitialElementSelections(Arrays.asList(selectedElements));
  }

  @Override
  public void setInitialElementSelections(List selectedElements) {
    DDiagram diagram = (DDiagram) baseEObject;
    List<FilteringModel> filteringModels = FilteringSiriusUtils.getFilteringModels(diagram, true);
    Set<FilteringCriterion> filteredSelection = Sets.newHashSet();
    if (!filteringModels.isEmpty() && FilteringUtils.hasFilteringFeatures(filteringModels)) {
      // Loop through all Filtering features
      for (Object o : diagram.getDiagramElements()) {
        if (o instanceof DDiagramElement) {
          DDiagramElement diagramElement = (DDiagramElement) o;
          if (!diagramElement.isVisible()) {
            continue;
          }
          // Loop through semantic elements
          for (EObject semanticElement : FilteringSiriusUtils.getRealSemanticElements(diagramElement)) {
            List<FilteringCriterion> associatedCriteria = FilteringUtils.getExplicitAssociatedCriteria(semanticElement);
            filteredSelection.addAll(associatedCriteria);
          }
        }
      }
    }
    super.setInitialElementSelections(new ArrayList<FilteringCriterion>(filteredSelection));
  }
}
