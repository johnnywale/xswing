package com.jovx.xswing.factory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.jovx.xswing.event.EventListener;
import com.jovx.xswing.event.EventService;
import com.jovx.xswing.event.ModelAddEvent;
import com.jovx.xswing.event.ModelDeleteEvent;
import com.jovx.xswing.event.ModelFieldValueChangedEvent;
import com.jovx.xswing.persis.PersisEventListener;
import com.jovx.xswing.util.JAXBXMLHandler;

public class XSwingFactory {
	private static XSwingFactory swingFactory;

	public Map<String, EventService> listeners = new HashMap<String, EventService>();
	public static final String DEFAULT = "DEFAULT";

	private PersisEventListener persisEventListener;

	public EventService findDefaultEventService() {
		EventService eventService = findEventService(DEFAULT);
		System.out.println(eventService);
		return eventService;
	}

	public EventService findEventService(String key) {
		EventService def = listeners.get(key);
		if (def == null) {
			def = new EventService();
			listeners.put(key, def);
		}
		return def;
	}

	public static XSwingFactory getInstance() {
		if (swingFactory == null) {
			swingFactory = new XSwingFactory();
		}
		return swingFactory;
	}

	public static void main(String[] args) throws IOException, Throwable {

		FactoryConfig factoryConfig = new FactoryConfig();
		factoryConfig
				.setEventLogListener("com.jovx.swing.persis.DefaultPersisEventListener");
		factoryConfig
				.setPersisEventListener("com.jovx.swing.persis.DefaultPersisEventListener");
		JAXBXMLHandler.marshal(factoryConfig, new FileWriter(new File(
				"xswing.xml")));
	}

	public void initInstance(InputStream inputStream) {
		try {
			FactoryConfig factoryConfig = JAXBXMLHandler.unmarshal(inputStream,
					FactoryConfig.class);
			this.persisEventListener = (PersisEventListener) Class.forName(
					factoryConfig.getPersisEventListener().trim())
					.newInstance();

			EventService defaultEventService = findDefaultEventService();
			defaultEventService.register(ModelAddEvent.class,
					new EventListener<ModelAddEvent>() {

						@Override
						public void onEvent(ModelAddEvent o) {
							persisEventListener.onModelAddEvent(o);

						}

					});

			defaultEventService.register(ModelFieldValueChangedEvent.class,
					new EventListener<ModelFieldValueChangedEvent>() {

						@Override
						public void onEvent(ModelFieldValueChangedEvent o) {
							persisEventListener
									.onModelFieldValueChangedEvent(o);

						}

					});
			defaultEventService.register(ModelDeleteEvent.class,
					new EventListener<ModelDeleteEvent>() {

						@Override
						public void onEvent(ModelDeleteEvent o) {
							persisEventListener.onModelDeleteEvent(o);

						}

					});

		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}
