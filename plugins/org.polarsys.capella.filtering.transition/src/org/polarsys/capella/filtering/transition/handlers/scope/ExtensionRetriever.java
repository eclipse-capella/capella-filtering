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
package org.polarsys.capella.filtering.transition.handlers.scope;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeRetriever;
import org.polarsys.capella.filtering.AssociatedFilteringCriterionSet;
import org.polarsys.capella.filtering.transition.constants.IExtensionConstants;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 * 
 */
public class ExtensionRetriever implements IScopeRetriever {

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public Collection<? extends EObject> retrieveRelatedElements(EObject source, IContext context) {
    Collection<EObject> elements = new LinkedList<>();
    if (source instanceof ExtensibleElement) {
      ExtensibleElement element = (ExtensibleElement) source;
      if (element.getOwnedExtensions() != null && OptionsHandlerHelper.getInstance(context).getBooleanValue(context,
          IExtensionConstants.OPTIONS_SCOPE, IExtensionConstants.OPTIONS_ASSOCIATEDCRITERIONSET,
          IExtensionConstants.OPTIONS_ASSOCIATEDCRITERIONSET__DEFAULT_VALUE)) {
        for (EObject eObject : element.getOwnedExtensions()) {
          if (eObject instanceof AssociatedFilteringCriterionSet) {
            elements.add(eObject);
          }
        }
      }
    }
    return elements;
  }

  /**
   * {@inheritDoc}
   */
  public Collection<? extends EObject> retrieveSharedElements(IContext context) {
    return Collections.emptyList();
  }
}
