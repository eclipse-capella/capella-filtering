package org.polarsys.capella.filtering.provider.sections;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.filtering.AbstractFilteringResult;

/**
 * This is the item provider adapter for a {@link AbstractFilteringResultSection} object. <!-- begin-user-doc --> <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class AbstractFilteringResultSection extends NamedElementSection {
  /**
   * @generated
   */
  @Override
  public boolean select(Object toTest) {
    EObject obj = CapellaAdapterHelper.resolveSemanticObject(toTest);

    return obj != null
        && obj.eClass().equals(org.polarsys.capella.filtering.FilteringPackage.eINSTANCE.getAbstractFilteringResult());
  }
}
