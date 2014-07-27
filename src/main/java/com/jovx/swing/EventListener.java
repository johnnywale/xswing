package com.stee.ims.monitoring.anno;

public interface EventListener<T> {

	void onEvent(T o);

}
