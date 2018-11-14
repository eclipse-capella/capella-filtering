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
package org.polarsys.capella.filtering.tools.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.command.PreDeleteHandler;
import org.polarsys.capella.core.model.handler.command.PreDeleteStructureCommand;
import org.polarsys.capella.core.model.handler.command.PreRemoveCommand;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;

class ModelOnlyFilteringDeleteCommand extends CapellaDeleteCommand {

	private Set<Object> allElementsToDelete;
	private ExecutionManager executionManager2;

	/**
	 * @param executionManager
	 * @param selection
	 * @param ensureTransaction
	 * @param confirmDelete
	 * @param longOperationEvents
	 */
	public ModelOnlyFilteringDeleteCommand(ExecutionManager executionManager, Collection<?> selection,
			boolean ensureTransaction, boolean confirmDelete, boolean longOperationEvents) {
		super(executionManager, selection, ensureTransaction, confirmDelete, longOperationEvents);
		executionManager2 = executionManager;
	}

	/**
	 * Finds _all_ elements that will be deleted when the command is executed.
	 * NOTES: a. Two layers of business logic are controlling the result of this
	 * operation: 1. The delete helper is used to expand the initial selection
	 * 2. Business rules defined in PreDeleteStructureCommand b. All containment
	 * children of deleted elements are explicitly contained in the result set.
	 */
	@Override
	public Set<?> getAllElementsToDelete() {
		if (allElementsToDelete == null) {
			// Get a new handler.
			HashSet<Object> result = new HashSet<Object>();
			PreDeleteHandler handler = new PreDeleteHandler();

			// Call predeletion command.
			Command preDeletion = new ModelOnlyPreDeleteStructureCommand(executionManager2.getEditingDomain(),
					getExpandedSelection(), handler);
			if (preDeletion.canExecute()) {
				preDeletion.execute();
			}

			for (Notification notification : handler.notifications) {
				Object notifier = notification.getNotifier();
				if (notifier instanceof EObject) {
					// Get old value (ie removed one).
					Object oldValue = notification.getOldValue();
					int notificationType = notification.getEventType();
					switch (notificationType) {
					// Set case.
					// Handle it as a remove, as long as there is a null new
					// value (and a not null old one, but that part is
					// tested within the remove case
					// directly).
					case Notification.SET:
						if (null != notification.getNewValue()) {
							break;
						}
						//$FALL-THROUGH$
					case Notification.REMOVE:
						if (oldValue instanceof EObject) {
							boolean handleNotification = false;
							try {
								EReference feature = EReference.class.cast(notification.getFeature());
								handleNotification = feature.isContainment();
							} catch (ClassCastException exception) {
								// Could not tell feature, add notification
								// whatever it might be.
								handleNotification = true;
							}
							if (handleNotification) {
								// Add the deleted element.
								EObject deletedObject = (EObject) oldValue;
								result.add(deletedObject);
								// Filter out children of non Melody model
								// elements as DRepresentation for instance.
								if (CapellaResourceHelper.isSemanticElement(deletedObject)) {
									// Add the deleted element subtree.
									TreeIterator<EObject> allChildrenOfDeletedObject = deletedObject.eAllContents();
									while (allChildrenOfDeletedObject.hasNext()) {
										EObject child = allChildrenOfDeletedObject.next();
										result.add(child);
									}
								}
							}
						}
						break;
					default:
						break;
					}
				}
			}
			handler.dispose();
			allElementsToDelete = Collections.unmodifiableSet(result);
		}
		return allElementsToDelete;
	}

	private class ModelOnlyPreDeleteStructureCommand extends PreDeleteStructureCommand {

		private PreDeleteHandler handler;

		/**
		 * @param editingDomain
		 * @param elements
		 * @param deleteParts
		 * @param handler
		 */
		public ModelOnlyPreDeleteStructureCommand(EditingDomain editingDomain, Collection<?> elements,
				PreDeleteHandler handler) {
			super(editingDomain, elements, handler);
			this.handler = handler;
		}

		/**
		 * {@inheritDoc}
		 */
		@SuppressWarnings("unchecked")
		@Override
		protected void doPrepare() {
			append(new PreRemoveCommand((Collection<EObject>) getElementsToDelete(), handler));
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		protected Command doDeleteStructure(EObject sourceObject) {
			return new ModelOnlyPreDeleteStructureCommand(getEditingDomain(), Collections.singletonList(sourceObject),
					handler);
		}
	}
}