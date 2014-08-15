package com.jovx.xswing.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModelField {
	public abstract String fieldName() default "";

	public abstract String viewName() default "";

	public abstract boolean editable() default true;

	public abstract boolean ignore() default false;

	public abstract Class renderClass() default Object.class;
}
