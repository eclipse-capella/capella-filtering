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
package org.polarsys.capella.filtering.tools.utils.ui;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.polarsys.capella.filtering.FilteringResults;

/**
 * FilteringResults Content Provider
 */
public class FilteringResultsContentProvider implements IStructuredContentProvider, ITreeContentProvider {

	private FilteringResults filteringResults;

	public FilteringResultsContentProvider(FilteringResults filteringResults) {
		super();
		this.filteringResults = filteringResults;
	}

	public Set<Object> getAllContents() {
		Set<Object> contents = new HashSet<Object>();
		if ((filteringResults != null) && (filteringResults.getFilteringResults() != null)) {
			for (Object content : filteringResults.getFilteringResults()) {
				contents.add(content);
			}
		}
		return contents;
	}

	public void dispose() {
		// Do nothing
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// Do nothing
	}

	public Object[] getElements(Object inputElement) {
		return getAllContents().toArray();
	}

	public Object[] getChildren(Object parentElement) {
		return null;
	}

	public Object getParent(Object element) {
		return null;
	}

	public boolean hasChildren(Object element) {
		return false;
	}

}
