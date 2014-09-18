package com.jovx.xswing.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.jovx.xswing.exception.GcException;

public class EventService extends ConcurrentHashMap<Class, List<EventListener>> {

	public void fireEvent(Object o) {

		List<EventListener> eventListeners = new ArrayList<EventListener>();
		Set<Class> classes = this.keySet();
		for (Class clazz : classes) {
			if (clazz.isAssignableFrom(o.getClass())) {
				List<EventListener> lis = get(clazz);
				for (EventListener eventListener : get(clazz)) {
					try {
						eventListener.onEvent(o);
					} catch (GcException throwable) {
						eventListeners.add(eventListener);
					} catch (Throwable throwable) {
						throwable.printStackTrace();
						System.out.println("error when public event");
					}
				}
				lis.removeAll(eventListeners);
				eventListeners.clear();

			}
		}

	}

	public <T> void registerWeak(Class<T> clazz, EventListener<T> eventListener) {
		register(clazz, new WeakEventListener<T>(eventListener));
	}

	public <T> void register(Class<T> clazz, EventListener<T> eventListener) {
		List<EventListener> x = super.get(clazz);
		if (x == null) {
			x = new ArrayList<EventListener>();
			super.put(clazz, x);
		}
		x.add(eventListener);
	}

}
