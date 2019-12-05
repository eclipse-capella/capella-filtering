
package org.polarsys.capella.filtering.impl;

import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;

import org.polarsys.capella.filtering.AbstractFilteringResult;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Abstract Filtering Result</b></em>'. <!--
 * end-user-doc -->
 *
 * @generated
 */
public abstract class AbstractFilteringResultImpl extends NamedElementImpl implements AbstractFilteringResult {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractFilteringResultImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FilteringPackage.Literals.ABSTRACT_FILTERING_RESULT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public FilteringCriterionSet computeFilteringCriterionSet() {

		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();

	}

} // AbstractFilteringResultImpl