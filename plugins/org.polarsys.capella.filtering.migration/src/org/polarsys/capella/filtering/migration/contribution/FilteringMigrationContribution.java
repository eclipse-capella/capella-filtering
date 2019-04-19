/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.filtering.migration.contribution;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringFactory;
import org.polarsys.capella.filtering.FilteringModel;
import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.capella.filtering.FilteringResults;
import org.polarsys.kitalpha.ad.metadata.metadata.ViewpointReference;

public class FilteringMigrationContribution  extends AbstractMigrationContribution {

  @Override
  public Object getValue(EObject peekObject, EStructuralFeature feature, Object value, int position, Resource resource,
      MigrationContext context) {
    if ("com.thalesgroup.mde.melody.productline".equals(value)) {
      return "org.polarsys.capella.filtering";
    } else if (peekObject instanceof ViewpointReference) {
      ViewpointReference viewpointReference = (ViewpointReference) peekObject;
      if ("org.polarsys.capella.filtering".equals(viewpointReference.getVpId())) {
        return "1.3.0";
      }
    } else if (peekObject instanceof DView && value instanceof Viewpoint) {
      Viewpoint viewpoint = (Viewpoint) value;
      if (viewpoint.eIsProxy()) {
        URI uri = ((InternalEObject)viewpoint).eProxyURI();
        if ("/plugin/com.thalesgroup.mde.melody.productline.doremi.analysis/description/configurations.odesign".equals(uri.path())) {
          Viewpoint filteringViewpoint = ViewpointRegistry.getInstance().getViewpoints().stream().filter(v -> v.getName().contains("iltering")).findFirst().get();
          return filteringViewpoint;
        }
      }
    }
    return super.getValue(peekObject, feature, value, position, resource, context);
  }

  @Override
  public String getFeatureName(String prefix, String name, boolean isElement, EObject peekObject, String value,
      Resource resource, MigrationContext context) {
    if (value != null && peekObject instanceof FilteringResults && "configurations".equals(name)) {
      return "filteringResults";
    } else if (value != null && peekObject instanceof FilteringModel && "ownedVariabilityFeatures".equals(name)) {
      return "ownedFilteringCriteria";
    } else if (value != null && peekObject instanceof AssociatedFilteringCriterionSet && "variabilityFeatures".equals(name)) {
      return "filteringCriteria";
    }
    return super.getFeatureName(prefix, name, isElement, peekObject, value, resource, context);
  }

  @Override
  public EFactory getEFactory(String prefix, Resource resource, MigrationContext context) {
    if ("filtering".equals(prefix)) {
      return FilteringFactory.eINSTANCE;
    }
    return super.getEFactory(prefix, resource, context);
  }

  @Override
  public String getNSPrefix(String prefix, MigrationContext context) {
    if ("xmlns:productline".equals(prefix)) {
      return "xmlns:filtering";
    }
    return super.getNSPrefix(prefix, context);
  }

  @Override
  public String getNSURI(String prefix, String nsUri, MigrationContext context) {
    if ("http://www.thalesgroup.com/mde/productline".equals(nsUri)) {
      return "http://www.polarsys.org/capella/filtering";
    }
    return super.getNSURI(prefix, nsUri, context);
  }

  @Override
  public void contributePackageRegistry(Registry packageRegistry, MigrationContext context) {
    packageRegistry.put(FilteringPackage.eNS_URI, FilteringPackage.eINSTANCE);
  }

  @Override
  public String getQName(EObject peekObject, String typeQName, EStructuralFeature feature, Resource resource,
      XMLHelper helper, MigrationContext context) {
    switch (typeQName) {
    case "productline:Configurations":
      return "filtering:FilteringResults";
    case "productline:Configuration":
      return "filtering:FilteringResult";
    case "productline:FeatureModel":
      return "filtering:FilteringModel";
    case "productline:VariabilityFeature":
      return "filtering:FilteringCriterion";
    case "productline:AssociatedFeatureSet":
      return "filtering:AssociatedFilteringCriterionSet";
    case "productline:CreationDefaultFeatureSet":
      return "filtering:CreationDefaultFilteringCriterionSet";
    default:
      break;
    }
    return super.getQName(peekObject, typeQName, feature, resource, helper, context);
  }
}
