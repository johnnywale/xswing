package com.jovx.xswing.config;

public interface IFactoryConfig {

	public abstract String getInstance(String key);

	public abstract <T> T getServiceConfig(String serviceName, String key);

}