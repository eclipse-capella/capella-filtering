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

package org.polarsys.capella.filtering.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.polarsys.capella.common.lib.IdGenerator;

import org.polarsys.capella.filtering.*;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class FilteringFactoryImpl extends EFactoryImpl implements FilteringFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
			return createFilteringModel();
		case FilteringPackage.FILTERING_CRITERION:
			return createFilteringCriterion();
		case FilteringPackage.FILTERING_RESULTS:
			return createFilteringResults();
		case FilteringPackage.FILTERING_RESULT:
			return createFilteringResult();
		case FilteringPackage.ASSOCIATED_FILTERING_CRITERION_SET:
			return createAssociatedFilteringCriterionSet();
		case FilteringPackage.CREATION_DEFAULT_FILTERING_CRITERION_SET:
			return createCreationDefaultFilteringCriterionSet();
		case FilteringPackage.FILTERING_RESULT_PKG:
			return createFilteringResultPkg();
		case FilteringPackage.FILTERING_CRITERION_PKG:
			return createFilteringCriterionPkg();
		case FilteringPackage.COMPOSED_FILTERING_RESULT:
			return createComposedFilteringResult();
		case FilteringPackage.FILTERING_RESULT_SET:
			return createFilteringResultSet();
		case FilteringPackage.UNION_FILTERING_RESULT_SET:
			return createUnionFilteringResultSet();
		case FilteringPackage.EXCLUSION_FILTERING_RESULT_SET:
			return createExclusionFilteringResultSet();
		case FilteringPackage.INTERSECTION_FILTERING_RESULT_SET:
			return createIntersectionFilteringResultSet();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FilteringModel createFilteringModel() {
		FilteringModelImpl filteringModel = new FilteringModelImpl();
		//begin-capella-code
		filteringModel.setId(IdGenerator.createId());
		//end-capella-code
		return filteringModel;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FilteringCriterion createFilteringCriterion() {
		FilteringCriterionImpl filteringCriterion = new FilteringCriterionImpl();
		//begin-capella-code
		filteringCriterion.setId(IdGenerator.createId());
		//end-capella-code
		return filteringCriterion;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FilteringResults createFilteringResults() {
		FilteringResultsImpl filteringResults = new FilteringResultsImpl();
		//begin-capella-code
		filteringResults.setId(IdGenerator.createId());
		//end-capella-code
		return filteringResults;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FilteringResult createFilteringResult() {
		FilteringResultImpl filteringResult = new FilteringResultImpl();
		//begin-capella-code
		filteringResult.setId(IdGenerator.createId());
		//end-capella-code
		return filteringResult;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AssociatedFilteringCriterionSet createAssociatedFilteringCriterionSet() {
		AssociatedFilteringCriterionSetImpl associatedFilteringCriterionSet = new AssociatedFilteringCriterionSetImpl();
		//begin-capella-code
		associatedFilteringCriterionSet.setId(IdGenerator.createId());
		//end-capella-code
		return associatedFilteringCriterionSet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CreationDefaultFilteringCriterionSet createCreationDefaultFilteringCriterionSet() {
		CreationDefaultFilteringCriterionSetImpl creationDefaultFilteringCriterionSet = new CreationDefaultFilteringCriterionSetImpl();
		//begin-capella-code
		creationDefaultFilteringCriterionSet.setId(IdGenerator.createId());
		//end-capella-code
		return creationDefaultFilteringCriterionSet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FilteringResultPkg createFilteringResultPkg() {
		FilteringResultPkgImpl filteringResultPkg = new FilteringResultPkgImpl();
		//begin-capella-code
		filteringResultPkg.setId(IdGenerator.createId());
		//end-capella-code
		return filteringResultPkg;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FilteringCriterionPkg createFilteringCriterionPkg() {
		FilteringCriterionPkgImpl filteringCriterionPkg = new FilteringCriterionPkgImpl();
		//begin-capella-code
		filteringCriterionPkg.setId(IdGenerator.createId());
		//end-capella-code
		return filteringCriterionPkg;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComposedFilteringResult createComposedFilteringResult() {
		ComposedFilteringResultImpl composedFilteringResult = new ComposedFilteringResultImpl();
		//begin-capella-code
		composedFilteringResult.setId(IdGenerator.createId());
		//end-capella-code
		return composedFilteringResult;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FilteringResultSet createFilteringResultSet() {
		FilteringResultSetImpl filteringResultSet = new FilteringResultSetImpl();
		//begin-capella-code
		filteringResultSet.setId(IdGenerator.createId());
		//end-capella-code
		return filteringResultSet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UnionFilteringResultSet createUnionFilteringResultSet() {
		UnionFilteringResultSetImpl unionFilteringResultSet = new UnionFilteringResultSetImpl();
		//begin-capella-code
		unionFilteringResultSet.setId(IdGenerator.createId());
		//end-capella-code
		return unionFilteringResultSet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExclusionFilteringResultSet createExclusionFilteringResultSet() {
		ExclusionFilteringResultSetImpl exclusionFilteringResultSet = new ExclusionFilteringResultSetImpl();
		//begin-capella-code
		exclusionFilteringResultSet.setId(IdGenerator.createId());
		//end-capella-code
		return exclusionFilteringResultSet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IntersectionFilteringResultSet createIntersectionFilteringResultSet() {
		IntersectionFilteringResultSetImpl intersectionFilteringResultSet = new IntersectionFilteringResultSetImpl();
		//begin-capella-code
		intersectionFilteringResultSet.setId(IdGenerator.createId());
		//end-capella-code
		return intersectionFilteringResultSet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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

	/**
	 * Creates class and sets its name (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p
	 *          : default name of created element
	 * @generated
	 */
	public FilteringModel createFilteringModel(String name_p) {
		FilteringModel filteringModel = createFilteringModel();
		filteringModel.setName(name_p);
		return filteringModel;
	}

	/**
	 * Creates class and sets its name (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p
	 *          : default name of created element
	 * @generated
	 */
	public FilteringCriterion createFilteringCriterion(String name_p) {
		FilteringCriterion filteringCriterion = createFilteringCriterion();
		filteringCriterion.setName(name_p);
		return filteringCriterion;
	}

	/**
	 * Creates class and sets its name (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p
	 *          : default name of created element
	 * @generated
	 */
	public FilteringResults createFilteringResults(String name_p) {
		FilteringResults filteringResults = createFilteringResults();
		filteringResults.setName(name_p);
		return filteringResults;
	}

	/**
	 * Creates class and sets its name (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p
	 *          : default name of created element
	 * @generated
	 */
	public FilteringResult createFilteringResult(String name_p) {
		FilteringResult filteringResult = createFilteringResult();
		filteringResult.setName(name_p);
		return filteringResult;
	}

	/**
	 * Creates class and sets its name (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p
	 *          : default name of created element
	 * @generated
	 */
	public AssociatedFilteringCriterionSet createAssociatedFilteringCriterionSet(String name_p) {
		AssociatedFilteringCriterionSet associatedFilteringCriterionSet = createAssociatedFilteringCriterionSet();
		associatedFilteringCriterionSet.setName(name_p);
		return associatedFilteringCriterionSet;
	}

	/**
	 * Creates class and sets its name (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p
	 *          : default name of created element
	 * @generated
	 */
	public CreationDefaultFilteringCriterionSet createCreationDefaultFilteringCriterionSet(String name_p) {
		CreationDefaultFilteringCriterionSet creationDefaultFilteringCriterionSet = createCreationDefaultFilteringCriterionSet();
		creationDefaultFilteringCriterionSet.setName(name_p);
		return creationDefaultFilteringCriterionSet;
	}

	/**
	 * Creates class and sets its name (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p
	 *          : default name of created element
	 * @generated
	 */
	public FilteringResultPkg createFilteringResultPkg(String name_p) {
		FilteringResultPkg filteringResultPkg = createFilteringResultPkg();
		filteringResultPkg.setName(name_p);
		return filteringResultPkg;
	}

	/**
	 * Creates class and sets its name (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p
	 *          : default name of created element
	 * @generated
	 */
	public FilteringCriterionPkg createFilteringCriterionPkg(String name_p) {
		FilteringCriterionPkg filteringCriterionPkg = createFilteringCriterionPkg();
		filteringCriterionPkg.setName(name_p);
		return filteringCriterionPkg;
	}

	/**
	 * Creates class and sets its name (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p
	 *          : default name of created element
	 * @generated
	 */
	public ComposedFilteringResult createComposedFilteringResult(String name_p) {
		ComposedFilteringResult composedFilteringResult = createComposedFilteringResult();
		composedFilteringResult.setName(name_p);
		return composedFilteringResult;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public FilteringResultSet createFilteringResultSet(String name_p) {
		FilteringResultSet filteringResultSet = createFilteringResultSet();
		filteringResultSet.setName(name_p);
		return filteringResultSet;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public UnionFilteringResultSet createUnionFilteringResultSet(String name_p) {
		UnionFilteringResultSet unionFilteringResultSet = createUnionFilteringResultSet();
		unionFilteringResultSet.setName(name_p);
		return unionFilteringResultSet;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ExclusionFilteringResultSet createExclusionFilteringResultSet(String name_p) {
		ExclusionFilteringResultSet exclusionFilteringResultSet = createExclusionFilteringResultSet();
		exclusionFilteringResultSet.setName(name_p);
		return exclusionFilteringResultSet;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public IntersectionFilteringResultSet createIntersectionFilteringResultSet(String name_p) {
		IntersectionFilteringResultSet intersectionFilteringResultSet = createIntersectionFilteringResultSet();
		intersectionFilteringResultSet.setName(name_p);
		return intersectionFilteringResultSet;
	}

	// end-capella-code
} // FilteringFactoryImpl