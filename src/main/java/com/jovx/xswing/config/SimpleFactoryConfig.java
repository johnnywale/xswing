package com.jovx.xswing.config;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SimpleFactoryConfig implements IFactoryConfig {
	private Map<String, String> configs = new HashMap<String, String>();

	public Map<String, String> getConfigs() {
		return configs;
	}

	public void setConfigs(Map<String, String> configs) {
		this.configs = configs;
	}

	public void addConfig(String key, String value) {
		configs.put(key, value);
	}

	@Override
	public String getInstance(String key) {
		return configs.get(key);
	}

	@Override
	public <T> T getServiceConfig(String serviceName, String key) {
		String x = configs.get(serviceName + "-" + key);
		if (x != null) {
			try {
				return (T) Class.forName(x).newInstance();
			} catch (Throwable e) {
				new RuntimeException(e);
			}
		}
		return null;
	}

}
