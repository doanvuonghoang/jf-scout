package com.jf.commons.annotations;

public class Annotations {
	public static String getDescriptionContent(String className) {
		try {
			Class<?> cls = Class.forName(className);

			return getDescriptionContent(cls);
		} catch (ClassNotFoundException e) {
			return "";
		}
	}

	public static String getDescriptionContent(Class<?> cls) {
		try {
			Description a = cls.getAnnotation(Description.class);
			if (a != null) {
				return a.content();
			}
		} catch (Exception e) {
		}
		return "";
	}
}
