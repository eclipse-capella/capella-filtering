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
package org.polarsys.capella.filtering.sirius.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.filtering.AbstractFilteringResult;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringModel;
import org.polarsys.capella.filtering.FilteringResult;
import org.polarsys.capella.filtering.FilteringResults;
import org.polarsys.capella.filtering.semantic.queries.FilteringCriterionQuery;
import org.polarsys.capella.filtering.semantic.queries.FilteringResultsUsingFilteringCriterionQuery;
import org.polarsys.capella.filtering.tools.FilteringToolsPlugin;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;
import org.polarsys.capella.filtering.tools.view.GlobalFiteringCache;

/**
 * Services used in the odesign file as expressions
 */
public class FilteringServices {

  // Needed code for odesign to get the service
  private static FilteringServices service = null;

  public static FilteringServices getService() {
    if (service == null) {
      service = new FilteringServices();
    }
    return service;
  }

  public boolean hasDecorationPLText(EObject view) {
    if (view instanceof DDiagramElement) {
      EObject target = ((DDiagramElement) view).getTarget();
      List<FilteringCriterion> f = FilteringUtils.getAssociatedCriteria(target);
      boolean hasDecoration = !f.isEmpty();
      return hasDecoration;
    }
    return false;
  }

  public String getPLText(EObject container) {
    // if (view instanceof DDiagramElement) {
    // String a = FilteringUtils.getAssociatedCriteria((DDiagramElement)
    // view).stream().map(x ->
    // x.getName()).collect(Collectors.joining("\n"));
    // return a;//+" "+(Math.random()*2000);
    // }
    return "TOTO TATA";

  }

  public RGBValues getBorderColor() {
    return RGBValues.create(255, 100, 50);
  }

  public RGBValues getStrokeColor() {
    return RGBValues.create(255, 100, 50);
  }

  public Integer getBorderSize() {
    return Integer.valueOf(3);
  }

  public Collection<Object> getAssociatedElements(FilteringCriterion filteringCriterion) {
    List<Object> queryResult = new FilteringCriterionQuery().compute(filteringCriterion);
    return queryResult;

  }

  /**
   * get associated criteria for a Capella Element
   * 
   * @param capellaElement
   * @return
   */
  public List<FilteringCriterion> getAssociatedCriteria(EObject capellaElement) {
    return FilteringUtils.getAssociatedCriteria(capellaElement);

  }

  /**
   * get associated results i.e. results that contains the specified Capella element
   * 
   * @param CapellaElement
   * @return
   */
  public Collection<Object> getAssociatedResults(EObject capellaElement) {

    List<FilteringCriterion> associatedCriteria = FilteringUtils.getAssociatedCriteria(capellaElement);
    FilteringResultsUsingFilteringCriterionQuery query = new FilteringResultsUsingFilteringCriterionQuery();
    HashSet<Object> resultSet = new HashSet<>();
    associatedCriteria.forEach(criterion -> resultSet.addAll(query.compute(criterion)));
    return resultSet;
  }

  public Collection<ModelElement> getAssociatedElements(AbstractFilteringResult filteringResult) {

    SystemEngineering root = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(filteringResult);

    if (null == root) {
      return Collections.emptySet();
    }

    Collection<?> allContents = EcoreUtil2.getAllContents(Collections.singletonList(root));

    EList<FilteringCriterion> resultCriteria = filteringResult.computeFilteringCriterionSet().getFilteringCriteria();

    Set<ModelElement> result = allContents.stream().filter(elt -> elt instanceof ModelElement)
        .map(elt -> (ModelElement) elt).filter(elt -> FilteringUtils.notInstanceOfFilteringPackageMetaclass(elt))
        .filter(elt -> hasAtLeastOneCriteria(elt, resultCriteria)).collect(Collectors.toSet());

    return result;

  }

  private boolean hasAtLeastOneCriteria(EObject elt, EList<FilteringCriterion> criteria) {
    return !intersectionOf(getAssociatedCriteria(elt), criteria).isEmpty();
  }

  /*
   * Used to filter diagrams at refresh
   */
  public boolean isFilteredByGlobalFilteringCache(EObject element) {

    GlobalFiteringCache globalFilteringCache = FilteringToolsPlugin.getGlobalFilteringCache();

    if (globalFilteringCache.isEnabled()) {

      Project project = CapellaProjectHelper.getProject(element);
      AbstractFilteringResult globalFilteringResult = globalFilteringCache.get(project);

      // get element associated criteria
      List<FilteringCriterion> associatedCriteria = getAssociatedCriteria(element);

      // element is a common element (no filtering criteria assigned)
      if (associatedCriteria == null || associatedCriteria.isEmpty()) {
        return true;
      }
      // element is an optional element and global filter is empty
      if (!associatedCriteria.isEmpty() && globalFilteringResult == null) {
        return false;
      }

      FilteringCriterionSet globalCriterionSet = globalFilteringResult.computeFilteringCriterionSet();

      // element is an optional element => apply global filter
      EList<FilteringCriterion> globalFilteringCriteria = globalCriterionSet.getFilteringCriteria();

      if (intersectionOf(globalFilteringCriteria, associatedCriteria).isEmpty()) {
        return false;
      }
    }
    return true;
  }

  /**
   * get all filtering criteria owned by containing Capella project
   * 
   * @param modelElement
   * @return
   */
  public Collection<FilteringCriterion> getAllFilteringCriteria(EObject modelElement) {
    List<FilteringModel> filteringModels = FilteringUtils.getFilteringModels(modelElement, true);
    return FilteringUtils.getOwnedFilteringCriteria(filteringModels);
  }

  public Collection<AbstractFilteringResult> getAllFilteringResults(EObject modelElement) {
    FilteringResults configs = FilteringUtils.getFilteringResults(modelElement);
    return FilteringUtils.getAllFilteringResults(configs);
  }

  //
  // public Collection<AbstractFilteringResult>
  // getAllFilteringResults(FilteringCriterion feature) {
  // List<AbstractFilteringResult> filteringResultList = new ArrayList<>();
  //
  // FilteringResults filteringResults =
  // FilteringUtils.getFilteringResults(feature);
  //
  // if (filteringResults != null) {
  // for (AbstractFilteringResult abstractFilteringResult :
  // filteringResults.getFilteringResults()) {
  // filteringResultList.add(abstractFilteringResult);
  // }
  // }
  // return filteringResultList;
  // }

  private <T> List<T> intersectionOf(List<T> list1, List<T> list2) {
    List<T> list = new ArrayList<T>();

    for (T t : list1) {
      if (list2.contains(t)) {
        list.add(t);
      }
    }

    return list;
  }

  public Collection<AbstractFilteringResult> hasFilteringCriterion(FilteringCriterion filteringCriterion) {
    List<AbstractFilteringResult> configurations = new ArrayList<>();

    FilteringResults configs = FilteringUtils.getFilteringResults(filteringCriterion);

    if (configs != null) {
      for (AbstractFilteringResult config : FilteringUtils.getAllFilteringResults(configs)) {
        EList<FilteringCriterion> filteringCriteria = config.computeFilteringCriterionSet().getFilteringCriteria();

        if ((filteringCriteria != null) && filteringCriteria.contains(filteringCriterion)) {
          configurations.add(config);
        }
      }
    }
    return configurations;
  }

  public EObject getFilteringModel(EObject modelElement) {
    return FilteringUtils.getFilteringModel(modelElement);
  }

  public EObject getFilteringResults(EObject modelElement) {
    return FilteringUtils.getFilteringResults(modelElement);
  }

  public EObject removeFilteringCriterionFromFilteringResult(EObject context, FilteringResult config,
      FilteringCriterion feat) {
    if (config.getFilteringCriteria() != null) {
      config.getFilteringCriteria().remove(feat);
    }
    return context;
  }

  public EObject addFilteringCriterionToResult(EObject context, FilteringResult result, FilteringCriterion criterion) {
    if ((result.getFilteringCriteria() != null) && !result.getFilteringCriteria().contains(criterion)) {
      result.getFilteringCriteria().add(criterion);
    }
    return context;
  }

}
