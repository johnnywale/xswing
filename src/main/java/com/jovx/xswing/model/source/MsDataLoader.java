package com.jovx.xswing.model.source;

import java.util.List;

public interface MsDataLoader<T> {
	public List<T> loadAll();

	public T getByInstance(T t);

	public List<T> find(String value);

}
