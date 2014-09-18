package com.jovx.xswing.factory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jovx.xswing.event.EventListener;
import com.jovx.xswing.event.EventService;
import com.jovx.xswing.event.ModelAddEvent;
import com.jovx.xswing.event.ModelDeleteEvent;
import com.jovx.xswing.event.ModelFieldValueChangedEvent;
import com.jovx.xswing.event.ModelFullUpdateEvent;
import com.jovx.xswing.persis.PersisEventListener;
import com.jovx.xswing.util.JAXBXMLHandler;

public class XSwingFactory {
	private static XSwingFactory swingFactory;

	public Map<String, EventService> listeners = new HashMap<String, EventService>();
	public static final String DEFAULT = "DEFAULT";

	private PersisEventListener persisEventListener;

	public EventService findDefaultEventService() {
		EventService eventService = findEventService(DEFAULT);
		// System.out.println(eventService);
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

	public static <T> void simpleAdd(T t) {
		ModelAddEvent<T> addEvent = new ModelAddEvent<T>();
		List<T> newList = new ArrayList<T>();
		newList.add(t);
		addEvent.setChangedList(newList);
		addEvent.setOriginalList(new ArrayList<T>());
		addEvent.setInstanceClass((Class<T>) t.getClass());
		addEvent.setCurrentList(newList);
		getInstance().findDefaultEventService().fireEvent(addEvent);
	}

	public static <T> void simpleModify(T t) {
		ModelFullUpdateEvent<T> addEvent = new ModelFullUpdateEvent<T>();
		List<T> newList = new ArrayList<T>();
		newList.add(t);
		addEvent.setChangedList(newList);
		addEvent.setOriginalList(new ArrayList<T>());
		addEvent.setInstanceClass((Class<T>) t.getClass());
		addEvent.setCurrentList(newList);
		getInstance().findDefaultEventService().fireEvent(addEvent);
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
			defaultEventService.register(ModelFullUpdateEvent.class,
					new EventListener<ModelFullUpdateEvent>() {

						@Override
						public void onEvent(ModelFullUpdateEvent o) {
							persisEventListener.onModelFullUpdateEvent(o);

						}

					});

		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> ModelDeleteEvent<T> getSimpleDeleteEvent(T x) {

		ModelDeleteEvent<T> modelDeleteEvent = new ModelDeleteEvent<T>();
		modelDeleteEvent.setInstanceClass((Class<T>) x.getClass());
		List<T> missionCells = new ArrayList<T>();
		missionCells.add((T) x);
		modelDeleteEvent.setChangedList(missionCells);
		return modelDeleteEvent;
	}
}
