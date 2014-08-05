package com.jf.commons.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Author {
	/**
	 * name of author
	 */
	public String name() default "";
}
