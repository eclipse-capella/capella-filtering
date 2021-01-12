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

import org.polarsys.capella.common.application.ArgumentsHelper;
import org.polarsys.capella.core.commandline.core.CommandLineArgumentHelper;

public class FilteringArgumentHelper extends CommandLineArgumentHelper {

  private String filteringResultId;
  private String derivationProjectName;

  @Override
  public void parseArgs(String[] args) {
    super.parseArgs(args);

    ArgumentsHelper helper = ArgumentsHelper.getInstance();
    filteringResultId = helper.getString(FilteringCommandLineConstants.FILTERING_RESULT_ID);
    derivationProjectName = helper.getString(FilteringCommandLineConstants.DERIVATION_PROJECT_NAME);
  }

  public String getFilteringResultId() {
    return filteringResultId;
  }

  public String getDerivationProjectName() {
    return derivationProjectName;
  }

}
