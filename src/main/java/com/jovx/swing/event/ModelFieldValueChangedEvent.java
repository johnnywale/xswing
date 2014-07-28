package com.jovx.swing.event;

public class ModelFieldValueChangedEvent<T> implements ModelListener<T> {
	private T model;
	private Object now;
	private Object before;
	private String fieldName;
	private Class<T> instanceClass;

	public Class<T> getInstanceClass() {
		return instanceClass;
	}

	public void setInstanceClass(Class<T> instanceClass) {
		this.instanceClass = instanceClass;
	}

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
