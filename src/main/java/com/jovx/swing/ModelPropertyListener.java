package com.jovx.swing;

public interface ModelPropertyListener<T> {

	void onValueChanged(T obj);

	void onAdd(T obj);

	void onSequenceChanged();
}
