package com.jovx.swing;

public class ModelFieldValueChangedEvent<T> {
	private T model;
	private Object now;
	private Object before;
	private String fieldName;

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public Object getNow() {
		return now;
	}

	public void setNow(Object now) {
		this.now = now;
	}

	public Object getBefore() {
		return before;
	}

	public void setBefore(Object before) {
		this.before = before;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public String toString() {
		return "ModelFieldValueChangedEvent [model=" + model + ", now=" + now
				+ ", before=" + before + ", fieldName=" + fieldName + "]";
	}

}
