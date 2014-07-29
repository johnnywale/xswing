package com.jovx.xswing.model.source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelSource {

	private static ModelSource modelSource;

	private Map<Class, MsDataLoader> dataLoaders = new HashMap<Class, MsDataLoader>();

	public <T> List<T> getModelSource(Class source) {
		MsDataLoader s = dataLoaders.get(source);
		if (s != null) {
			return s.loadAll();
		} else {
			return new ArrayList();
		}
	}

	public <T> List<T> getModelSource(Class source, String value) {
		MsDataLoader s = dataLoaders.get(source);
		if (s != null) {
			return s.find(value);
		} else {
			return new ArrayList();
		}
	}

	public static ModelSource getInstance() {
		if (modelSource == null) {
			modelSource = new ModelSource();
		}
		return modelSource;
	}

}
