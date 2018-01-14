package com.github.kklisura.cdtp.protocol.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Optional annotation.
 *
 * @author Kenan Klisura
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ FIELD, METHOD, PARAMETER, TYPE })
public @interface Optional {
}
