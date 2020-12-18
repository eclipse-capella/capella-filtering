/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.filtering.sirius.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.filtering.AbstractFilteringResult;
import org.polarsys.capella.filtering.ComposedFilteringResult;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringModel;
import org.polarsys.capella.filtering.FilteringResult;
import org.polarsys.capella.filtering.FilteringResults;
import org.polarsys.capella.filtering.semantic.queries.FilteringCriterionQuery;
import org.polarsys.capella.filtering.tools.FilteringToolsPlugin;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;
import org.polarsys.capella.filtering.tools.view.GlobalFiteringCache;

/**
 * Services used/exposed in the odesign file as expressions/queries
 * 
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
  /**
   * get all associated model elements to a specific {@link FilteringCriterion}
   * @param filteringCriterion
   * @return
   */
  public Collection<Object> getAssociatedElements(FilteringCriterion filteringCriterion) {
    List<Object> queryResult = new FilteringCriterionQuery().compute(filteringCriterion);
    return queryResult;
  }


  /**
   * get associated criteria for a specific EObject
   * 
   * @param eObj
   * @return
   */
  public List<FilteringCriterion> getAssociatedCriteria(EObject eObj) {
    return FilteringUtils.getAssociatedCriteria(eObj);

  }

  /**
   * get associated results i.e. results that contains the specified {@link ModelElement}
   * 
   * @param CapellaElement
   * @return
   */
  public Collection<AbstractFilteringResult> getAssociatedResults(ModelElement capellaElement) {

    Set<AbstractFilteringResult> result = new HashSet<AbstractFilteringResult>();

    FilteringResults filteringResults = FilteringUtils.getFilteringResults(capellaElement);
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(capellaElement);

    List<AbstractFilteringResult> allFilteringResults = FilteringUtils.getAllFilteringResults(filteringResults);

    for (AbstractFilteringResult abstractFilteringResult : allFilteringResults) {
      if (abstractFilteringResult instanceof FilteringResult) {

        if (hasAtLeastOneCriteria(capellaElement, ((FilteringResult) abstractFilteringResult).getFilteringCriteria()))
          result.add(abstractFilteringResult);
      } else if (abstractFilteringResult instanceof ComposedFilteringResult) {
        Set<EObject> derivation = FilteringUtils.computeDerivation((ComposedFilteringResult) abstractFilteringResult,
            sysEng);
        if (derivation.contains(capellaElement))
          result.add(abstractFilteringResult);
      }

    }
    return result;
  }

  /**
   * Finds all elements that are contained in results derivation
   * 
   * @param filteringResult
   * @return
   */
  public Collection<EObject> getAssociatedElements(AbstractFilteringResult filteringResult) {

    SystemEngineering root = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(filteringResult);

    if (null == root) {
      return Collections.emptySet();
    }

    Set<EObject> derivation = FilteringUtils.computeDerivation(filteringResult, root);

    return derivation;

  }

  private boolean hasAtLeastOneCriteria(EObject elt, EList<FilteringCriterion> criteria) {
    return !intersectionOf(getAssociatedCriteria(elt), criteria).isEmpty();
  }

  /*
   * Used to filter diagrams at refresh
   */
  public boolean isFilteredByGlobalFilteringCache(EObject element) {

    GlobalFiteringCache globalFilteringCache = FilteringToolsPlugin.getGlobalFilteringCache();
    Project project = CapellaProjectHelper.getProject(element);

    if (FilteringUtils.hasAssociatedCriteria(element) && globalFilteringCache.isEnabled(project)) {

      AbstractFilteringResult globalFilteringResult = globalFilteringCache.get(project).getFilteringResult();

      if (globalFilteringResult == null) {// no filtering result is cached => hide all elements
        return false;
      }

      Predicate<EObject> predicate = FilteringUtils.computePredicate(globalFilteringResult);

      if (predicate.test(element)) {
        return true;
      }
      return false;
    }

    return true;

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
