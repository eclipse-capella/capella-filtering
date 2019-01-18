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
package org.polarsys.capella.core.validation.commandline;

import org.polarsys.capella.core.commandline.core.CommandLineArgumentHelper;

/**
 */
public class FilteringArgumentHelper extends CommandLineArgumentHelper {

  private String filteringResultId;

  /**
   * {@inheritDoc}
   */
  @Override
  public void parseArgs(String[] args) {
    super.parseArgs(args);

    // parse validation specific args
    for (int i = 0; i < args.length; i++) {
      String arg = args[i].toLowerCase();

      if (FilteringCommandLineConstants.FILTERING_RESULT_ID.equalsIgnoreCase(arg)) {
        filteringResultId = args[++i];

      }
    }
  }

  /**
   * @return the filteringResultId
   */
  public String getFilteringResultId() {
    return filteringResultId;
  }

}
