package com.jovx.xswing.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppEvent<T> {
	private boolean record;
	private boolean sequence;
	private List<String> channels = new ArrayList<String>();
	private Map<String, Object> eventAttribute;

	public Map<String, Object> getEventAttribute() {
		return eventAttribute;
	}

	public List<String> getChannels() {
		return channels;
	}

	public void addAttribute(String key, Object object) {
		if (eventAttribute == null) {
			eventAttribute = new HashMap<String, Object>();
		}
		eventAttribute.put(key, object);
	}

	public void addAttribute(Object object) {
		addAttribute(object.getClass().getName(), object);
	}

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void addChannel(String channel) {
		channels.add(channel);
	}

	public boolean isRecord() {
		return record;
	}

	public void setRecord(boolean record) {
		this.record = record;
	}

	public boolean isSequence() {
		return sequence;
	}

	public void setSequence(boolean sequence) {
		this.sequence = sequence;
	}

}
