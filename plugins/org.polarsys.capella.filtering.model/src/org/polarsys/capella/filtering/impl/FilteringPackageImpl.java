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
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;

import org.polarsys.capella.core.data.capellacore.CapellacorePackage;

import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;

import org.polarsys.capella.core.data.cs.CsPackage;

import org.polarsys.capella.core.data.ctx.CtxPackage;

import org.polarsys.capella.core.data.epbs.EpbsPackage;

import org.polarsys.capella.core.data.fa.FaPackage;

import org.polarsys.capella.core.data.information.InformationPackage;

import org.polarsys.capella.core.data.interaction.InteractionPackage;

import org.polarsys.capella.core.data.la.LaPackage;

import org.polarsys.capella.core.data.oa.OaPackage;

import org.polarsys.capella.core.data.pa.PaPackage;

import org.polarsys.capella.core.data.requirement.RequirementPackage;

import org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage;

import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;
import org.polarsys.capella.filtering.CreationDefaultFilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringCriterionPkg;
import org.polarsys.capella.filtering.FilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringFactory;
import org.polarsys.capella.filtering.FilteringModel;
import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.capella.filtering.FilteringResult;
import org.polarsys.capella.filtering.FilteringResultPkg;
import org.polarsys.capella.filtering.FilteringResults;

import org.polarsys.kitalpha.emde.model.EmdePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class FilteringPackageImpl extends EPackageImpl implements FilteringPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass filteringModelEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass filteringCriterionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass filteringCriterionSetEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass filteringResultsEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass filteringResultEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass associatedFilteringCriterionSetEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass creationDefaultFilteringCriterionSetEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass filteringResultPkgEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass filteringCriterionPkgEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
	 * package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory
	 * method {@link #init init()}, which also performs initialization of the
	 * package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.polarsys.capella.filtering.FilteringPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private FilteringPackageImpl() {
		super(eNS_URI, FilteringFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model,
	 * and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link FilteringPackage#eINSTANCE} when
	 * that field is accessed. Clients should not invoke it directly. Instead,
	 * they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static FilteringPackage init() {
		if (isInited)
			return (FilteringPackage) EPackage.Registry.INSTANCE.getEPackage(FilteringPackage.eNS_URI);

		// Obtain or create and register package
		FilteringPackageImpl theFilteringPackage = (FilteringPackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof FilteringPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
						: new FilteringPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CapellacorePackage.eINSTANCE.eClass();
		OaPackage.eINSTANCE.eClass();
		CtxPackage.eINSTANCE.eClass();
		LaPackage.eINSTANCE.eClass();
		PaPackage.eINSTANCE.eClass();
		EpbsPackage.eINSTANCE.eClass();
		RequirementPackage.eINSTANCE.eClass();
		CapellacommonPackage.eINSTANCE.eClass();
		InformationPackage.eINSTANCE.eClass();
		CsPackage.eINSTANCE.eClass();
		FaPackage.eINSTANCE.eClass();
		InteractionPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theFilteringPackage.createPackageContents();

		// Initialize created meta-data
		theFilteringPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theFilteringPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(FilteringPackage.eNS_URI, theFilteringPackage);
		return theFilteringPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFilteringModel() {
		return filteringModelEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFilteringModel_OwnedFilteringCriteria() {
		return (EReference) filteringModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFilteringModel_OwnedFilteringCriterionPkgs() {
		return (EReference) filteringModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFilteringCriterion() {
		return filteringCriterionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFilteringCriterionSet() {
		return filteringCriterionSetEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFilteringCriterionSet_FilteringCriteria() {
		return (EReference) filteringCriterionSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFilteringResults() {
		return filteringResultsEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFilteringResults_FilteringResults() {
		return (EReference) filteringResultsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFilteringResults_OwnedFilteringResultPkgs() {
		return (EReference) filteringResultsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFilteringResult() {
		return filteringResultEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssociatedFilteringCriterionSet() {
		return associatedFilteringCriterionSetEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCreationDefaultFilteringCriterionSet() {
		return creationDefaultFilteringCriterionSetEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFilteringResultPkg() {
		return filteringResultPkgEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFilteringResultPkg_OwnedFilteringResults() {
		return (EReference) filteringResultPkgEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFilteringResultPkg_OwnedFilteringResultPkgs() {
		return (EReference) filteringResultPkgEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFilteringCriterionPkg() {
		return filteringCriterionPkgEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFilteringCriterionPkg_OwnedFilteringCriteria() {
		return (EReference) filteringCriterionPkgEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFilteringCriterionPkg_OwnedFilteringCriterionPkgs() {
		return (EReference) filteringCriterionPkgEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public FilteringFactory getFilteringFactory() {
		return (FilteringFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		filteringModelEClass = createEClass(FILTERING_MODEL);
		createEReference(filteringModelEClass, FILTERING_MODEL__OWNED_FILTERING_CRITERIA);
		createEReference(filteringModelEClass, FILTERING_MODEL__OWNED_FILTERING_CRITERION_PKGS);

		filteringCriterionEClass = createEClass(FILTERING_CRITERION);

		filteringCriterionSetEClass = createEClass(FILTERING_CRITERION_SET);
		createEReference(filteringCriterionSetEClass, FILTERING_CRITERION_SET__FILTERING_CRITERIA);

		filteringResultsEClass = createEClass(FILTERING_RESULTS);
		createEReference(filteringResultsEClass, FILTERING_RESULTS__FILTERING_RESULTS);
		createEReference(filteringResultsEClass, FILTERING_RESULTS__OWNED_FILTERING_RESULT_PKGS);

		filteringResultEClass = createEClass(FILTERING_RESULT);

		associatedFilteringCriterionSetEClass = createEClass(ASSOCIATED_FILTERING_CRITERION_SET);

		creationDefaultFilteringCriterionSetEClass = createEClass(CREATION_DEFAULT_FILTERING_CRITERION_SET);

		filteringResultPkgEClass = createEClass(FILTERING_RESULT_PKG);
		createEReference(filteringResultPkgEClass, FILTERING_RESULT_PKG__OWNED_FILTERING_RESULTS);
		createEReference(filteringResultPkgEClass, FILTERING_RESULT_PKG__OWNED_FILTERING_RESULT_PKGS);

		filteringCriterionPkgEClass = createEClass(FILTERING_CRITERION_PKG);
		createEReference(filteringCriterionPkgEClass, FILTERING_CRITERION_PKG__OWNED_FILTERING_CRITERIA);
		createEReference(filteringCriterionPkgEClass, FILTERING_CRITERION_PKG__OWNED_FILTERING_CRITERION_PKGS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This
	 * method is guarded to have no affect on any invocation but its first. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		CapellacorePackage theCapellacorePackage = (CapellacorePackage) EPackage.Registry.INSTANCE
				.getEPackage(CapellacorePackage.eNS_URI);
		EmdePackage theEmdePackage = (EmdePackage) EPackage.Registry.INSTANCE.getEPackage(EmdePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		filteringModelEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
		filteringModelEClass.getESuperTypes().add(theEmdePackage.getElementExtension());
		filteringCriterionEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
		filteringCriterionSetEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
		filteringResultsEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
		filteringResultsEClass.getESuperTypes().add(theEmdePackage.getElementExtension());
		filteringResultEClass.getESuperTypes().add(this.getFilteringCriterionSet());
		associatedFilteringCriterionSetEClass.getESuperTypes().add(this.getFilteringCriterionSet());
		associatedFilteringCriterionSetEClass.getESuperTypes().add(theEmdePackage.getElementExtension());
		creationDefaultFilteringCriterionSetEClass.getESuperTypes().add(this.getFilteringCriterionSet());
		creationDefaultFilteringCriterionSetEClass.getESuperTypes().add(theEmdePackage.getElementExtension());
		filteringResultPkgEClass.getESuperTypes().add(theCapellacorePackage.getNamespace());
		filteringCriterionPkgEClass.getESuperTypes().add(theCapellacorePackage.getNamespace());

		// Initialize classes and features; add operations and parameters
		initEClass(filteringModelEClass, FilteringModel.class, "FilteringModel", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFilteringModel_OwnedFilteringCriteria(), this.getFilteringCriterion(), null,
				"ownedFilteringCriteria", null, 0, -1, FilteringModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFilteringModel_OwnedFilteringCriterionPkgs(), this.getFilteringCriterionPkg(), null,
				"ownedFilteringCriterionPkgs", null, 0, -1, FilteringModel.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(filteringCriterionEClass, FilteringCriterion.class, "FilteringCriterion", !IS_ABSTRACT, //$NON-NLS-1$
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(filteringCriterionSetEClass, FilteringCriterionSet.class, "FilteringCriterionSet", IS_ABSTRACT, //$NON-NLS-1$
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFilteringCriterionSet_FilteringCriteria(), this.getFilteringCriterion(), null,
				"filteringCriteria", null, 0, -1, FilteringCriterionSet.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
				IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(filteringResultsEClass, FilteringResults.class, "FilteringResults", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFilteringResults_FilteringResults(), this.getFilteringResult(), null, "filteringResults", //$NON-NLS-1$
				null, 0, -1, FilteringResults.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFilteringResults_OwnedFilteringResultPkgs(), this.getFilteringResultPkg(), null,
				"ownedFilteringResultPkgs", null, 0, -1, FilteringResults.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(filteringResultEClass, FilteringResult.class, "FilteringResult", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(associatedFilteringCriterionSetEClass, AssociatedFilteringCriterionSet.class,
				"AssociatedFilteringCriterionSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(creationDefaultFilteringCriterionSetEClass, CreationDefaultFilteringCriterionSet.class,
				"CreationDefaultFilteringCriterionSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(filteringResultPkgEClass, FilteringResultPkg.class, "FilteringResultPkg", !IS_ABSTRACT, //$NON-NLS-1$
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFilteringResultPkg_OwnedFilteringResults(), this.getFilteringResult(), null,
				"ownedFilteringResults", null, 0, -1, FilteringResultPkg.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFilteringResultPkg_OwnedFilteringResultPkgs(), this.getFilteringResultPkg(), null,
				"ownedFilteringResultPkgs", null, 0, -1, FilteringResultPkg.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(filteringCriterionPkgEClass, FilteringCriterionPkg.class, "FilteringCriterionPkg", !IS_ABSTRACT, //$NON-NLS-1$
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFilteringCriterionPkg_OwnedFilteringCriteria(), this.getFilteringCriterion(), null,
				"ownedFilteringCriteria", null, 0, -1, FilteringCriterionPkg.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFilteringCriterionPkg_OwnedFilteringCriterionPkgs(), this.getFilteringCriterionPkg(), null,
				"ownedFilteringCriterionPkgs", null, 0, -1, FilteringCriterionPkg.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.polarsys.org/kitalpha/emde/1.0.0/extension
		createExtensionAnnotations();
		// http://www.polarsys.org/kitalpha/dsl/2007/dslfactory
		createDslfactoryAnnotations();
		// http://www.polarsys.org/kitalpha/ecore/documentation
		createDocumentationAnnotations();
		// http://www.polarsys.org/kitalpha/emde/1.0.0/constraint
		createConstraintAnnotations();
		// http://www.polarsys.org/capella/semantic
		createSemanticAnnotations();
	}

	/**
	 * Initializes the annotations for
	 * <b>http://www.polarsys.org/kitalpha/emde/1.0.0/extension</b>. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void createExtensionAnnotations() {
		String source = "http://www.polarsys.org/kitalpha/emde/1.0.0/extension"; //$NON-NLS-1$	
		addAnnotation(this, source,
				new String[] { "extensibleProviderFactory", "true", //$NON-NLS-1$ //$NON-NLS-2$
						"childCreationExtenders", "true", //$NON-NLS-1$ //$NON-NLS-2$
						"useUUIDs", "true", //$NON-NLS-1$ //$NON-NLS-2$
						"useIDAttributes", "false" //$NON-NLS-1$ //$NON-NLS-2$
				});
	}

	/**
	 * Initializes the annotations for
	 * <b>http://www.polarsys.org/kitalpha/dsl/2007/dslfactory</b>. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void createDslfactoryAnnotations() {
		String source = "http://www.polarsys.org/kitalpha/dsl/2007/dslfactory"; //$NON-NLS-1$	
		addAnnotation(this, source,
				new String[] { "extensibleProviderFactory", "true", //$NON-NLS-1$ //$NON-NLS-2$
						"childCreationExtenders", "true", //$NON-NLS-1$ //$NON-NLS-2$
						"useUUIDs", "true", //$NON-NLS-1$ //$NON-NLS-2$
						"useIDAttributes", "false" //$NON-NLS-1$ //$NON-NLS-2$
				});
	}

	/**
	 * Initializes the annotations for
	 * <b>http://www.polarsys.org/kitalpha/ecore/documentation</b>. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void createDocumentationAnnotations() {
		String source = "http://www.polarsys.org/kitalpha/ecore/documentation"; //$NON-NLS-1$	
		addAnnotation(this, source,
				new String[] { "description", null, //$NON-NLS-1$
						"usage guideline", null, //$NON-NLS-1$
						"used in levels", null, //$NON-NLS-1$
						"usage examples", null, //$NON-NLS-1$
						"constraints", null //$NON-NLS-1$
				});
		addAnnotation(filteringModelEClass, source,
				new String[] { "description", null, //$NON-NLS-1$
						"usage guideline", null, //$NON-NLS-1$
						"used in levels", null, //$NON-NLS-1$
						"usage examples", null, //$NON-NLS-1$
						"constraints", null //$NON-NLS-1$
				});
		addAnnotation(getFilteringModel_OwnedFilteringCriteria(), source,
				new String[] { "description", null, //$NON-NLS-1$
						"usage guideline", null, //$NON-NLS-1$
						"used in levels", null, //$NON-NLS-1$
						"usage examples", null, //$NON-NLS-1$
						"constraints", null //$NON-NLS-1$
				});
		addAnnotation(filteringCriterionEClass, source,
				new String[] { "description", null, //$NON-NLS-1$
						"usage guideline", null, //$NON-NLS-1$
						"used in levels", null, //$NON-NLS-1$
						"usage examples", null, //$NON-NLS-1$
						"constraints", null //$NON-NLS-1$
				});
		addAnnotation(filteringCriterionSetEClass, source,
				new String[] { "description", null, //$NON-NLS-1$
						"usage guideline", null, //$NON-NLS-1$
						"used in levels", null, //$NON-NLS-1$
						"usage examples", null, //$NON-NLS-1$
						"constraints", null //$NON-NLS-1$
				});
		addAnnotation(getFilteringCriterionSet_FilteringCriteria(), source,
				new String[] { "description", null, //$NON-NLS-1$
						"usage guideline", null, //$NON-NLS-1$
						"used in levels", null, //$NON-NLS-1$
						"usage examples", null, //$NON-NLS-1$
						"constraints", null //$NON-NLS-1$
				});
		addAnnotation(filteringResultsEClass, source,
				new String[] { "description", null, //$NON-NLS-1$
						"usage guideline", null, //$NON-NLS-1$
						"used in levels", null, //$NON-NLS-1$
						"usage examples", null, //$NON-NLS-1$
						"constraints", null //$NON-NLS-1$
				});
		addAnnotation(getFilteringResults_FilteringResults(), source,
				new String[] { "description", null, //$NON-NLS-1$
						"usage guideline", null, //$NON-NLS-1$
						"used in levels", null, //$NON-NLS-1$
						"usage examples", null, //$NON-NLS-1$
						"constraints", null //$NON-NLS-1$
				});
		addAnnotation(filteringResultEClass, source,
				new String[] { "description", null, //$NON-NLS-1$
						"usage guideline", null, //$NON-NLS-1$
						"used in levels", null, //$NON-NLS-1$
						"usage examples", null, //$NON-NLS-1$
						"constraints", null //$NON-NLS-1$
				});
		addAnnotation(associatedFilteringCriterionSetEClass, source,
				new String[] { "description", null, //$NON-NLS-1$
						"usage guideline", null, //$NON-NLS-1$
						"used in levels", null, //$NON-NLS-1$
						"usage examples", null, //$NON-NLS-1$
						"constraints", null //$NON-NLS-1$
				});
		addAnnotation(creationDefaultFilteringCriterionSetEClass, source,
				new String[] { "description", null, //$NON-NLS-1$
						"usage guideline", null, //$NON-NLS-1$
						"used in levels", null, //$NON-NLS-1$
						"usage examples", null, //$NON-NLS-1$
						"constraints", null //$NON-NLS-1$
				});
	}

	/**
	 * Initializes the annotations for
	 * <b>http://www.polarsys.org/kitalpha/emde/1.0.0/constraint</b>. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void createConstraintAnnotations() {
		String source = "http://www.polarsys.org/kitalpha/emde/1.0.0/constraint"; //$NON-NLS-1$	
		addAnnotation(filteringModelEClass, source, new String[] { "ExtendedElement", //$NON-NLS-1$
				"http://www.polarsys.org/capella/core/modeller/1.2.0#//SystemEngineering" //$NON-NLS-1$
		});
		addAnnotation(filteringResultsEClass, source, new String[] { "ExtendedElement", //$NON-NLS-1$
				"http://www.polarsys.org/capella/core/modeller/1.2.0#//SystemEngineering" //$NON-NLS-1$
		});
		addAnnotation(associatedFilteringCriterionSetEClass, source,
				new String[] { "ExtendedElement", "http://www.polarsys.org/capella/core/core/1.2.0#//CapellaElement" //$NON-NLS-1$ //$NON-NLS-2$
				});
		addAnnotation(creationDefaultFilteringCriterionSetEClass, source, new String[] { "ExtendedElement", //$NON-NLS-1$
				"http://www.polarsys.org/capella/core/modeller/1.2.0#//SystemEngineering " //$NON-NLS-1$
		});
	}

	/**
	 * Initializes the annotations for <b>http://www.polarsys.org/capella/semantic</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createSemanticAnnotations() {
		String source = "http://www.polarsys.org/capella/semantic"; //$NON-NLS-1$	
		addAnnotation(getFilteringModel_OwnedFilteringCriteria(), source, new String[] {});
		addAnnotation(getFilteringCriterionSet_FilteringCriteria(), source, new String[] {});
		addAnnotation(getFilteringResults_FilteringResults(), source, new String[] {});
	}

} // FilteringPackageImpl
