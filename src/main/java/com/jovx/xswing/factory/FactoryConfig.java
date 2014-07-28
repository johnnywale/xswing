package com.jovx.xswing.factory;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FactoryConfig {
	private String persisEventListener;
	private String eventLogListener;

	public String getPersisEventListener() {
		return persisEventListener;
	}

	public void setPersisEventListener(String persisEventListener) {
		this.persisEventListener = persisEventListener;
	}

	public String getEventLogListener() {
		return eventLogListener;
	}

	public void setEventLogListener(String eventLogListener) {
		this.eventLogListener = eventLogListener;
	}

}
