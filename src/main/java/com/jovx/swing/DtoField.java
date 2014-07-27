package com.stee.ims.monitoring.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DtoField {
	public abstract String fieldName() default "";

	public abstract String viewName() default "";

	public abstract boolean editable() default true;

	public abstract Class renderClass() default Object.class;
}
