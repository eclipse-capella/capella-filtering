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

package org.polarsys.capella.filtering.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.polarsys.capella.common.lib.IdGenerator;

import org.polarsys.capella.filtering.*;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class FilteringFactoryImpl extends EFactoryImpl implements FilteringFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public static FilteringFactory init() {
		try {
			FilteringFactory theFilteringFactory = (FilteringFactory) EPackage.Registry.INSTANCE
					.getEFactory(FilteringPackage.eNS_URI);
			if (theFilteringFactory != null) {
				return theFilteringFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new FilteringFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public FilteringFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case FilteringPackage.FILTERING_MODEL:
			return (EObject) createFilteringModel();
		case FilteringPackage.FILTERING_CRITERION:
			return (EObject) createFilteringCriterion();
		case FilteringPackage.FILTERING_RESULTS:
			return (EObject) createFilteringResults();
		case FilteringPackage.FILTERING_RESULT:
			return (EObject) createFilteringResult();
		case FilteringPackage.ASSOCIATED_FILTERING_CRITERION_SET:
			return (EObject) createAssociatedFilteringCriterionSet();
		case FilteringPackage.CREATION_DEFAULT_FILTERING_CRITERION_SET:
			return (EObject) createCreationDefaultFilteringCriterionSet();
		case FilteringPackage.FILTERING_RESULT_PKG:
			return (EObject) createFilteringResultPkg();
		case FilteringPackage.FILTERING_CRITERION_PKG:
			return (EObject) createFilteringCriterionPkg();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public FilteringModel createFilteringModel() {
		FilteringModelImpl filteringModel = new FilteringModelImpl();
		return filteringModel;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public FilteringCriterion createFilteringCriterion() {
		FilteringCriterionImpl filteringCriterion = new FilteringCriterionImpl();
		return filteringCriterion;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public FilteringResults createFilteringResults() {
		FilteringResultsImpl filteringResults = new FilteringResultsImpl();
		return filteringResults;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public FilteringResult createFilteringResult() {
		FilteringResultImpl filteringResult = new FilteringResultImpl();
		return filteringResult;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public AssociatedFilteringCriterionSet createAssociatedFilteringCriterionSet() {
		AssociatedFilteringCriterionSetImpl associatedFilteringCriterionSet = new AssociatedFilteringCriterionSetImpl();
		return associatedFilteringCriterionSet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CreationDefaultFilteringCriterionSet createCreationDefaultFilteringCriterionSet() {
		CreationDefaultFilteringCriterionSetImpl creationDefaultFilteringCriterionSet = new CreationDefaultFilteringCriterionSetImpl();
		return creationDefaultFilteringCriterionSet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public FilteringResultPkg createFilteringResultPkg() {
		FilteringResultPkgImpl filteringResultPkg = new FilteringResultPkgImpl();
		return filteringResultPkg;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public FilteringCriterionPkg createFilteringCriterionPkg() {
		FilteringCriterionPkgImpl filteringCriterionPkg = new FilteringCriterionPkgImpl();
		return filteringCriterionPkg;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public FilteringPackage getFilteringPackage() {
		return (FilteringPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static FilteringPackage getPackage() {
		return FilteringPackage.eINSTANCE;
	}

	// begin-capella-code

	// end-capella-code
} // FilteringFactoryImpl