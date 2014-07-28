package com.jovx.swing.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class EventService extends HashMap<Class, List<EventListener>> {

	public void fireEvent(Object o) {

		Set<Class> classes = this.keySet();
		for (Class clazz : classes) {
			if (clazz.isAssignableFrom(o.getClass())) {
				for (EventListener eventListener : get(clazz)) {
					eventListener.onEvent(o);
				}

			}
		}

	}

	public <T> void register(Class<T> clazz, EventListener<T> eventListener) {
		List<EventListener> x = super.get(clazz);
		if (x == null) {
			x = new ArrayList<EventListener>();
			super.put(clazz, x);
		}
		x.add(eventListener);
		System.out.println(this);
	}

}
