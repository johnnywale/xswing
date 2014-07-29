package com.jovx.xswing.model;

import java.lang.reflect.Method;

public class ModelConfig {
	private String header;
	private Method getMethod;
	private Method setMethod;
	private String fieldName;
	private String sort;
	private Class renderClazz;
	private boolean editable = false;

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public Method getGetMethod() {
		return getMethod;
	}

	public void setGetMethod(Method getMethod) {
		this.getMethod = getMethod;
	}

	public Method getSetMethod() {
		return setMethod;
	}

	public void setSetMethod(Method setMethod) {
		this.setMethod = setMethod;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Class getRenderClazz() {
		return renderClazz;
	}

	public void setRenderClazz(Class renderClazz) {
		this.renderClazz = renderClazz;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

}
