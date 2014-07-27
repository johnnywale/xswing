package com.jovx.swing;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ModelInfoBuilder<T> {

	private List<ColumnConfig> columnConfigs = new ArrayList<ColumnConfig>();

	private Class<T> modelType;

	public Class<T> getModelType() {
		return modelType;
	}

	public void setModelType(Class<T> modelType) {
		this.modelType = modelType;
	}

	public static String addBetweenUpperCase(String name, String charts) {
		StringBuffer buf = new StringBuffer(name);
		for (int i = 1; i < buf.length() - 1; i++) {
			if (Character.isLowerCase(buf.charAt(i - 1))
					&& Character.isUpperCase(buf.charAt(i))
					&& Character.isLowerCase(buf.charAt(i + 1))) {
				buf.insert(i++, charts);
			}
		}
		return buf.toString();
	}

	public ModelInfoBuilder(Class<T> clazz) {
		modelType = clazz;
		Field[] methods = modelType.getDeclaredFields();
		Class parent = modelType.getSuperclass();
		if (parent != null && !parent.equals(Object.class)) {
			Field[] fm = parent.getDeclaredFields();
			Field[] result = new Field[methods.length + fm.length];
			System.arraycopy(methods, 0, result, 0, methods.length);
			System.arraycopy(fm, 0, result, methods.length, fm.length);
		}
		for (Field field : methods) {
			ColumnConfig columnConfig = new ColumnConfig();
			columnConfigs.add(columnConfig);
			String name = field.getName();
			columnConfig.setFieldName(name);
			String upperName = Character.toUpperCase(name.charAt(0))
					+ name.substring(1);
			String methodName = "get" + upperName;
			String setName = "set" + upperName;
			columnConfig.setHeader(addBetweenUpperCase(upperName, " "));

			try {
				Method method = modelType.getDeclaredMethod(methodName);
				if (method.isAnnotationPresent(DtoField.class)) {
					DtoField dtoField = method.getAnnotation(DtoField.class);
					String backEndField = dtoField.fieldName();
					if (dtoField.fieldName().length() > 0) {
						columnConfig.setSort(backEndField);
					} else {
						columnConfig.setSort(name);
					}
					columnConfig.setRenderClazz(dtoField.renderClass());
					columnConfig.setEditable(dtoField.editable());
				} else {
					columnConfig.setEditable(true);
					columnConfig.setSort(name);
					columnConfig.setRenderClazz(Object.class);
				}
				columnConfig.setGetMethod(method);

				Method[] mmm = modelType.getDeclaredMethods();
				for (Method m : mmm) {
					if (m.getName().equals(setName)) {
						columnConfig.setSetMethod(m);
					}
				}

			} catch (Throwable e) {

				e.printStackTrace();
			}

		}
	}

	public boolean getEditable(int columnIndex) {
		return columnConfigs.get(columnIndex).isEditable();
	}

	public Method getGetMethod(int j) {
		return columnConfigs.get(j).getGetMethod();
	}

	public String getSort(int i) {
		return columnConfigs.get(i).getSort();
	}

	public String getHeader(int i) {

		return columnConfigs.get(i).getHeader();
	}

	public Class getRenderClazz(int i) {
		return columnConfigs.get(i).getRenderClazz();
	}

	public Method getSetMethod(int j) {
		return columnConfigs.get(j).getSetMethod();
	}

	public int getColumnCount() {

		return columnConfigs.size();
	}

	public String getFieldName(int j) {

		return columnConfigs.get(j).getFieldName();
	}
}
