package com.jovx.xswing.log;

import java.util.logging.Logger;

import com.jovx.xswing.event.EventContext;
import com.jovx.xswing.event.EventListener;
import com.jovx.xswing.factory.XSwingFactory;

public class SimpleLog {

	private static Logger logger = Logger.getLogger("com.jovx.xswing.log");

	public static void init() {
		XSwingFactory.getInstance().findDefaultEventService()
				.register(Object.class, new EventListener() {
					@Override
					public void onEvent(Object o, EventContext eventContext) {
						logger.info("receive " + o);
					}
				});
	}
}
