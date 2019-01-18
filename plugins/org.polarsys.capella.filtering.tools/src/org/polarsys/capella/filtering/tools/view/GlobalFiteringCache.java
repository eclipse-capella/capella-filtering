package org.polarsys.capella.filtering.tools.view;

import java.util.HashMap;
import java.util.Map;

import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.filtering.AbstractFilteringResult;

public class GlobalFiteringCache {
  @SuppressWarnings("unused")
  private static final long serialVersionUID = 4483023425603579476L;

  private boolean isEnabled;
  private Map<Project, AbstractFilteringResult> cache = new HashMap<>();

  public GlobalFiteringCache() {
    // TODO Auto-generated constructor stub
  }

  public void setCurrentFilteringResult(Project project, AbstractFilteringResult filteringResult) {
    cache.put(project, filteringResult);

  }

  public void clear() {
    cache.clear();

  }

  public AbstractFilteringResult get(Project project) {
    return cache.get(project);
  }

  public void remove(Project project) {
    cache.remove(project);
  }

  public boolean isEnabled() {
    return isEnabled;
  }

  public void enable() {
    this.isEnabled = true;
  }

  public void disable() {
    this.isEnabled = false;
  }

  public void setEnabled(boolean enabled) {
    this.isEnabled = enabled;

  }

  @Override
  public String toString() {
    return cache.toString();
  }

}
