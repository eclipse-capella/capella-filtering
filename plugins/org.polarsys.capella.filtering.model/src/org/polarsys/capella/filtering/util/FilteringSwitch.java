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

package org.polarsys.capella.filtering.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;

import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.filtering.*;

import org.polarsys.kitalpha.emde.model.Element;
import org.polarsys.kitalpha.emde.model.ElementExtension;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 * @see org.polarsys.capella.filtering.FilteringPackage
 * @generated
 */
public class FilteringSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static FilteringPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public FilteringSwitch() {
		if (modelPackage == null) {
			modelPackage = FilteringPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
		case FilteringPackage.FILTERING_MODEL: {
			FilteringModel filteringModel = (FilteringModel) theEObject;
			T result = caseFilteringModel(filteringModel);
			if (result == null)
				result = caseNamedElement(filteringModel);
			if (result == null)
				result = caseElementExtension(filteringModel);
			if (result == null)
				result = caseAbstractNamedElement(filteringModel);
			if (result == null)
				result = caseCapellaElement(filteringModel);
			if (result == null)
				result = caseTraceableElement(filteringModel);
			if (result == null)
				result = casePublishableElement(filteringModel);
			if (result == null)
				result = caseModelElement(filteringModel);
			if (result == null)
				result = caseExtensibleElement(filteringModel);
			if (result == null)
				result = caseElement(filteringModel);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FilteringPackage.FILTERING_CRITERION: {
			FilteringCriterion filteringCriterion = (FilteringCriterion) theEObject;
			T result = caseFilteringCriterion(filteringCriterion);
			if (result == null)
				result = caseNamedElement(filteringCriterion);
			if (result == null)
				result = caseAbstractNamedElement(filteringCriterion);
			if (result == null)
				result = caseCapellaElement(filteringCriterion);
			if (result == null)
				result = caseTraceableElement(filteringCriterion);
			if (result == null)
				result = casePublishableElement(filteringCriterion);
			if (result == null)
				result = caseModelElement(filteringCriterion);
			if (result == null)
				result = caseExtensibleElement(filteringCriterion);
			if (result == null)
				result = caseElement(filteringCriterion);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FilteringPackage.FILTERING_CRITERION_SET: {
			FilteringCriterionSet filteringCriterionSet = (FilteringCriterionSet) theEObject;
			T result = caseFilteringCriterionSet(filteringCriterionSet);
			if (result == null)
				result = caseNamedElement(filteringCriterionSet);
			if (result == null)
				result = caseAbstractNamedElement(filteringCriterionSet);
			if (result == null)
				result = caseCapellaElement(filteringCriterionSet);
			if (result == null)
				result = caseTraceableElement(filteringCriterionSet);
			if (result == null)
				result = casePublishableElement(filteringCriterionSet);
			if (result == null)
				result = caseModelElement(filteringCriterionSet);
			if (result == null)
				result = caseExtensibleElement(filteringCriterionSet);
			if (result == null)
				result = caseElement(filteringCriterionSet);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FilteringPackage.FILTERING_RESULTS: {
			FilteringResults filteringResults = (FilteringResults) theEObject;
			T result = caseFilteringResults(filteringResults);
			if (result == null)
				result = caseNamedElement(filteringResults);
			if (result == null)
				result = caseElementExtension(filteringResults);
			if (result == null)
				result = caseAbstractNamedElement(filteringResults);
			if (result == null)
				result = caseCapellaElement(filteringResults);
			if (result == null)
				result = caseTraceableElement(filteringResults);
			if (result == null)
				result = casePublishableElement(filteringResults);
			if (result == null)
				result = caseModelElement(filteringResults);
			if (result == null)
				result = caseExtensibleElement(filteringResults);
			if (result == null)
				result = caseElement(filteringResults);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FilteringPackage.FILTERING_RESULT: {
			FilteringResult filteringResult = (FilteringResult) theEObject;
			T result = caseFilteringResult(filteringResult);
			if (result == null)
				result = caseFilteringCriterionSet(filteringResult);
			if (result == null)
				result = caseAbstractFilteringResult(filteringResult);
			if (result == null)
				result = caseNamedElement(filteringResult);
			if (result == null)
				result = caseAbstractNamedElement(filteringResult);
			if (result == null)
				result = caseCapellaElement(filteringResult);
			if (result == null)
				result = caseTraceableElement(filteringResult);
			if (result == null)
				result = casePublishableElement(filteringResult);
			if (result == null)
				result = caseModelElement(filteringResult);
			if (result == null)
				result = caseExtensibleElement(filteringResult);
			if (result == null)
				result = caseElement(filteringResult);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FilteringPackage.ASSOCIATED_FILTERING_CRITERION_SET: {
			AssociatedFilteringCriterionSet associatedFilteringCriterionSet = (AssociatedFilteringCriterionSet) theEObject;
			T result = caseAssociatedFilteringCriterionSet(associatedFilteringCriterionSet);
			if (result == null)
				result = caseFilteringCriterionSet(associatedFilteringCriterionSet);
			if (result == null)
				result = caseElementExtension(associatedFilteringCriterionSet);
			if (result == null)
				result = caseNamedElement(associatedFilteringCriterionSet);
			if (result == null)
				result = caseAbstractNamedElement(associatedFilteringCriterionSet);
			if (result == null)
				result = caseCapellaElement(associatedFilteringCriterionSet);
			if (result == null)
				result = caseTraceableElement(associatedFilteringCriterionSet);
			if (result == null)
				result = casePublishableElement(associatedFilteringCriterionSet);
			if (result == null)
				result = caseModelElement(associatedFilteringCriterionSet);
			if (result == null)
				result = caseExtensibleElement(associatedFilteringCriterionSet);
			if (result == null)
				result = caseElement(associatedFilteringCriterionSet);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FilteringPackage.CREATION_DEFAULT_FILTERING_CRITERION_SET: {
			CreationDefaultFilteringCriterionSet creationDefaultFilteringCriterionSet = (CreationDefaultFilteringCriterionSet) theEObject;
			T result = caseCreationDefaultFilteringCriterionSet(creationDefaultFilteringCriterionSet);
			if (result == null)
				result = caseFilteringCriterionSet(creationDefaultFilteringCriterionSet);
			if (result == null)
				result = caseElementExtension(creationDefaultFilteringCriterionSet);
			if (result == null)
				result = caseNamedElement(creationDefaultFilteringCriterionSet);
			if (result == null)
				result = caseAbstractNamedElement(creationDefaultFilteringCriterionSet);
			if (result == null)
				result = caseCapellaElement(creationDefaultFilteringCriterionSet);
			if (result == null)
				result = caseTraceableElement(creationDefaultFilteringCriterionSet);
			if (result == null)
				result = casePublishableElement(creationDefaultFilteringCriterionSet);
			if (result == null)
				result = caseModelElement(creationDefaultFilteringCriterionSet);
			if (result == null)
				result = caseExtensibleElement(creationDefaultFilteringCriterionSet);
			if (result == null)
				result = caseElement(creationDefaultFilteringCriterionSet);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FilteringPackage.FILTERING_RESULT_PKG: {
			FilteringResultPkg filteringResultPkg = (FilteringResultPkg) theEObject;
			T result = caseFilteringResultPkg(filteringResultPkg);
			if (result == null)
				result = caseNamespace(filteringResultPkg);
			if (result == null)
				result = caseNamedElement(filteringResultPkg);
			if (result == null)
				result = caseAbstractNamedElement(filteringResultPkg);
			if (result == null)
				result = caseCapellaElement(filteringResultPkg);
			if (result == null)
				result = caseTraceableElement(filteringResultPkg);
			if (result == null)
				result = casePublishableElement(filteringResultPkg);
			if (result == null)
				result = caseModelElement(filteringResultPkg);
			if (result == null)
				result = caseExtensibleElement(filteringResultPkg);
			if (result == null)
				result = caseElement(filteringResultPkg);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FilteringPackage.FILTERING_CRITERION_PKG: {
			FilteringCriterionPkg filteringCriterionPkg = (FilteringCriterionPkg) theEObject;
			T result = caseFilteringCriterionPkg(filteringCriterionPkg);
			if (result == null)
				result = caseNamespace(filteringCriterionPkg);
			if (result == null)
				result = caseNamedElement(filteringCriterionPkg);
			if (result == null)
				result = caseAbstractNamedElement(filteringCriterionPkg);
			if (result == null)
				result = caseCapellaElement(filteringCriterionPkg);
			if (result == null)
				result = caseTraceableElement(filteringCriterionPkg);
			if (result == null)
				result = casePublishableElement(filteringCriterionPkg);
			if (result == null)
				result = caseModelElement(filteringCriterionPkg);
			if (result == null)
				result = caseExtensibleElement(filteringCriterionPkg);
			if (result == null)
				result = caseElement(filteringCriterionPkg);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FilteringPackage.COMPOSED_FILTERING_RESULT: {
			ComposedFilteringResult composedFilteringResult = (ComposedFilteringResult) theEObject;
			T result = caseComposedFilteringResult(composedFilteringResult);
			if (result == null)
				result = caseAbstractFilteringResult(composedFilteringResult);
			if (result == null)
				result = caseNamedElement(composedFilteringResult);
			if (result == null)
				result = caseAbstractNamedElement(composedFilteringResult);
			if (result == null)
				result = caseCapellaElement(composedFilteringResult);
			if (result == null)
				result = caseTraceableElement(composedFilteringResult);
			if (result == null)
				result = casePublishableElement(composedFilteringResult);
			if (result == null)
				result = caseModelElement(composedFilteringResult);
			if (result == null)
				result = caseExtensibleElement(composedFilteringResult);
			if (result == null)
				result = caseElement(composedFilteringResult);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FilteringPackage.FILTERING_RESULT_SET: {
			FilteringResultSet filteringResultSet = (FilteringResultSet) theEObject;
			T result = caseFilteringResultSet(filteringResultSet);
			if (result == null)
				result = caseNamedElement(filteringResultSet);
			if (result == null)
				result = caseAbstractNamedElement(filteringResultSet);
			if (result == null)
				result = caseCapellaElement(filteringResultSet);
			if (result == null)
				result = caseTraceableElement(filteringResultSet);
			if (result == null)
				result = casePublishableElement(filteringResultSet);
			if (result == null)
				result = caseModelElement(filteringResultSet);
			if (result == null)
				result = caseExtensibleElement(filteringResultSet);
			if (result == null)
				result = caseElement(filteringResultSet);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FilteringPackage.ABSTRACT_FILTERING_RESULT: {
			AbstractFilteringResult abstractFilteringResult = (AbstractFilteringResult) theEObject;
			T result = caseAbstractFilteringResult(abstractFilteringResult);
			if (result == null)
				result = caseNamedElement(abstractFilteringResult);
			if (result == null)
				result = caseAbstractNamedElement(abstractFilteringResult);
			if (result == null)
				result = caseCapellaElement(abstractFilteringResult);
			if (result == null)
				result = caseTraceableElement(abstractFilteringResult);
			if (result == null)
				result = casePublishableElement(abstractFilteringResult);
			if (result == null)
				result = caseModelElement(abstractFilteringResult);
			if (result == null)
				result = caseExtensibleElement(abstractFilteringResult);
			if (result == null)
				result = caseElement(abstractFilteringResult);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FilteringPackage.UNION_FILTERING_RESULT_SET: {
			UnionFilteringResultSet unionFilteringResultSet = (UnionFilteringResultSet) theEObject;
			T result = caseUnionFilteringResultSet(unionFilteringResultSet);
			if (result == null)
				result = caseFilteringResultSet(unionFilteringResultSet);
			if (result == null)
				result = caseNamedElement(unionFilteringResultSet);
			if (result == null)
				result = caseAbstractNamedElement(unionFilteringResultSet);
			if (result == null)
				result = caseCapellaElement(unionFilteringResultSet);
			if (result == null)
				result = caseTraceableElement(unionFilteringResultSet);
			if (result == null)
				result = casePublishableElement(unionFilteringResultSet);
			if (result == null)
				result = caseModelElement(unionFilteringResultSet);
			if (result == null)
				result = caseExtensibleElement(unionFilteringResultSet);
			if (result == null)
				result = caseElement(unionFilteringResultSet);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FilteringPackage.EXCLUSION_FILTERING_RESULT_SET: {
			ExclusionFilteringResultSet exclusionFilteringResultSet = (ExclusionFilteringResultSet) theEObject;
			T result = caseExclusionFilteringResultSet(exclusionFilteringResultSet);
			if (result == null)
				result = caseFilteringResultSet(exclusionFilteringResultSet);
			if (result == null)
				result = caseNamedElement(exclusionFilteringResultSet);
			if (result == null)
				result = caseAbstractNamedElement(exclusionFilteringResultSet);
			if (result == null)
				result = caseCapellaElement(exclusionFilteringResultSet);
			if (result == null)
				result = caseTraceableElement(exclusionFilteringResultSet);
			if (result == null)
				result = casePublishableElement(exclusionFilteringResultSet);
			if (result == null)
				result = caseModelElement(exclusionFilteringResultSet);
			if (result == null)
				result = caseExtensibleElement(exclusionFilteringResultSet);
			if (result == null)
				result = caseElement(exclusionFilteringResultSet);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FilteringPackage.INTERSECTION_FILTERING_RESULT_SET: {
			IntersectionFilteringResultSet intersectionFilteringResultSet = (IntersectionFilteringResultSet) theEObject;
			T result = caseIntersectionFilteringResultSet(intersectionFilteringResultSet);
			if (result == null)
				result = caseFilteringResultSet(intersectionFilteringResultSet);
			if (result == null)
				result = caseNamedElement(intersectionFilteringResultSet);
			if (result == null)
				result = caseAbstractNamedElement(intersectionFilteringResultSet);
			if (result == null)
				result = caseCapellaElement(intersectionFilteringResultSet);
			if (result == null)
				result = caseTraceableElement(intersectionFilteringResultSet);
			if (result == null)
				result = casePublishableElement(intersectionFilteringResultSet);
			if (result == null)
				result = caseModelElement(intersectionFilteringResultSet);
			if (result == null)
				result = caseExtensibleElement(intersectionFilteringResultSet);
			if (result == null)
				result = caseElement(intersectionFilteringResultSet);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFilteringModel(FilteringModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Criterion</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Criterion</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFilteringCriterion(FilteringCriterion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Criterion Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Criterion Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFilteringCriterionSet(FilteringCriterionSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Results</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Results</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFilteringResults(FilteringResults object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Result</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFilteringResult(FilteringResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Associated Filtering Criterion Set</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Associated Filtering Criterion Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssociatedFilteringCriterionSet(AssociatedFilteringCriterionSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Creation Default Filtering Criterion Set</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the
	 * switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Creation Default Filtering Criterion Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCreationDefaultFilteringCriterionSet(CreationDefaultFilteringCriterionSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Result Pkg</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Result Pkg</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFilteringResultPkg(FilteringResultPkg object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Criterion Pkg</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Criterion Pkg</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFilteringCriterionPkg(FilteringCriterionPkg object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Composed Filtering Result</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Composed Filtering Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComposedFilteringResult(ComposedFilteringResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Result Set</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Result Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFilteringResultSet(FilteringResultSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Filtering Result</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Filtering Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractFilteringResult(AbstractFilteringResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Union Filtering Result Set</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Union Filtering Result Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnionFilteringResultSet(UnionFilteringResultSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exclusion Filtering Result Set</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exclusion Filtering Result Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExclusionFilteringResultSet(ExclusionFilteringResultSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Intersection Filtering Result Set</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Intersection Filtering Result Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntersectionFilteringResultSet(IntersectionFilteringResultSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElement(Element object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extensible Element</em>'.
	 * <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extensible Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtensibleElement(ExtensibleElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelElement(ModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Named Element</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractNamedElement(AbstractNamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Traceable Element</em>'.
	 * <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Traceable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTraceableElement(TraceableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Publishable Element</em>'.
	 * <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Publishable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePublishableElement(PublishableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Capella Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Capella Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCapellaElement(CapellaElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element Extension</em>'.
	 * <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element Extension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElementExtension(ElementExtension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Namespace</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Namespace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamespace(Namespace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch, but this is the last case
	 * anyway. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} // FilteringSwitch
