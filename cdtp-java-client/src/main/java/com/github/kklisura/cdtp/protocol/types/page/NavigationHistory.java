package com.github.kklisura.cdtp.protocol.types.page;

import java.util.List;

public class NavigationHistory {

  private Integer currentIndex;

  private List<NavigationEntry> entries;

  /** Index of the current navigation history entry. */
  public Integer getCurrentIndex() {
    return currentIndex;
  }

  /** Index of the current navigation history entry. */
  public void setCurrentIndex(Integer currentIndex) {
    this.currentIndex = currentIndex;
  }

  /** Array of navigation history entries. */
  public List<NavigationEntry> getEntries() {
    return entries;
  }

  /** Array of navigation history entries. */
  public void setEntries(List<NavigationEntry> entries) {
    this.entries = entries;
  }
}
