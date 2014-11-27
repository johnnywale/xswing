package com.jovx.xswing.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jovx.xswing.exception.GcException;

public class SimpleEventService extends
		ConcurrentHashMap<Class, List<EventListener>> implements IEventService {
	private Logger logger = Logger.getLogger(getClass().toString());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jovx.xswing.event.IEventService#fireEvent(java.lang.Object)
	 */
	@Override
	public void fireEvent(Object o) {

		List<EventListener> eventListeners = new ArrayList<EventListener>();
		Set<Class> classes = this.keySet();
		for (Class clazz : classes) {
			if (clazz.isAssignableFrom(o.getClass())) {
				List<EventListener> lis = get(clazz);
				for (EventListener eventListener : get(clazz)) {
					try {

						EventContext eventContext = new EventContext();
						if (o instanceof AppEvent) {
							AppEvent appEvent = (AppEvent) o;
							eventContext.setEventAttribute(appEvent
									.getEventAttribute());
						}
						eventListener.onEvent(o, eventContext);
					} catch (GcException throwable) {
						eventListeners.add(eventListener);
					} catch (Throwable throwable) {
						throwable.printStackTrace();
						logger.log(Level.SEVERE, "error when public event");
					}
				}
				lis.removeAll(eventListeners);
				eventListeners.clear();

			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jovx.xswing.event.IEventService#registerWeak(java.lang.Class,
	 * com.jovx.xswing.event.EventListener)
	 */
	@Override
	public <T> void registerWeak(Class<T> clazz, EventListener<T> eventListener) {
		register(clazz, new WeakEventListener<T>(eventListener));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jovx.xswing.event.IEventService#registerListener(java.lang.Object)
	 */
	@Override
	public void registerListener(Object o) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jovx.xswing.event.IEventService#unRegister(java.lang.Object)
	 */
	@Override
	public void unRegister(Object o) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jovx.xswing.event.IEventService#unRegister(java.lang.Class,
	 * com.jovx.xswing.event.EventListener)
	 */
	@Override
	public <T> void unRegister(Class<T> clazz, EventListener<T> eventListener) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jovx.xswing.event.IEventService#register(java.lang.Class,
	 * com.jovx.xswing.event.EventListener)
	 */
	@Override
	public <T> void register(Class<T> clazz, EventListener<T> eventListener) {
		List<EventListener> x = super.get(clazz);
		if (x == null) {
			x = new ArrayList<EventListener>();
			super.put(clazz, x);
		}
		x.add(eventListener);
	}

	@Override
	public void fireEventBySequence(Object o) {
		// TODO Auto-generated method stub

	}

}
