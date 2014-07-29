package com.jovx.xswing.persis;

import com.jovx.xswing.event.ModelAddEvent;
import com.jovx.xswing.event.ModelDeleteEvent;
import com.jovx.xswing.event.ModelFieldValueChangedEvent;

public class DefaultPersisEventListener implements PersisEventListener {

	@Override
	public <T> void onModelAddEvent(ModelAddEvent<T> modelAddEvent) {
		System.out.println("onModelAddEvent");

	}

	@Override
	public <T> void onModelDeleteEvent(ModelDeleteEvent<T> modelDeleteEvent) {
		System.out.println("onModelDeleteEvent");

	}

	@Override
	public <T> void onModelFieldValueChangedEvent(
			ModelFieldValueChangedEvent<T> modelFieldValueChangedEvent) {
		System.out.println("onModelFieldValueChangedEvent");

	}

}
