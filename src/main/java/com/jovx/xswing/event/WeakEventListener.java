package com.jovx.xswing.event;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class WeakEventListener<T> implements EventListener<T> {
	private Reference<EventListener<T>> reference;

	public WeakEventListener(EventListener<T> l) {
		reference = new WeakReference<EventListener<T>>(l);
	}

	@Override
	public void onEvent(T o, EventContext eventContext) {

		if (reference == null) {
			return;
		}
		EventListener<T> l = reference.get();
		if (l != null) {
			l.onEvent(o, eventContext);
		} else {
			reference = null;
		}

	}
}
