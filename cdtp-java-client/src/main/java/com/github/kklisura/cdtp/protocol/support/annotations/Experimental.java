package com.github.kklisura.cdtp.protocol.support.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Experimental annotation type.
 *
 * @author Kenan Klisura
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ FIELD, METHOD, PARAMETER, TYPE })
public @interface Experimental {
}
