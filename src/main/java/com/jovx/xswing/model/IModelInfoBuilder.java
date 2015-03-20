package com.jovx.xswing.model;

import java.lang.reflect.Method;
import java.util.List;

public interface IModelInfoBuilder<T> {

	public abstract void invokeSet(String field, Object value, T instance);

	public abstract ModelConfig getModelConfigByName(String name);

	public abstract List<ModelConfig> getColumnConfigs();

	public abstract Class<T> getModelType();

	public abstract void setModelType(Class<T> modelType);

	public abstract boolean getEditable(int columnIndex);

	public abstract Method getGetMethod(int j);

	public abstract String getSort(int i);

	public abstract String getHeader(int i);

	public abstract Class getRenderClazz(int i);

	public abstract Method getSetMethod(int j);

	public abstract int getColumnCount();

	public abstract String getFieldName(int j);

}