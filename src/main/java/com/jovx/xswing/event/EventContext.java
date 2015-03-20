package com.jovx.xswing.event;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EventContext {
	private Date time = new Date();
	private Map<String, Object> eventAttribute;

	public Object getAttribute(String key) {
		if (eventAttribute == null) {
			return null;
		} else {
			return eventAttribute.get(key);
		}
	}

	public synchronized void addAttribute(String key, Object value) {
		if (eventAttribute == null) {
			eventAttribute = new HashMap<String, Object>();
		}
		eventAttribute.put(key, value);
	}

	public Map<String, Object> getEventAttribute() {
		return eventAttribute;
	}

	public void setEventAttribute(Map<String, Object> eventAttribute) {
		this.eventAttribute = eventAttribute;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
