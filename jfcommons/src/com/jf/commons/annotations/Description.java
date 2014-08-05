/**
 * 
 */
package com.jf.commons.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Hoàng
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {
	String content() default "";
}
