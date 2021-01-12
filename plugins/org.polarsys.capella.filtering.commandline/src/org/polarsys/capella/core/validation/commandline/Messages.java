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
package org.polarsys.capella.core.validation.commandline;

import org.eclipse.osgi.util.NLS;

/**
 */
public class Messages extends NLS {

  public static String RESOURCE_PREFIX = "platform:/resource/";
  public static String FILTERING_RESULT_ID_MANDATORY = "Filtering result id argument is mandatory!";

  public static String DERIVATION_PROJECTS_ALREADY_EXISTS = "Project [{0}] already exists!";
  public static String DERIVATION_PROJECT_COMPUTED_NAME = "Project [{0}] will contain the derivation result.";

  public static String PROJECT_CONTAINS_NO_AIRD = "Project [{0}] does not contain any aird resources!";
  public static String MULTIPLE_INPUT_PROJECTS_DETECTED = "Multiple input projects detected, only {0} will be considered.";
  public static String NO_SEMANTIC_MODEL_FOUND = "No semantic model found!";
  public static String INVALID_FILTERING_RESULT_ID = "Invalid filtering result id!";

  public static String FILTERING_RESULT_ID_CATEGORY = "mandatory";
  public static String FILTERING_RESULT_ID_DESCRIPTION = "defines the id of the Filtering Result model element used for the derivation process";
  public static String DERIVATION_PROJECT_CATEGORY = "optional";
  public static String DERIVATION_PROJECT_DESCRIPTION = "defines the derivation project name, if absent then the Filtering Result name will be used instead, in case of name conflicts the project name is incremented";
}
