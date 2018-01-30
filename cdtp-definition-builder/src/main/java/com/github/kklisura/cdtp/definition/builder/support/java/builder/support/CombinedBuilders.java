package com.github.kklisura.cdtp.definition.builder.support.java.builder.support;

import com.github.javaparser.utils.SourceRoot;
import com.github.kklisura.cdtp.definition.builder.support.java.builder.Builder;
import java.io.IOException;
import java.util.List;

/**
 * Combines multiple builders in a single builder.
 *
 * @author Kenan Klisura
 */
public class CombinedBuilders implements Builder {
  private List<Builder> builderList;

  /**
   * Instantiates a new Combined builders with list of builders to combine.
   *
   * @param builderList Builders.
   */
  public CombinedBuilders(List<Builder> builderList) {
    this.builderList = builderList;
  }

  /**
   * Gets builder list.
   *
   * @return Builder list.
   */
  public List<Builder> getBuilderList() {
    return builderList;
  }

  @Override
  public void build(SourceRoot sourceRoot) throws IOException {
    for (Builder builder : builderList) {
      builder.build(sourceRoot);
    }
  }
}
