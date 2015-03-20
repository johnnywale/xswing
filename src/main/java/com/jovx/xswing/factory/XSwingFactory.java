package com.jovx.xswing.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jovx.xswing.config.IFactoryConfig;
import com.jovx.xswing.event.EventContext;
import com.jovx.xswing.event.EventListener;
import com.jovx.xswing.event.IEventService;
import com.jovx.xswing.event.ModelAddEvent;
import com.jovx.xswing.event.ModelDeleteEvent;
import com.jovx.xswing.event.ModelFieldValueChangedEvent;
import com.jovx.xswing.event.ModelFullUpdateEvent;
import com.jovx.xswing.event.SimpleEventService;
import com.jovx.xswing.persis.PersisEventListener;

public class XSwingFactory {
	private static XSwingFactory swingFactory;
	private IFactoryConfig factoryConfig;
	public Map<String, IEventService> listeners = new HashMap<String, IEventService>();
	public static final String DEFAULT = "DEFAULT";

	private XSwingFactory() {

	}

	public <T> T forInstance(String key) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		String value = factoryConfig.getInstance(key);
		if (value != null) {
			return (T) Class.forName(value).newInstance();
		} else {
			throw new RuntimeException("Could not get instance from config "
					+ key);
		}
	}

	private PersisEventListener persisEventListener;

	public IEventService findDefaultEventService() {
		IEventService eventService = findEventService(DEFAULT);

		return eventService;
	}

	public static final String EVENT_KEY = "EVENT";

	public IEventService findEventService(String key) {

		IEventService def = listeners.get(key);
		if (def == null) {

			def = factoryConfig.getServiceConfig(EVENT_KEY, key);
			if (def == null) {
				def = new SimpleEventService();
			}
			listeners.put(key, def);
		}
		return def;
	}

	public static XSwingFactory getInstance() {
		if (swingFactory == null) {
			throw new RuntimeException("Should init with config");
		}
		return swingFactory;
	}

	public static XSwingFactory initInstanceWithConfig(
			IFactoryConfig factoryConfig) throws Throwable {
		if (swingFactory != null) {
			throw new RuntimeException("Should init with config");
		}
		swingFactory = new XSwingFactory();
		swingFactory.init(factoryConfig);
		return swingFactory;

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

	private static final String PERSIS_KEY = "PERSIS";

	private void init(IFactoryConfig factoryConfig) throws Throwable {
		this.factoryConfig = factoryConfig;
		this.persisEventListener = forInstance(PERSIS_KEY);

		IEventService defaultEventService = findDefaultEventService();
		defaultEventService.register(ModelAddEvent.class,
				new EventListener<ModelAddEvent>() {

					@Override
					public void onEvent(ModelAddEvent o,
							EventContext eventContext) {
						persisEventListener.onModelAddEvent(o);

					}

				});

		defaultEventService.register(ModelFieldValueChangedEvent.class,
				new EventListener<ModelFieldValueChangedEvent>() {

					@Override
					public void onEvent(ModelFieldValueChangedEvent o,
							EventContext eventContext) {
						persisEventListener.onModelFieldValueChangedEvent(o);

					}

				});
		defaultEventService.register(ModelDeleteEvent.class,
				new EventListener<ModelDeleteEvent>() {

					@Override
					public void onEvent(ModelDeleteEvent o,
							EventContext eventContext) {
						persisEventListener.onModelDeleteEvent(o);

					}

				});
		defaultEventService.register(ModelFullUpdateEvent.class,
				new EventListener<ModelFullUpdateEvent>() {

					@Override
					public void onEvent(ModelFullUpdateEvent o,
							EventContext eventContext) {
						persisEventListener.onModelFullUpdateEvent(o);

					}

				});
	}

	public static <T> ModelDeleteEvent<T> getSimpleDeleteEvent(T x) {

		ModelDeleteEvent<T> modelDeleteEvent = new ModelDeleteEvent<T>();
		modelDeleteEvent.setInstanceClass((Class<T>) x.getClass());
		List<T> missionCells = new ArrayList<T>();
		missionCells.add((T) x);
		modelDeleteEvent.setChangedList(missionCells);
		return modelDeleteEvent;
	}

	public static IEventService fireDefault() {
		return getInstance().findDefaultEventService();
	}

	public static void fireEvent(Object o) {
		getInstance().findDefaultEventService().fireEvent(o);
	}

}
