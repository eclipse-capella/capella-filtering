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
package org.polarsys.capella.filtering.sirius.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.diagramtype.DiagramTypeDescriptorRegistry;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramTypeDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringModel;
import org.polarsys.capella.filtering.FilteringResults;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Sets;

public class FilteringSiriusUtils {

  private FilteringSiriusUtils() {
    //
  }

  /**
   * Get product line feature model given a diagram
   * 
   * @param diagram
   * @return the FilteringModel that contains the features
   */
  public static FilteringModel getFilteringModel(DDiagram diagram) {
    List<FilteringModel> filteringModels = getFilteringModels(diagram, false);
    return !filteringModels.isEmpty() ? filteringModels.get(0) : null;
  }

  public static List<FilteringModel> getFilteringModels(DDiagram diagram, boolean includeReferencedLibraries) {
    DAnalysis dAnalysis = new EObjectQuery(diagram).getDAnalysis();
    List<EObject> models = dAnalysis.getModels();
    Set<FilteringModel> result = new HashSet<>();
    if ((models != null) && !models.isEmpty()) {
      for (EObject project : models) {
        result.addAll(FilteringUtils.getFilteringModels(project, includeReferencedLibraries));
      }
    }
    return new ArrayList<>(result);
  }

  /**
   * Get product line configurations given a diagram
   * 
   * @param diagram
   * @return the FilteringModel that contains the features
   */
  public static FilteringResults getFilteringResults(DDiagram diagram) {
    EObject dAnalysis = EcoreUtil.getRootContainer(diagram);
    List<EObject> models = ((DAnalysis) dAnalysis).getModels();
    if ((models != null) && !models.isEmpty()) {
      for (EObject project : models) {
        FilteringResults configurations = FilteringUtils.getFilteringResults(project);
        if (configurations != null) {
          return configurations;
        }
      }
    }
    return null;
  }

  /**
   * Get all visible diagram elements
   * 
   * @param diagram
   * @return visible diagram elements
   */
  public static Set<Object> getAllVisibleElements(DDiagram diagram) {
    Set<Object> set = Sets.newHashSet();
    for (DDiagramElement o : diagram.getDiagramElements()) {
      if (o.isVisible()) {
        set.add(o);
      }
    }
    return set;
  }

  /**
   * Get all invisible diagram elements
   * 
   * @param diagram
   * @return invisible diagram elements
   */
  public static Set<Object> getAllInvisibleElements(DDiagram diagram) {
    Set<Object> set = Sets.newHashSet();
    for (DDiagramElement o : diagram.getDiagramElements()) {
      if (!o.isVisible()) {
        set.add(o);
      }
    }
    return set;
  }

  /**
   * Check if all elements associated to a given feature are visible in a given diagram
   * 
   * @param diagram
   * @param feature
   * @return true if all elements associated to a feature are visible
   */
  public static boolean allElementsAssociatedToAFeatureAreVisible(DDiagram diagram,
      EnumerationPropertyLiteral feature) {
    for (Object o : getAllInvisibleElements(diagram)) {
      if (o instanceof DDiagramElement) {
        DDiagramElement diagramElement = (DDiagramElement) o;
        // Loop through semantic elements
        for (EObject semanticElement : getRealSemanticElements(diagramElement)) {
          if (FilteringUtils.getAssociatedCriteria(semanticElement).contains(feature)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * @param diagram
   * @param feature
   * @param includeCommon
   * @return elements associated to a given feature in a given diagram
   */
  public static List<DDiagramElement> getAllElementsAssociatedToAFeature(DDiagram diagram, FilteringCriterion feature,
      boolean includeCommon) {
    List<DDiagramElement> result = new ArrayList<>();
    for (Object o : diagram.getDiagramElements()) {
      if (o instanceof DDiagramElement) {
        DDiagramElement diagramElement = (DDiagramElement) o;
        // Loop through semantic elements
        for (EObject semanticElement : getRealSemanticElements(diagramElement)) {
          List<FilteringCriterion> associatedCriteria = FilteringUtils.getAssociatedCriteria(semanticElement);
          if (includeCommon && ((associatedCriteria == null) || associatedCriteria.isEmpty())
              && !result.contains(diagramElement)) {
            result.add(diagramElement);
            break;
          } else if (associatedCriteria != null && associatedCriteria.contains(feature)
              && !result.contains(diagramElement)) {
            result.add(diagramElement);
            break;
          }
        }
      }
    }
    return result;
  }

  /**
   * A diagram element semantic elements has associated features or not
   * 
   * @param element
   * @return
   */
  public static boolean hasAssociatedCriteria(DDiagramElement element) {
    for (EObject eObject : getRealSemanticElements(element)) {
      if (FilteringUtils.getAssociatedCriteria(eObject).isEmpty()) {
        return false;
      }
    }
    return true;
  }

  /**
   * This methods retrieves the semantic elements of a given diagram element. It was needed for the special case of
   * breakdown diagram edges that does not contains semantic elements
   * 
   * @param diagram
   *          element
   * @return the semantic elements
   */
  public static List<EObject> getRealSemanticElements(DDiagramElement element) {
    List<EObject> semanticElements = new ArrayList<>();
    // If it is not empty return the list
    if (!element.getSemanticElements().isEmpty()) {
      return element.getSemanticElements();
    } else if (element instanceof DEdge) { // Else check breakdown edges
      // special case
      DEdge edge = (DEdge) element;
      EdgeTarget edgeTarget = edge.getSourceNode();
      if (edgeTarget instanceof DDiagramElement) {
        return getRealSemanticElements((DDiagramElement) edgeTarget);
      }
    }
    return semanticElements;
  }

  /**
   * Get the melody elements of a given diagram element
   * 
   * @param element
   * @return
   */
  public static List<ExtensibleElement> getCapellaElements(DDiagramElement element) {
    List<ExtensibleElement> melodyElements = new ArrayList<>();
    for (EObject e : getRealSemanticElements(element)) {
      if (e instanceof ExtensibleElement) {
        melodyElements.add((ExtensibleElement) e);
      }
    }
    return melodyElements;
  }

  /**
   * Check if a diagram element represent melody elements Used mainly for decorators
   * 
   * @param element
   * @return
   */
  public static boolean containsCapellaElements(DDiagramElement element) {
    for (EObject e : element.getSemanticElements()) {
      if (e instanceof ExtensibleElement) {
        return true;
      }
    }
    return false;
  }

  /**
   * Contains associated features
   * 
   * @param element
   * @return
   */
  public static boolean containsAssociatedCriteria(DDiagramElement element) {
    for (ExtensibleElement me : getCapellaElements(element)) {
      if (!FilteringUtils.getAssociatedCriteria(me).isEmpty()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Contains explicit associated criterion
   * 
   * @param element
   * @return
   */
  public static boolean containsExplicitAssociatedCriteria(DDiagramElement element) {
    for (ExtensibleElement me : getCapellaElements(element)) {
      if (!FilteringUtils.getExplicitAssociatedCriteria(me).isEmpty()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Given a list of elements it checks if all elements associated to a feature are in this list
   * 
   * @param diagram
   * @param elements
   * @param feature
   * @return
   */
  public static boolean allElementsAssociatedToAFeatureAreInTheList(DDiagram diagram, List<?> elements,
      FilteringCriterion feature) {
    List<?> associatedElements = getAllElementsAssociatedToAFeature(diagram, feature, false);
    return (!associatedElements.isEmpty() && elements.containsAll(associatedElements));
  }

  /**
   * Indicates if the given ddiagram is allowing pin/unpin.
   * 
   * @param diagram
   *          the diagram to inspect
   * @return true if the given ddiagram is allowing layouting mode, false otherwise
   */
  public static Predicate<DDiagramElement> allowsHideReveal(DDiagram diagram) {
    // default return value is true (for basic DDiagram that are not handled
    // by any DiagramDescriptionProvider).
    Predicate<DDiagramElement> result = Predicates.alwaysTrue();

    // If an aird has been opened from the Package Explorer View, then
    // we return false as no diagram is associated to this editor
    if ((diagram == null) || (diagram.getDescription() == null) || !isEditable(diagram)) {
      return Predicates.alwaysFalse();
    }

    // If diagram is not null, we search for a possible
    // DiagramDescriptionProvider handling this type of diagram
    for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance()
        .getAllDiagramTypeDescriptors()) {
      if (diagramTypeDescriptor.getDiagramDescriptionProvider()
          .handles(diagram.getDescription().eClass().getEPackage())) {
        // This DiagramDescriptionProvider may forbid hide/reveal
        // actions.
        Predicate<DDiagramElement> allowsHideReveal = new Predicate<DDiagramElement>() {
          @Override
          public boolean apply(DDiagramElement element) {
            return diagramTypeDescriptor.getDiagramDescriptionProvider().allowsHideReveal(element);
          }
        };

        if (allowsHideReveal != null) {
          result = allowsHideReveal;
          break;
        }
      }
    }

    return result;
  }

  /**
   * Check if the diagram is editable
   * 
   * @param diagram
   * @return
   */
  public static boolean isEditable(DDiagram diagram) {
    IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault()
        .getPermissionAuthority(diagram.eResource().getResourceSet());
    return permissionAuthority.canEditInstance(diagram);
  }

  /**
   * Get active page
   * 
   * @return
   */
  public static IWorkbenchPage getActivePage() {
    IWorkbench wb = PlatformUI.getWorkbench();
    IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
    if (win != null) {
      return win.getActivePage();
    }
    return null;
  }

  /**
   * Get active part
   * 
   * @return
   */
  public static IDiagramWorkbenchPart getActivePart() {
    IWorkbenchPage activePage = getActivePage();
    if (activePage != null) {
      IWorkbenchPart workbenchPart = activePage.getActivePart();
      if (workbenchPart instanceof IDiagramWorkbenchPart) {
        return (IDiagramWorkbenchPart) workbenchPart;
      }
    }
    return null;
  }

  /**
   * Comma separated features
   * 
   * @param element
   * @return
   */
  public static String getCommaSeparatedFeatures(DDiagramElement element) {
    List<FilteringCriterion> featureList = getAssociatedCriteria(element);
    if (featureList.isEmpty()) {
      return null;
    }
    String features = ""; //$NON-NLS-1$
    for (FilteringCriterion feature : featureList) {
      features = features + feature.getName() + ", "; //$NON-NLS-1$
    }
    // remove last comma
    if (features.length() > 0) {
      features = features.substring(0, features.length() - ", ".length()); //$NON-NLS-1$
    }
    return features;
  }

  /**
   * Enter separated explicit criteria
   * 
   * @param element
   * @param sort
   * @return
   */
  public static String getEnterSeparatedExplicitFeatures(DDiagramElement element, boolean sort) {
    List<FilteringCriterion> featureList = getExplicitAssociatedCriteria(element);
    if (featureList.isEmpty()) {
      return null;
    }

    String[] strings = new String[featureList.size()];
    for (int x = 0; x < featureList.size(); x++) {
      strings[x] = featureList.get(x).getName();
    }

    if (sort) {
      Arrays.sort(strings);
    }

    String features = ""; //$NON-NLS-1$
    for (String feature : strings) {
      features = features + feature + "\n"; //$NON-NLS-1$
    }
    // remove last comma
    if (features.length() > 0) {
      features = features.substring(0, features.length() - "\n".length()); //$NON-NLS-1$
    }
    return features;
  }

  /**
   * Get the associated features of a diagram element semantic elements
   * 
   * @param element
   * @return
   */
  public static List<FilteringCriterion> getAssociatedCriteria(DDiagramElement element) {
    List<FilteringCriterion> featureList = new ArrayList<>();
    for (EObject semanticObject : element.getSemanticElements()) {
      for (FilteringCriterion feature : FilteringUtils.getAssociatedCriteria(semanticObject)) {
        if (!featureList.contains(feature)) {
          featureList.add(feature);
        }
      }
    }
    return featureList;
  }

  /**
   * Get the explicit associated criteria of a diagram element semantic elements
   * 
   * @param element
   * @return
   */
  public static List<FilteringCriterion> getExplicitAssociatedCriteria(DDiagramElement element) {
    List<FilteringCriterion> featureList = new ArrayList<>();
    for (EObject semanticObject : element.getSemanticElements()) {
      for (FilteringCriterion feature : FilteringUtils.getExplicitAssociatedCriteria(semanticObject)) {
        if (!featureList.contains(feature)) {
          featureList.add(feature);
        }
      }
    }
    return featureList;
  }
}
