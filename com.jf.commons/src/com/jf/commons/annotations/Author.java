package com.jf.commons.annotations;

public @interface Author {
	/**
	 * name of author
	 */
	public String name() default "";
}
