package com.stee.ims.monitoring.anno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventService extends HashMap<Class, List<EventListener>> {

	public void fireEvent(Object o) {
		List<EventListener> x = get(o.getClass());
		if (x != null) {
			for (EventListener eventListener : x) {
				eventListener.onEvent(o);
			}
		}
		System.out.println("fireEvent " + o);
	}

	public <T> void put(Class<T> clazz, EventListener<T> eventListener) {
		List<EventListener> x = super.get(clazz);
		if (x == null) {
			x = new ArrayList<EventListener>();
			super.put(clazz, x);
		}
		x.add(eventListener);
	}

}
