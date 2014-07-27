package com.stee.ims.monitoring.anno;

public interface ModelPropertyListener<T> {

	void onValueChanged(T obj);

	void onAdd(T obj);

	void onSequenceChanged();
}
