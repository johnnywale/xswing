package com.jovx.xswing.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanModelInfoBuilder<T> implements IModelInfoBuilder<T> {

	private List<ModelConfig> columnConfigs = new ArrayList<ModelConfig>();
	private Class<T> modelType;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jovx.xswing.model.IModelInfoBuilder#invokeSet(java.lang.String,
	 * java.lang.Object, T)
	 */
	@Override
	public void invokeSet(String field, Object value, T instance) {
		try {
			getModelConfigByName(field).getSetMethod().invoke(instance, value);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jovx.xswing.model.IModelInfoBuilder#getModelConfigByName(java.lang
	 * .String)
	 */
	@Override
	public ModelConfig getModelConfigByName(String name) {
		for (ModelConfig modelConfig : columnConfigs) {
			if (modelConfig.getFieldName().equals(name)) {
				return modelConfig;
			}
		}
		throw new RuntimeException("Fail to get config by field " + name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jovx.xswing.model.IModelInfoBuilder#getColumnConfigs()
	 */
	@Override
	public List<ModelConfig> getColumnConfigs() {
		return columnConfigs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jovx.xswing.model.IModelInfoBuilder#getModelType()
	 */
	@Override
	public Class<T> getModelType() {
		return modelType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jovx.xswing.model.IModelInfoBuilder#setModelType(java.lang.Class)
	 */
	@Override
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

	public BeanModelInfoBuilder(Class<T> clazz) {
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
			ModelConfig columnConfig = new ModelConfig();

			String name = field.getName();
			columnConfig.setFieldName(name);
			String upperName;

			if (name.length() > 1) {

				if (Character.isLowerCase(name.charAt(0))
						&& !Character.isLowerCase(name.charAt(1))) {
					upperName = name;
				} else {
					upperName = Character.toUpperCase(name.charAt(0))
							+ name.substring(1);

				}
			} else {
				upperName = name.toUpperCase();
			}

			String methodName = "get" + upperName;
			String setName = "set" + upperName;
			columnConfig.setHeader(addBetweenUpperCase(upperName, " "));

			try {
				Method method = modelType.getDeclaredMethod(methodName);
				if (method.isAnnotationPresent(ModelField.class)) {
					ModelField modelField = method
							.getAnnotation(ModelField.class);
					if (modelField.ignore()) {
						continue;
					}

					String backEndField = modelField.fieldName();
					if (modelField.fieldName().length() > 0) {
						columnConfig.setSort(backEndField);
					} else {
						columnConfig.setSort(name);
					}
					columnConfig.setRenderClazz(modelField.renderClass());
					columnConfig.setEditable(modelField.editable());
					columnConfig.setSortable(modelField.sortable());
					columnConfig.setProtect(modelField.protect());

				} else {
					columnConfig.setEditable(true);
					columnConfig.setSort(name);
					columnConfig.setRenderClazz(field.getType());
				}
				columnConfig.setGetMethod(method);
				Method[] mmm = modelType.getDeclaredMethods();
				for (Method m : mmm) {
					if (m.getName().equals(setName)) {
						columnConfig.setSetMethod(m);
					}
				}
				columnConfigs.add(columnConfig);

			} catch (Throwable e) {

				e.printStackTrace();
			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jovx.xswing.model.IModelInfoBuilder#getEditable(int)
	 */
	@Override
	public boolean getEditable(int columnIndex) {
		return columnConfigs.get(columnIndex).isEditable();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jovx.xswing.model.IModelInfoBuilder#getGetMethod(int)
	 */
	@Override
	public Method getGetMethod(int j) {
		return columnConfigs.get(j).getGetMethod();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jovx.xswing.model.IModelInfoBuilder#getSort(int)
	 */
	@Override
	public String getSort(int i) {
		return columnConfigs.get(i).getSort();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jovx.xswing.model.IModelInfoBuilder#getHeader(int)
	 */
	@Override
	public String getHeader(int i) {

		return columnConfigs.get(i).getHeader();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jovx.xswing.model.IModelInfoBuilder#getRenderClazz(int)
	 */
	@Override
	public Class getRenderClazz(int i) {
		return columnConfigs.get(i).getRenderClazz();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jovx.xswing.model.IModelInfoBuilder#getSetMethod(int)
	 */
	@Override
	public Method getSetMethod(int j) {
		return columnConfigs.get(j).getSetMethod();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jovx.xswing.model.IModelInfoBuilder#getColumnCount()
	 */
	@Override
	public int getColumnCount() {

		return columnConfigs.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jovx.xswing.model.IModelInfoBuilder#getFieldName(int)
	 */
	@Override
	public String getFieldName(int j) {

		return columnConfigs.get(j).getFieldName();
	}
}
