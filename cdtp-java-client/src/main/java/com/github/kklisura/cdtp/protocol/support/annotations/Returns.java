package com.github.kklisura.cdtp.protocol.support.annotations;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates return property.
 *
 * @author Kenan Klisura
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {METHOD})
public @interface Returns {
  String value();
}
