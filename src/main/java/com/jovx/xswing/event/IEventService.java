package com.jovx.xswing.event;

public interface IEventService {

	public abstract void fireEvent(Object o);

	public abstract <T> void registerWeak(Class<T> clazz,
			EventListener<T> eventListener);

	public abstract void registerListener(Object o);

	public abstract void unRegister(Object o);

	public abstract <T> void unRegister(Class<T> clazz,
			EventListener<T> eventListener);

	public abstract <T> void register(Class<T> clazz,
			EventListener<T> eventListener);

	<T> void onEvent(T o, EventContext eventContext);

	public abstract void fireEventBySequence(Object o);

	public abstract void fireEvent(Object heartBeat, EventContext eventContext);

}