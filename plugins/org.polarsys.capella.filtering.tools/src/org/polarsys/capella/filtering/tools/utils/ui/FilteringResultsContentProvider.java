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
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.filtering.AbstractFilteringResult;
import org.polarsys.capella.filtering.ComposedFilteringResult;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringResultSet;
import org.polarsys.capella.filtering.FilteringResults;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

/**
 * FilteringResults Content Provider
 */
public class FilteringResultsContentProvider implements ITreeContentProvider {

  private FilteringResults filteringResults;

  public Object[] getElements(Object inputElement) {
    return getChildren(inputElement);
  }

  public Object[] getChildren(Object parentElement) {
    List<Object> children = new ArrayList<Object>();

    if (parentElement instanceof Collection<?/* Project */>) {
      children.addAll((Collection<?>) parentElement);

    } else if (parentElement instanceof Project) {
      FilteringResults filteringResults = FilteringUtils.getFilteringResults((Project) parentElement);
      if (filteringResults != null) {
        children.addAll(doGetElements(filteringResults));
      }

    } else if (parentElement instanceof ComposedFilteringResult) {
      children.addAll(FilteringUtils.getComposedResultChildren((ComposedFilteringResult) parentElement));

    } else if (parentElement instanceof FilteringResultSet) {
      children.addAll(((FilteringResultSet) parentElement).getFilteringResults());

    } else if (parentElement instanceof DSemanticDiagram) {

      return getChildren(((DSemanticDiagram) parentElement).getTarget());

    } else if (parentElement instanceof SystemEngineering) {
       children.addAll(FilteringUtils.getMainAndReferencedVariantProjects((EObject) parentElement));
    }
    return children.toArray();
  }

  private List<AbstractFilteringResult> doGetElements(FilteringResults filteringResults) {
    return FilteringUtils.getAllFilteringResults(filteringResults);
  }

  public Set<Object> getAllContents() {
    Set<Object> contents = new HashSet<Object>();
    if ((filteringResults != null) && (filteringResults.getFilteringResults() != null)) {
      for (Object content : filteringResults.getFilteringResults()) {
        contents.add(content);
      }
    }
    return contents;
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

  public boolean hasChildren(Object element) {
    boolean hasChildren = element instanceof Project || element instanceof ComposedFilteringResult
        || element instanceof FilteringResultSet;

    return hasChildren;
  }

}
