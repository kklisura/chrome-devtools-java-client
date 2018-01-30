package com.github.kklisura.cdtp.definition.builder.support.java.builder.support;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Method param.
 *
 * @author Kenan Klisura
 */
@Getter
@Setter
public class MethodParam {
  private String type;

  private String name;

  private List<Annotation> annotations;

  /** Method param annotation. Supports both Marker and simple string argument annotation. */
  public static class Annotation {
    private String name;
    private String value;

    /**
     * Instantiates a new Method param annotation.
     *
     * @param name Annotation name.
     * @param value Annotation value for simple string argument annotation.
     */
    public Annotation(String name, String value) {
      this.name = name;
      this.value = value;
    }

    /**
     * Instantiates a new Method param annotation.
     *
     * @param name Annotation name.
     */
    public Annotation(String name) {
      this.name = name;
    }

    /**
     * Gets name.
     *
     * @return Annotation name.
     */
    public String getName() {
      return name;
    }

    /**
     * Gets annotation value.
     *
     * @return Annotation value.
     */
    public String getValue() {
      return value;
    }
  }
}
