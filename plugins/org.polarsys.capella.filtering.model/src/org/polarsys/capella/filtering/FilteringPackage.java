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

package org.polarsys.capella.filtering;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.data.capellacore.CapellacorePackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.filtering.FilteringFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension extensibleProviderFactory='true' childCreationExtenders='true' useUUIDs='true' useIDAttributes='false'"
 *        annotation="http://www.polarsys.org/kitalpha/dsl/2007/dslfactory extensibleProviderFactory='true' childCreationExtenders='true' useUUIDs='true' useIDAttributes='false'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='null' usage\040guideline='null' used\040in\040levels='null' usage\040examples='null' constraints='null'"
 * @generated
 */
public interface FilteringPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "filtering"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.polarsys.org/capella/filtering/1.2.0"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "filtering"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	FilteringPackage eINSTANCE = org.polarsys.capella.filtering.impl.FilteringPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.polarsys.capella.filtering.impl.FilteringModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.polarsys.capella.filtering.impl.FilteringModelImpl
	 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getFilteringModel()
	 * @generated
	 */
	int FILTERING_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__APPLIED_REQUIREMENTS = CapellacorePackage.NAMED_ELEMENT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Filtering Criteria</b></em>' containment reference list.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__OWNED_FILTERING_CRITERIA = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned Filtering Criterion Pkgs</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL__OWNED_FILTERING_CRITERION_PKGS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_MODEL_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.filtering.impl.FilteringCriterionImpl <em>Criterion</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.polarsys.capella.filtering.impl.FilteringCriterionImpl
	 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getFilteringCriterion()
	 * @generated
	 */
	int FILTERING_CRITERION = 1;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION__APPLIED_REQUIREMENTS = CapellacorePackage.NAMED_ELEMENT__APPLIED_REQUIREMENTS;

	/**
	 * The number of structural features of the '<em>Criterion</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.filtering.impl.FilteringCriterionSetImpl <em>Criterion Set</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.polarsys.capella.filtering.impl.FilteringCriterionSetImpl
	 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getFilteringCriterionSet()
	 * @generated
	 */
	int FILTERING_CRITERION_SET = 2;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__APPLIED_REQUIREMENTS = CapellacorePackage.NAMED_ELEMENT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Filtering Criteria</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET__FILTERING_CRITERIA = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Criterion Set</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_SET_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.filtering.impl.FilteringResultsImpl <em>Results</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.polarsys.capella.filtering.impl.FilteringResultsImpl
	 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getFilteringResults()
	 * @generated
	 */
	int FILTERING_RESULTS = 3;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__APPLIED_REQUIREMENTS = CapellacorePackage.NAMED_ELEMENT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Filtering Results</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__FILTERING_RESULTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned Filtering Result Pkgs</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS__OWNED_FILTERING_RESULT_PKGS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Results</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULTS_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.filtering.impl.FilteringResultImpl <em>Result</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.polarsys.capella.filtering.impl.FilteringResultImpl
	 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getFilteringResult()
	 * @generated
	 */
	int FILTERING_RESULT = 4;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__OWNED_EXTENSIONS = FILTERING_CRITERION_SET__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__ID = FILTERING_CRITERION_SET__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__SID = FILTERING_CRITERION_SET__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__CONSTRAINTS = FILTERING_CRITERION_SET__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__OWNED_CONSTRAINTS = FILTERING_CRITERION_SET__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__NAME = FILTERING_CRITERION_SET__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__INCOMING_TRACES = FILTERING_CRITERION_SET__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__OUTGOING_TRACES = FILTERING_CRITERION_SET__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__VISIBLE_IN_DOC = FILTERING_CRITERION_SET__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__VISIBLE_IN_LM = FILTERING_CRITERION_SET__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__SUMMARY = FILTERING_CRITERION_SET__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__DESCRIPTION = FILTERING_CRITERION_SET__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__REVIEW = FILTERING_CRITERION_SET__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__OWNED_PROPERTY_VALUES = FILTERING_CRITERION_SET__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__OWNED_ENUMERATION_PROPERTY_TYPES = FILTERING_CRITERION_SET__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__APPLIED_PROPERTY_VALUES = FILTERING_CRITERION_SET__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__OWNED_PROPERTY_VALUE_GROUPS = FILTERING_CRITERION_SET__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__APPLIED_PROPERTY_VALUE_GROUPS = FILTERING_CRITERION_SET__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__STATUS = FILTERING_CRITERION_SET__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__FEATURES = FILTERING_CRITERION_SET__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__APPLIED_REQUIREMENTS = FILTERING_CRITERION_SET__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Filtering Criteria</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT__FILTERING_CRITERIA = FILTERING_CRITERION_SET__FILTERING_CRITERIA;

	/**
	 * The number of structural features of the '<em>Result</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_FEATURE_COUNT = FILTERING_CRITERION_SET_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.filtering.impl.AssociatedFilteringCriterionSetImpl <em>Associated Filtering Criterion Set</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.polarsys.capella.filtering.impl.AssociatedFilteringCriterionSetImpl
	 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getAssociatedFilteringCriterionSet()
	 * @generated
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET = 5;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__OWNED_EXTENSIONS = FILTERING_CRITERION_SET__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__ID = FILTERING_CRITERION_SET__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__SID = FILTERING_CRITERION_SET__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__CONSTRAINTS = FILTERING_CRITERION_SET__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__OWNED_CONSTRAINTS = FILTERING_CRITERION_SET__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__NAME = FILTERING_CRITERION_SET__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__INCOMING_TRACES = FILTERING_CRITERION_SET__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__OUTGOING_TRACES = FILTERING_CRITERION_SET__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__VISIBLE_IN_DOC = FILTERING_CRITERION_SET__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__VISIBLE_IN_LM = FILTERING_CRITERION_SET__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__SUMMARY = FILTERING_CRITERION_SET__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__DESCRIPTION = FILTERING_CRITERION_SET__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__REVIEW = FILTERING_CRITERION_SET__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__OWNED_PROPERTY_VALUES = FILTERING_CRITERION_SET__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__OWNED_ENUMERATION_PROPERTY_TYPES = FILTERING_CRITERION_SET__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__APPLIED_PROPERTY_VALUES = FILTERING_CRITERION_SET__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__OWNED_PROPERTY_VALUE_GROUPS = FILTERING_CRITERION_SET__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__APPLIED_PROPERTY_VALUE_GROUPS = FILTERING_CRITERION_SET__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__STATUS = FILTERING_CRITERION_SET__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__FEATURES = FILTERING_CRITERION_SET__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__APPLIED_REQUIREMENTS = FILTERING_CRITERION_SET__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Filtering Criteria</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET__FILTERING_CRITERIA = FILTERING_CRITERION_SET__FILTERING_CRITERIA;

	/**
	 * The number of structural features of the '<em>Associated Filtering Criterion Set</em>' class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATED_FILTERING_CRITERION_SET_FEATURE_COUNT = FILTERING_CRITERION_SET_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.filtering.impl.CreationDefaultFilteringCriterionSetImpl <em>Creation Default Filtering Criterion Set</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.polarsys.capella.filtering.impl.CreationDefaultFilteringCriterionSetImpl
	 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getCreationDefaultFilteringCriterionSet()
	 * @generated
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET = 6;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__OWNED_EXTENSIONS = FILTERING_CRITERION_SET__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__ID = FILTERING_CRITERION_SET__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__SID = FILTERING_CRITERION_SET__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__CONSTRAINTS = FILTERING_CRITERION_SET__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__OWNED_CONSTRAINTS = FILTERING_CRITERION_SET__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__NAME = FILTERING_CRITERION_SET__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__INCOMING_TRACES = FILTERING_CRITERION_SET__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__OUTGOING_TRACES = FILTERING_CRITERION_SET__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__VISIBLE_IN_DOC = FILTERING_CRITERION_SET__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__VISIBLE_IN_LM = FILTERING_CRITERION_SET__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__SUMMARY = FILTERING_CRITERION_SET__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__DESCRIPTION = FILTERING_CRITERION_SET__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__REVIEW = FILTERING_CRITERION_SET__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__OWNED_PROPERTY_VALUES = FILTERING_CRITERION_SET__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__OWNED_ENUMERATION_PROPERTY_TYPES = FILTERING_CRITERION_SET__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__APPLIED_PROPERTY_VALUES = FILTERING_CRITERION_SET__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__OWNED_PROPERTY_VALUE_GROUPS = FILTERING_CRITERION_SET__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__APPLIED_PROPERTY_VALUE_GROUPS = FILTERING_CRITERION_SET__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__STATUS = FILTERING_CRITERION_SET__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__FEATURES = FILTERING_CRITERION_SET__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__APPLIED_REQUIREMENTS = FILTERING_CRITERION_SET__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Filtering Criteria</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET__FILTERING_CRITERIA = FILTERING_CRITERION_SET__FILTERING_CRITERIA;

	/**
	 * The number of structural features of the '<em>Creation Default Filtering Criterion Set</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CREATION_DEFAULT_FILTERING_CRITERION_SET_FEATURE_COUNT = FILTERING_CRITERION_SET_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.filtering.impl.FilteringResultPkgImpl <em>Result Pkg</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.polarsys.capella.filtering.impl.FilteringResultPkgImpl
	 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getFilteringResultPkg()
	 * @generated
	 */
	int FILTERING_RESULT_PKG = 7;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__OWNED_EXTENSIONS = CapellacorePackage.NAMESPACE__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__ID = CapellacorePackage.NAMESPACE__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__SID = CapellacorePackage.NAMESPACE__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__CONSTRAINTS = CapellacorePackage.NAMESPACE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__OWNED_CONSTRAINTS = CapellacorePackage.NAMESPACE__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__NAME = CapellacorePackage.NAMESPACE__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__INCOMING_TRACES = CapellacorePackage.NAMESPACE__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__OUTGOING_TRACES = CapellacorePackage.NAMESPACE__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__VISIBLE_IN_DOC = CapellacorePackage.NAMESPACE__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__VISIBLE_IN_LM = CapellacorePackage.NAMESPACE__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__SUMMARY = CapellacorePackage.NAMESPACE__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__DESCRIPTION = CapellacorePackage.NAMESPACE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__REVIEW = CapellacorePackage.NAMESPACE__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMESPACE__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMESPACE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMESPACE__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMESPACE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMESPACE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__STATUS = CapellacorePackage.NAMESPACE__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__FEATURES = CapellacorePackage.NAMESPACE__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__APPLIED_REQUIREMENTS = CapellacorePackage.NAMESPACE__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__OWNED_TRACES = CapellacorePackage.NAMESPACE__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__CONTAINED_GENERIC_TRACES = CapellacorePackage.NAMESPACE__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__CONTAINED_REQUIREMENTS_TRACES = CapellacorePackage.NAMESPACE__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__NAMING_RULES = CapellacorePackage.NAMESPACE__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Owned Filtering Results</b></em>' containment reference list.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__OWNED_FILTERING_RESULTS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned Filtering Result Pkgs</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG__OWNED_FILTERING_RESULT_PKGS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Result Pkg</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_PKG_FEATURE_COUNT = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.filtering.impl.FilteringCriterionPkgImpl <em>Criterion Pkg</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.polarsys.capella.filtering.impl.FilteringCriterionPkgImpl
	 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getFilteringCriterionPkg()
	 * @generated
	 */
	int FILTERING_CRITERION_PKG = 8;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__OWNED_EXTENSIONS = CapellacorePackage.NAMESPACE__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__ID = CapellacorePackage.NAMESPACE__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__SID = CapellacorePackage.NAMESPACE__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__CONSTRAINTS = CapellacorePackage.NAMESPACE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__OWNED_CONSTRAINTS = CapellacorePackage.NAMESPACE__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__NAME = CapellacorePackage.NAMESPACE__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__INCOMING_TRACES = CapellacorePackage.NAMESPACE__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__OUTGOING_TRACES = CapellacorePackage.NAMESPACE__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__VISIBLE_IN_DOC = CapellacorePackage.NAMESPACE__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__VISIBLE_IN_LM = CapellacorePackage.NAMESPACE__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__SUMMARY = CapellacorePackage.NAMESPACE__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__DESCRIPTION = CapellacorePackage.NAMESPACE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__REVIEW = CapellacorePackage.NAMESPACE__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMESPACE__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMESPACE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMESPACE__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMESPACE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMESPACE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__STATUS = CapellacorePackage.NAMESPACE__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__FEATURES = CapellacorePackage.NAMESPACE__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__APPLIED_REQUIREMENTS = CapellacorePackage.NAMESPACE__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__OWNED_TRACES = CapellacorePackage.NAMESPACE__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__CONTAINED_GENERIC_TRACES = CapellacorePackage.NAMESPACE__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__CONTAINED_REQUIREMENTS_TRACES = CapellacorePackage.NAMESPACE__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__NAMING_RULES = CapellacorePackage.NAMESPACE__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Owned Filtering Criteria</b></em>' containment reference list.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__OWNED_FILTERING_CRITERIA = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned Filtering Criterion Pkgs</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG__OWNED_FILTERING_CRITERION_PKGS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Criterion Pkg</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FILTERING_CRITERION_PKG_FEATURE_COUNT = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.filtering.impl.AbstractFilteringResultImpl <em>Abstract Filtering Result</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.polarsys.capella.filtering.impl.AbstractFilteringResultImpl
	 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getAbstractFilteringResult()
	 * @generated
	 */
	int ABSTRACT_FILTERING_RESULT = 11;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT__APPLIED_REQUIREMENTS = CapellacorePackage.NAMED_ELEMENT__APPLIED_REQUIREMENTS;

	/**
	 * The number of structural features of the '<em>Abstract Filtering Result</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FILTERING_RESULT_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.filtering.impl.ComposedFilteringResultImpl <em>Composed Filtering Result</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.polarsys.capella.filtering.impl.ComposedFilteringResultImpl
	 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getComposedFilteringResult()
	 * @generated
	 */
	int COMPOSED_FILTERING_RESULT = 9;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__OWNED_EXTENSIONS = ABSTRACT_FILTERING_RESULT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__ID = ABSTRACT_FILTERING_RESULT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__SID = ABSTRACT_FILTERING_RESULT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__CONSTRAINTS = ABSTRACT_FILTERING_RESULT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__OWNED_CONSTRAINTS = ABSTRACT_FILTERING_RESULT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__NAME = ABSTRACT_FILTERING_RESULT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__INCOMING_TRACES = ABSTRACT_FILTERING_RESULT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__OUTGOING_TRACES = ABSTRACT_FILTERING_RESULT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__VISIBLE_IN_DOC = ABSTRACT_FILTERING_RESULT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__VISIBLE_IN_LM = ABSTRACT_FILTERING_RESULT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__SUMMARY = ABSTRACT_FILTERING_RESULT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__DESCRIPTION = ABSTRACT_FILTERING_RESULT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__REVIEW = ABSTRACT_FILTERING_RESULT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__OWNED_PROPERTY_VALUES = ABSTRACT_FILTERING_RESULT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_FILTERING_RESULT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__APPLIED_PROPERTY_VALUES = ABSTRACT_FILTERING_RESULT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_FILTERING_RESULT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_FILTERING_RESULT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__STATUS = ABSTRACT_FILTERING_RESULT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__FEATURES = ABSTRACT_FILTERING_RESULT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__APPLIED_REQUIREMENTS = ABSTRACT_FILTERING_RESULT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Union Filtering Result Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__UNION_FILTERING_RESULT_SET = ABSTRACT_FILTERING_RESULT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Intersection Filtering Result Set</b></em>' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__INTERSECTION_FILTERING_RESULT_SET = ABSTRACT_FILTERING_RESULT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Exclusion Filtering Result Set</b></em>' containment reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT__EXCLUSION_FILTERING_RESULT_SET = ABSTRACT_FILTERING_RESULT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Composed Filtering Result</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_FILTERING_RESULT_FEATURE_COUNT = ABSTRACT_FILTERING_RESULT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.filtering.impl.FilteringResultSetImpl <em>Result Set</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.polarsys.capella.filtering.impl.FilteringResultSetImpl
	 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getFilteringResultSet()
	 * @generated
	 */
	int FILTERING_RESULT_SET = 10;

	/**
	 * The feature id for the '<em><b>Filtering Results</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_SET__FILTERING_RESULTS = 0;

	/**
	 * The number of structural features of the '<em>Result Set</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_RESULT_SET_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.filtering.impl.UnionFilteringResultSetImpl <em>Union Filtering Result Set</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.polarsys.capella.filtering.impl.UnionFilteringResultSetImpl
	 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getUnionFilteringResultSet()
	 * @generated
	 */
	int UNION_FILTERING_RESULT_SET = 12;

	/**
	 * The feature id for the '<em><b>Filtering Results</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_FILTERING_RESULT_SET__FILTERING_RESULTS = FILTERING_RESULT_SET__FILTERING_RESULTS;

	/**
	 * The number of structural features of the '<em>Union Filtering Result Set</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_FILTERING_RESULT_SET_FEATURE_COUNT = FILTERING_RESULT_SET_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.filtering.impl.ExclusionFilteringResultSetImpl <em>Exclusion Filtering Result Set</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.polarsys.capella.filtering.impl.ExclusionFilteringResultSetImpl
	 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getExclusionFilteringResultSet()
	 * @generated
	 */
	int EXCLUSION_FILTERING_RESULT_SET = 13;

	/**
	 * The feature id for the '<em><b>Filtering Results</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCLUSION_FILTERING_RESULT_SET__FILTERING_RESULTS = FILTERING_RESULT_SET__FILTERING_RESULTS;

	/**
	 * The number of structural features of the '<em>Exclusion Filtering Result Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCLUSION_FILTERING_RESULT_SET_FEATURE_COUNT = FILTERING_RESULT_SET_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.filtering.impl.IntersectionFilteringResultSetImpl <em>Intersection Filtering Result Set</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.polarsys.capella.filtering.impl.IntersectionFilteringResultSetImpl
	 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getIntersectionFilteringResultSet()
	 * @generated
	 */
	int INTERSECTION_FILTERING_RESULT_SET = 14;

	/**
	 * The feature id for the '<em><b>Filtering Results</b></em>' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERSECTION_FILTERING_RESULT_SET__FILTERING_RESULTS = FILTERING_RESULT_SET__FILTERING_RESULTS;

	/**
	 * The number of structural features of the '<em>Intersection Filtering Result Set</em>' class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERSECTION_FILTERING_RESULT_SET_FEATURE_COUNT = FILTERING_RESULT_SET_FEATURE_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.filtering.FilteringModel <em>Model</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Model</em>'.
	 * @see org.polarsys.capella.filtering.FilteringModel
	 * @generated
	 */
	EClass getFilteringModel();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.filtering.FilteringModel#getOwnedFilteringCriteria <em>Owned Filtering Criteria</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Filtering Criteria</em>'.
	 * @see org.polarsys.capella.filtering.FilteringModel#getOwnedFilteringCriteria()
	 * @see #getFilteringModel()
	 * @generated
	 */
	EReference getFilteringModel_OwnedFilteringCriteria();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.filtering.FilteringModel#getOwnedFilteringCriterionPkgs <em>Owned Filtering Criterion Pkgs</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Filtering Criterion Pkgs</em>'.
	 * @see org.polarsys.capella.filtering.FilteringModel#getOwnedFilteringCriterionPkgs()
	 * @see #getFilteringModel()
	 * @generated
	 */
	EReference getFilteringModel_OwnedFilteringCriterionPkgs();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.filtering.FilteringCriterion <em>Criterion</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Criterion</em>'.
	 * @see org.polarsys.capella.filtering.FilteringCriterion
	 * @generated
	 */
	EClass getFilteringCriterion();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.filtering.FilteringCriterionSet <em>Criterion Set</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Criterion Set</em>'.
	 * @see org.polarsys.capella.filtering.FilteringCriterionSet
	 * @generated
	 */
	EClass getFilteringCriterionSet();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.filtering.FilteringCriterionSet#getFilteringCriteria <em>Filtering Criteria</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Filtering Criteria</em>'.
	 * @see org.polarsys.capella.filtering.FilteringCriterionSet#getFilteringCriteria()
	 * @see #getFilteringCriterionSet()
	 * @generated
	 */
	EReference getFilteringCriterionSet_FilteringCriteria();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.filtering.FilteringResults <em>Results</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Results</em>'.
	 * @see org.polarsys.capella.filtering.FilteringResults
	 * @generated
	 */
	EClass getFilteringResults();

	/**
	 * Returns the meta object for the containment reference list
	 * '{@link org.polarsys.capella.filtering.FilteringResults#getFilteringResults <em>Filtering Results</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Filtering Results</em>'.
	 * @see org.polarsys.capella.filtering.FilteringResults#getFilteringResults()
	 * @see #getFilteringResults()
	 * @generated
	 */
	EReference getFilteringResults_FilteringResults();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.filtering.FilteringResults#getOwnedFilteringResultPkgs <em>Owned Filtering Result Pkgs</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Filtering Result Pkgs</em>'.
	 * @see org.polarsys.capella.filtering.FilteringResults#getOwnedFilteringResultPkgs()
	 * @see #getFilteringResults()
	 * @generated
	 */
	EReference getFilteringResults_OwnedFilteringResultPkgs();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.filtering.FilteringResult <em>Result</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Result</em>'.
	 * @see org.polarsys.capella.filtering.FilteringResult
	 * @generated
	 */
	EClass getFilteringResult();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.filtering.AssociatedFilteringCriterionSet <em>Associated Filtering Criterion Set</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Associated Filtering Criterion Set</em>'.
	 * @see org.polarsys.capella.filtering.AssociatedFilteringCriterionSet
	 * @generated
	 */
	EClass getAssociatedFilteringCriterionSet();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.filtering.CreationDefaultFilteringCriterionSet <em>Creation Default Filtering Criterion Set</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Creation Default Filtering Criterion Set</em>'.
	 * @see org.polarsys.capella.filtering.CreationDefaultFilteringCriterionSet
	 * @generated
	 */
	EClass getCreationDefaultFilteringCriterionSet();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.filtering.FilteringResultPkg <em>Result Pkg</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Result Pkg</em>'.
	 * @see org.polarsys.capella.filtering.FilteringResultPkg
	 * @generated
	 */
	EClass getFilteringResultPkg();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.filtering.FilteringResultPkg#getOwnedFilteringResults <em>Owned Filtering Results</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Filtering Results</em>'.
	 * @see org.polarsys.capella.filtering.FilteringResultPkg#getOwnedFilteringResults()
	 * @see #getFilteringResultPkg()
	 * @generated
	 */
	EReference getFilteringResultPkg_OwnedFilteringResults();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.filtering.FilteringResultPkg#getOwnedFilteringResultPkgs <em>Owned Filtering Result Pkgs</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Filtering Result Pkgs</em>'.
	 * @see org.polarsys.capella.filtering.FilteringResultPkg#getOwnedFilteringResultPkgs()
	 * @see #getFilteringResultPkg()
	 * @generated
	 */
	EReference getFilteringResultPkg_OwnedFilteringResultPkgs();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.filtering.FilteringCriterionPkg <em>Criterion Pkg</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Criterion Pkg</em>'.
	 * @see org.polarsys.capella.filtering.FilteringCriterionPkg
	 * @generated
	 */
	EClass getFilteringCriterionPkg();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.filtering.FilteringCriterionPkg#getOwnedFilteringCriteria <em>Owned Filtering Criteria</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Filtering Criteria</em>'.
	 * @see org.polarsys.capella.filtering.FilteringCriterionPkg#getOwnedFilteringCriteria()
	 * @see #getFilteringCriterionPkg()
	 * @generated
	 */
	EReference getFilteringCriterionPkg_OwnedFilteringCriteria();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.filtering.FilteringCriterionPkg#getOwnedFilteringCriterionPkgs <em>Owned Filtering Criterion Pkgs</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Filtering Criterion Pkgs</em>'.
	 * @see org.polarsys.capella.filtering.FilteringCriterionPkg#getOwnedFilteringCriterionPkgs()
	 * @see #getFilteringCriterionPkg()
	 * @generated
	 */
	EReference getFilteringCriterionPkg_OwnedFilteringCriterionPkgs();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.filtering.ComposedFilteringResult <em>Composed Filtering Result</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composed Filtering Result</em>'.
	 * @see org.polarsys.capella.filtering.ComposedFilteringResult
	 * @generated
	 */
	EClass getComposedFilteringResult();

	/**
	 * Returns the meta object for the containment reference '{@link org.polarsys.capella.filtering.ComposedFilteringResult#getUnionFilteringResultSet <em>Union Filtering Result Set</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Union Filtering Result Set</em>'.
	 * @see org.polarsys.capella.filtering.ComposedFilteringResult#getUnionFilteringResultSet()
	 * @see #getComposedFilteringResult()
	 * @generated
	 */
	EReference getComposedFilteringResult_UnionFilteringResultSet();

	/**
	 * Returns the meta object for the containment reference '{@link org.polarsys.capella.filtering.ComposedFilteringResult#getIntersectionFilteringResultSet <em>Intersection Filtering Result Set</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Intersection Filtering Result Set</em>'.
	 * @see org.polarsys.capella.filtering.ComposedFilteringResult#getIntersectionFilteringResultSet()
	 * @see #getComposedFilteringResult()
	 * @generated
	 */
	EReference getComposedFilteringResult_IntersectionFilteringResultSet();

	/**
	 * Returns the meta object for the containment reference '{@link org.polarsys.capella.filtering.ComposedFilteringResult#getExclusionFilteringResultSet <em>Exclusion Filtering Result Set</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Exclusion Filtering Result Set</em>'.
	 * @see org.polarsys.capella.filtering.ComposedFilteringResult#getExclusionFilteringResultSet()
	 * @see #getComposedFilteringResult()
	 * @generated
	 */
	EReference getComposedFilteringResult_ExclusionFilteringResultSet();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.filtering.FilteringResultSet <em>Result Set</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Result Set</em>'.
	 * @see org.polarsys.capella.filtering.FilteringResultSet
	 * @generated
	 */
	EClass getFilteringResultSet();

	/**
	 * Returns the meta object for the reference list
	 * '{@link org.polarsys.capella.filtering.FilteringResultSet#getFilteringResults <em>Filtering Results</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Filtering Results</em>'.
	 * @see org.polarsys.capella.filtering.FilteringResultSet#getFilteringResults()
	 * @see #getFilteringResultSet()
	 * @generated
	 */
	EReference getFilteringResultSet_FilteringResults();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.filtering.AbstractFilteringResult <em>Abstract Filtering Result</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Filtering Result</em>'.
	 * @see org.polarsys.capella.filtering.AbstractFilteringResult
	 * @generated
	 */
	EClass getAbstractFilteringResult();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.filtering.UnionFilteringResultSet <em>Union Filtering Result Set</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Union Filtering Result Set</em>'.
	 * @see org.polarsys.capella.filtering.UnionFilteringResultSet
	 * @generated
	 */
	EClass getUnionFilteringResultSet();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.filtering.ExclusionFilteringResultSet <em>Exclusion Filtering Result Set</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exclusion Filtering Result Set</em>'.
	 * @see org.polarsys.capella.filtering.ExclusionFilteringResultSet
	 * @generated
	 */
	EClass getExclusionFilteringResultSet();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.filtering.IntersectionFilteringResultSet <em>Intersection Filtering Result Set</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Intersection Filtering Result Set</em>'.
	 * @see org.polarsys.capella.filtering.IntersectionFilteringResultSet
	 * @generated
	 */
	EClass getIntersectionFilteringResultSet();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	FilteringFactory getFilteringFactory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
			 * The meta object literal for the '{@link org.polarsys.capella.filtering.impl.FilteringModelImpl <em>Model</em>}' class.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @see org.polarsys.capella.filtering.impl.FilteringModelImpl
			 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getFilteringModel()
			 * @generated
			 */
		EClass FILTERING_MODEL = eINSTANCE.getFilteringModel();

		/**
			 * The meta object literal for the '<em><b>Owned Filtering Criteria</b></em>' containment reference list feature.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @generated
			 */
		EReference FILTERING_MODEL__OWNED_FILTERING_CRITERIA = eINSTANCE.getFilteringModel_OwnedFilteringCriteria();

		/**
			 * The meta object literal for the '<em><b>Owned Filtering Criterion Pkgs</b></em>' containment reference list feature.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @generated
			 */
		EReference FILTERING_MODEL__OWNED_FILTERING_CRITERION_PKGS = eINSTANCE
				.getFilteringModel_OwnedFilteringCriterionPkgs();

		/**
			 * The meta object literal for the '{@link org.polarsys.capella.filtering.impl.FilteringCriterionImpl <em>Criterion</em>}' class.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @see org.polarsys.capella.filtering.impl.FilteringCriterionImpl
			 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getFilteringCriterion()
			 * @generated
			 */
		EClass FILTERING_CRITERION = eINSTANCE.getFilteringCriterion();

		/**
			 * The meta object literal for the '{@link org.polarsys.capella.filtering.impl.FilteringCriterionSetImpl <em>Criterion Set</em>}' class.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @see org.polarsys.capella.filtering.impl.FilteringCriterionSetImpl
			 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getFilteringCriterionSet()
			 * @generated
			 */
		EClass FILTERING_CRITERION_SET = eINSTANCE.getFilteringCriterionSet();

		/**
			 * The meta object literal for the '<em><b>Filtering Criteria</b></em>' reference list feature.
			 * <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
			 * @generated
			 */
		EReference FILTERING_CRITERION_SET__FILTERING_CRITERIA = eINSTANCE.getFilteringCriterionSet_FilteringCriteria();

		/**
			 * The meta object literal for the '{@link org.polarsys.capella.filtering.impl.FilteringResultsImpl <em>Results</em>}' class.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @see org.polarsys.capella.filtering.impl.FilteringResultsImpl
			 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getFilteringResults()
			 * @generated
			 */
		EClass FILTERING_RESULTS = eINSTANCE.getFilteringResults();

		/**
		 * The meta object literal for the '<em><b>Filtering Results</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference FILTERING_RESULTS__FILTERING_RESULTS = eINSTANCE.getFilteringResults_FilteringResults();

		/**
			 * The meta object literal for the '<em><b>Owned Filtering Result Pkgs</b></em>' containment reference list feature.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @generated
			 */
		EReference FILTERING_RESULTS__OWNED_FILTERING_RESULT_PKGS = eINSTANCE
				.getFilteringResults_OwnedFilteringResultPkgs();

		/**
			 * The meta object literal for the '{@link org.polarsys.capella.filtering.impl.FilteringResultImpl <em>Result</em>}' class.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @see org.polarsys.capella.filtering.impl.FilteringResultImpl
			 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getFilteringResult()
			 * @generated
			 */
		EClass FILTERING_RESULT = eINSTANCE.getFilteringResult();

		/**
			 * The meta object literal for the '{@link org.polarsys.capella.filtering.impl.AssociatedFilteringCriterionSetImpl <em>Associated Filtering Criterion Set</em>}' class.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @see org.polarsys.capella.filtering.impl.AssociatedFilteringCriterionSetImpl
			 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getAssociatedFilteringCriterionSet()
			 * @generated
			 */
		EClass ASSOCIATED_FILTERING_CRITERION_SET = eINSTANCE.getAssociatedFilteringCriterionSet();

		/**
			 * The meta object literal for the '{@link org.polarsys.capella.filtering.impl.CreationDefaultFilteringCriterionSetImpl <em>Creation Default Filtering Criterion Set</em>}' class.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @see org.polarsys.capella.filtering.impl.CreationDefaultFilteringCriterionSetImpl
			 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getCreationDefaultFilteringCriterionSet()
			 * @generated
			 */
		EClass CREATION_DEFAULT_FILTERING_CRITERION_SET = eINSTANCE.getCreationDefaultFilteringCriterionSet();

		/**
			 * The meta object literal for the '{@link org.polarsys.capella.filtering.impl.FilteringResultPkgImpl <em>Result Pkg</em>}' class.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @see org.polarsys.capella.filtering.impl.FilteringResultPkgImpl
			 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getFilteringResultPkg()
			 * @generated
			 */
		EClass FILTERING_RESULT_PKG = eINSTANCE.getFilteringResultPkg();

		/**
			 * The meta object literal for the '<em><b>Owned Filtering Results</b></em>' containment reference list feature.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @generated
			 */
		EReference FILTERING_RESULT_PKG__OWNED_FILTERING_RESULTS = eINSTANCE
				.getFilteringResultPkg_OwnedFilteringResults();

		/**
			 * The meta object literal for the '<em><b>Owned Filtering Result Pkgs</b></em>' containment reference list feature.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @generated
			 */
		EReference FILTERING_RESULT_PKG__OWNED_FILTERING_RESULT_PKGS = eINSTANCE
				.getFilteringResultPkg_OwnedFilteringResultPkgs();

		/**
			 * The meta object literal for the '{@link org.polarsys.capella.filtering.impl.FilteringCriterionPkgImpl <em>Criterion Pkg</em>}' class.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @see org.polarsys.capella.filtering.impl.FilteringCriterionPkgImpl
			 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getFilteringCriterionPkg()
			 * @generated
			 */
		EClass FILTERING_CRITERION_PKG = eINSTANCE.getFilteringCriterionPkg();

		/**
			 * The meta object literal for the '<em><b>Owned Filtering Criteria</b></em>' containment reference list feature.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @generated
			 */
		EReference FILTERING_CRITERION_PKG__OWNED_FILTERING_CRITERIA = eINSTANCE
				.getFilteringCriterionPkg_OwnedFilteringCriteria();

		/**
			 * The meta object literal for the '<em><b>Owned Filtering Criterion Pkgs</b></em>' containment reference list feature.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @generated
			 */
		EReference FILTERING_CRITERION_PKG__OWNED_FILTERING_CRITERION_PKGS = eINSTANCE
				.getFilteringCriterionPkg_OwnedFilteringCriterionPkgs();

		/**
			 * The meta object literal for the '{@link org.polarsys.capella.filtering.impl.ComposedFilteringResultImpl <em>Composed Filtering Result</em>}' class.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @see org.polarsys.capella.filtering.impl.ComposedFilteringResultImpl
			 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getComposedFilteringResult()
			 * @generated
			 */
		EClass COMPOSED_FILTERING_RESULT = eINSTANCE.getComposedFilteringResult();

		/**
		 * The meta object literal for the '<em><b>Union Filtering Result Set</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference COMPOSED_FILTERING_RESULT__UNION_FILTERING_RESULT_SET = eINSTANCE
				.getComposedFilteringResult_UnionFilteringResultSet();

		/**
			 * The meta object literal for the '<em><b>Intersection Filtering Result Set</b></em>' containment reference feature.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @generated
			 */
		EReference COMPOSED_FILTERING_RESULT__INTERSECTION_FILTERING_RESULT_SET = eINSTANCE
				.getComposedFilteringResult_IntersectionFilteringResultSet();

		/**
			 * The meta object literal for the '<em><b>Exclusion Filtering Result Set</b></em>' containment reference feature.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @generated
			 */
		EReference COMPOSED_FILTERING_RESULT__EXCLUSION_FILTERING_RESULT_SET = eINSTANCE
				.getComposedFilteringResult_ExclusionFilteringResultSet();

		/**
			 * The meta object literal for the '{@link org.polarsys.capella.filtering.impl.FilteringResultSetImpl <em>Result Set</em>}' class.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @see org.polarsys.capella.filtering.impl.FilteringResultSetImpl
			 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getFilteringResultSet()
			 * @generated
			 */
		EClass FILTERING_RESULT_SET = eINSTANCE.getFilteringResultSet();

		/**
			 * The meta object literal for the '<em><b>Filtering Results</b></em>' reference list feature.
			 * <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
			 * @generated
			 */
		EReference FILTERING_RESULT_SET__FILTERING_RESULTS = eINSTANCE.getFilteringResultSet_FilteringResults();

		/**
			 * The meta object literal for the '{@link org.polarsys.capella.filtering.impl.AbstractFilteringResultImpl <em>Abstract Filtering Result</em>}' class.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @see org.polarsys.capella.filtering.impl.AbstractFilteringResultImpl
			 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getAbstractFilteringResult()
			 * @generated
			 */
		EClass ABSTRACT_FILTERING_RESULT = eINSTANCE.getAbstractFilteringResult();

		/**
			 * The meta object literal for the '{@link org.polarsys.capella.filtering.impl.UnionFilteringResultSetImpl <em>Union Filtering Result Set</em>}' class.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @see org.polarsys.capella.filtering.impl.UnionFilteringResultSetImpl
			 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getUnionFilteringResultSet()
			 * @generated
			 */
		EClass UNION_FILTERING_RESULT_SET = eINSTANCE.getUnionFilteringResultSet();

		/**
			 * The meta object literal for the '{@link org.polarsys.capella.filtering.impl.ExclusionFilteringResultSetImpl <em>Exclusion Filtering Result Set</em>}' class.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @see org.polarsys.capella.filtering.impl.ExclusionFilteringResultSetImpl
			 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getExclusionFilteringResultSet()
			 * @generated
			 */
		EClass EXCLUSION_FILTERING_RESULT_SET = eINSTANCE.getExclusionFilteringResultSet();

		/**
			 * The meta object literal for the '{@link org.polarsys.capella.filtering.impl.IntersectionFilteringResultSetImpl <em>Intersection Filtering Result Set</em>}' class.
			 * <!-- begin-user-doc --> <!-- end-user-doc -->
			 * @see org.polarsys.capella.filtering.impl.IntersectionFilteringResultSetImpl
			 * @see org.polarsys.capella.filtering.impl.FilteringPackageImpl#getIntersectionFilteringResultSet()
			 * @generated
			 */
		EClass INTERSECTION_FILTERING_RESULT_SET = eINSTANCE.getIntersectionFilteringResultSet();

	}

} // FilteringPackage
