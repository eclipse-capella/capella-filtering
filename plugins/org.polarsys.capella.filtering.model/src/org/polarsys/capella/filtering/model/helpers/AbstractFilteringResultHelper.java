package org.polarsys.capella.filtering.model.helpers;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.filtering.AbstractFilteringResult;

/**
 * @generated
 */
public class AbstractFilteringResultHelper {

  private static final AbstractFilteringResultHelper instance = new AbstractFilteringResultHelper();

  /**
   * @generated
   */
  public static AbstractFilteringResultHelper getInstance() {
    return instance;
  }

  /**
   * @generated
   */
  public Object doSwitch(AbstractFilteringResult object, EStructuralFeature feature) {
    // handle derivated feature

    // delegate to parent helper
    return org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper.getInstance()
        .doSwitch(object, feature);

  }

}