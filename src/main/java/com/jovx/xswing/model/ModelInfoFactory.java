package com.jovx.xswing.model;

import java.util.HashMap;
import java.util.Map;

public class ModelInfoFactory {
	public static Map<Class, BeanModelInfoBuilder> maps = new HashMap<Class, BeanModelInfoBuilder>();
	
	
	
	
	public static <T> IModelInfoBuilder<T> forBuilder(Class<T> value) {
		BeanModelInfoBuilder<T> builder = maps.get(value);
		if (builder == null) {
			builder = new BeanModelInfoBuilder<T>(value);
			maps.put(value, builder);
		}
		return builder;

	}

}
