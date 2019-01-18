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
package org.polarsys.capella.filtering.menu.contribution;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;
import org.polarsys.capella.filtering.CreationDefaultFilteringCriterionSet;
import org.polarsys.capella.filtering.FilteringCriterion;
import org.polarsys.capella.filtering.FilteringFactory;
import org.polarsys.capella.filtering.FilteringPackage;
import org.polarsys.capella.filtering.tools.utils.FilteringUtils;

public class FilteringItemContribution implements IMDEMenuItemContribution {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Command executionContribution(EditingDomain editingDomain, ModelElement containerElement,
      ModelElement createdElement, EStructuralFeature feature) {

    if (createdElement instanceof CapellaElement
        && !FilteringUtils.isInstanceOfFilteringExcludedElements(createdElement)) {

      // Get creation default features
      CreationDefaultFilteringCriterionSet creationDefaultFeatureSet = FilteringUtils
          .getCreationDefaultFeatureSet(containerElement);

      if (creationDefaultFeatureSet != null) {
        List<FilteringCriterion> creationDefaultFeatures = creationDefaultFeatureSet.getFilteringCriteria();
        if (!creationDefaultFeatures.isEmpty()) {

          // Create associatedFeatureSet for the element if it doesn't
          // exist
          AssociatedFilteringCriterionSet featureSet = FilteringUtils
              .getAssociatedFilteringCriterionSet((CapellaElement) createdElement);
          if (featureSet == null) {
            AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
              @Override
              public void run() {
                AssociatedFilteringCriterionSet newFeatureSet = FilteringFactory.eINSTANCE
                    .createAssociatedFilteringCriterionSet();
                createdElement.getOwnedExtensions().add(newFeatureSet);
              }
            };
            executeCommand(command, containerElement);
            // Get the associated feature set again
            featureSet = FilteringUtils.getAssociatedFilteringCriterionSet((CapellaElement) createdElement);
          }

          // Create the command that we should return in the execution
          // contribution
          CompoundCommand cmd = new CompoundCommand();
          for (FilteringCriterion filteringCriterion : creationDefaultFeatures) {
            if (!FilteringUtils.getAssociatedCriteria(createdElement).contains(filteringCriterion)) {
              cmd.append(new AddCommand(editingDomain, featureSet,
                  FilteringPackage.eINSTANCE.getFilteringCriterionSet_FilteringCriteria(), filteringCriterion));
            }
          }
          return cmd;
        }
      }
    }
    return null;
  }

  private boolean notFilteringItem(ModelElement elt) {

    return (elt instanceof FilteringCriterion);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EClass getMetaclass() {
    return ModellingcorePackage.Literals.MODEL_ELEMENT;
  }

  /**
   * Execute given command.
   * 
   * @param command
   * @param createdElement
   */
  protected void executeCommand(ICommand command, ModelElement createdElement) {
    FilteringUtils.executeCommand(command, createdElement);
  }
}
