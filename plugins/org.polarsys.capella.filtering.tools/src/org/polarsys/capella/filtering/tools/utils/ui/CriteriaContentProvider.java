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
package org.polarsys.capella.filtering.tools.utils.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringCriterionPkg;
import org.polarsys.capella.filtering.FilteringModel;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

/**
 * Filtering Criteria Content Provider.
 */
public class CriteriaContentProvider implements ITreeContentProvider {

  public Object[] getElements(Object inputElement) {
    return getChildren(inputElement);
  }

  public Object[] getChildren(Object parentElement) {
    List<Object> children = new ArrayList<Object>();

    if (parentElement instanceof Collection<?/* Project */>) {
      children.addAll((Collection<?>) parentElement);

    } else if (parentElement instanceof Project) {
      FilteringModel filteringModel = FilteringUtils.getFilteringModel((Project) parentElement);
      if (filteringModel != null) {
        children.addAll(doGetElements(filteringModel));
      }

    } else if (parentElement instanceof FilteringCriterionPkg) {
      children.addAll(FilteringUtils.getOwnedFilteringCriteriaAndPkgs((FilteringCriterionPkg) parentElement));

    } else if (parentElement instanceof DSemanticDiagram) {

      return getChildren(((DSemanticDiagram) parentElement).getTarget());

    } else if (parentElement instanceof SystemEngineering) {
      children.addAll(FilteringUtils.getMainAndReferencedVariantProjects((EObject) parentElement));
      
    } else if (parentElement instanceof EObject) {
      return getChildren(EcoreUtil2.getFirstContainer((EObject)parentElement, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING));
    }
    return children.toArray();
  }

  private List<EObject> doGetElements(FilteringModel filteringModel) {
    return FilteringUtils.getOwnedFilteringCriteriaAndPkgs(Arrays.asList(filteringModel));
  }

  public void dispose() {
    // Do nothing
  }

  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // Do nothing
  }

  public Object getParent(Object element) {
    if (element instanceof FilteringCriterion) {
      return CapellaProjectHelper.getProject((FilteringCriterion) element);
    }
    return null;
  }

  public boolean hasChildren(Object obj) {
    boolean hasChildren = obj instanceof Project || obj instanceof FilteringCriterionPkg;

    return hasChildren;
  }
}
