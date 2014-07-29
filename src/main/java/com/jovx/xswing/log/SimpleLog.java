package com.jovx.xswing.log;

import com.jovx.xswing.event.EventListener;
import com.jovx.xswing.factory.XSwingFactory;

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
