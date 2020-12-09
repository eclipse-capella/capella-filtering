/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.filtering.migration.contribution;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.osgi.framework.Version;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.capella.filtering.FilteringFactory;
import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.capella.filtering.migration.Activator;
import org.polarsys.kitalpha.ad.metadata.metadata.ViewpointReference;

public class FilteringMigrationContribution extends AbstractMigrationContribution {

  public static final String OLD_NSURI = "http://www.thalesgroup.com/mde/productline";
  public static final String OLD_NSPREFIX = "productline";
  public static final String OLD_XMLNS = XMLResource.XML_NS + ":" + OLD_NSPREFIX;

  public static final String OLD_VIEWPOINT = "com.thalesgroup.mde.melody.productline";
  public static final String NEW_VIEWPOINT = "org.polarsys.capella.filtering";

  public static final String OLD_DIAGRAM_PLUGIN = "com.thalesgroup.mde.melody.productline.doremi.analysis";
  public static final String NEW_DIAGRAM_PLUGIN = "org.polarsys.capella.filtering.sirius.analysis";

  public static final String OLD_ODESIGN = "configurations.odesign";
  public static final String NEW_ODESIGN = "FilteringResults.odesign";

  public static final String OLD_ODESIGN_VIEWPOINT = "ownedViewpoints[name='Variability']";
  public static final String NEW_ODESIGN_VIEWPOINT = "ownedViewpoints[name='Filtering']";

  public static final String OLD_TABLE_NAME = "[VAR]%20Product%20family";
  public static final String NEW_TABLE_NAME = "[FILTERING]%20Product%20family";

  public static final String OLD_TABLE_LINE_MAPPING = "VariabilityFeature%20list";
  public static final String NEW_TABLE_LINE_MAPPING = "FilteringCriterion%20list";

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
  
  @Override
  public Object getValue(EObject peekObject, EStructuralFeature feature, Object value, int position, Resource resource,
      MigrationContext context) {

    // We replace in AFM the viewpoint id
    if (OLD_VIEWPOINT.equals(value)) {
      return NEW_VIEWPOINT;

    } else if (peekObject instanceof ViewpointReference) {
      ViewpointReference viewpointReference = (ViewpointReference) peekObject;
      if (NEW_VIEWPOINT.equals(viewpointReference.getVpId())) {
        // We store in AFM the current version of the viewpoint
        Version version = Activator.getContext().getBundle().getVersion();
        return version.getMajor() + "." + version.getMinor() + "." + version.getMicro();
      }

    } else if (value instanceof EObject && ((EObject) value).eIsProxy()) {
      // If we refer an old odesign uri, we replace it by the new one
      URI uri = ((InternalEObject) value).eProxyURI();
      String path = uri.path();
      if (path != null && path.contains(OLD_DIAGRAM_PLUGIN + "/")) {
        String uriValue = uri.toPlatformString(true);
        String fragment = uri.fragment();

        uriValue = uriValue.replace(OLD_DIAGRAM_PLUGIN, NEW_DIAGRAM_PLUGIN);
        uriValue = uriValue.replace(OLD_ODESIGN, NEW_ODESIGN);
        
        fragment = fragment.replace(OLD_ODESIGN_VIEWPOINT, NEW_ODESIGN_VIEWPOINT);
        fragment = fragment.replace(OLD_TABLE_NAME, NEW_TABLE_NAME);
        fragment = fragment.replace(OLD_TABLE_LINE_MAPPING, NEW_TABLE_LINE_MAPPING);

        uri = URI.createPlatformPluginURI(uriValue, true).appendFragment(fragment);
        ((InternalEObject) value).eSetProxyURI(uri);
      }
    }
    return super.getValue(peekObject, feature, value, position, resource, context);
  }

  @Override
  public EStructuralFeature getFeature(EObject peekObject, EStructuralFeature feature, Resource resource,
      MigrationContext context) {

    if (FilteringPackage.Literals.FILTERING_RESULTS__CONFIGURATIONS.equals(feature)) {
      return FilteringPackage.Literals.FILTERING_RESULTS__FILTERING_RESULTS;

    } else if (FilteringPackage.Literals.FILTERING_MODEL__OWNED_VARIABILITY_FEATURES.equals(feature)) {
      return FilteringPackage.Literals.FILTERING_MODEL__OWNED_FILTERING_CRITERIA;

    } else if (FilteringPackage.Literals.FILTERING_CRITERION_SET__VARIABILITY_FEATURES.equals(feature)) {
      return FilteringPackage.Literals.FILTERING_CRITERION_SET__FILTERING_CRITERIA;
    }

    return super.getFeature(peekObject, feature, resource, context);
  }

  @Override
  public EFactory getEFactory(String prefix, Resource resource, MigrationContext context) {
    if (FilteringPackage.eNS_PREFIX.equals(prefix)) {
      return FilteringFactory.eINSTANCE;
    }
    return super.getEFactory(prefix, resource, context);
  }

  @Override
  public String getNSPrefix(String prefix, MigrationContext context) {
    if (OLD_XMLNS.equals(prefix)) {
      return XMLResource.XML_NS + ":" + FilteringPackage.eNS_PREFIX;
    }
    return super.getNSPrefix(prefix, context);
  }

  @Override
  public String getNSURI(String prefix, String nsUri, MigrationContext context) {
    if (OLD_NSURI.equals(nsUri)) {
      return FilteringPackage.eNS_URI;
    }
    return super.getNSURI(prefix, nsUri, context);
  }

  @Override
  public void contributePackageRegistry(Registry packageRegistry, MigrationContext context) {
    packageRegistry.put(FilteringPackage.eNS_URI, FilteringPackage.eINSTANCE);
  }

}
