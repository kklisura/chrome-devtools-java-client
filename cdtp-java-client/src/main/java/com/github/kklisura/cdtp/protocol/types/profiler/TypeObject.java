package com.github.kklisura.cdtp.protocol.types.profiler;

import com.github.kklisura.cdtp.protocol.support.annotations.Experimental;

/** Describes a type collected during runtime. */
@Experimental
public class TypeObject {

  private String name;

  /** Name of a type collected with type profiling. */
  public String getName() {
    return name;
  }

  /** Name of a type collected with type profiling. */
  public void setName(String name) {
    this.name = name;
  }
}
