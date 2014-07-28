package com.jovx.swing.log;

import com.jovx.swing.event.EventListener;
import com.jovx.swing.factory.XSwingFactory;

public class SimpleLog {
	public static void init() {
		XSwingFactory.getInstance().findDefaultEventService()
				.register(Object.class, new EventListener() {
					@Override
					public void onEvent(Object o) {
						System.out.println("receive " + o);

					}
				});
	}
}
