package com.jovx.swing.persis;

import com.jovx.swing.event.ModelAddEvent;
import com.jovx.swing.event.ModelDeleteEvent;
import com.jovx.swing.event.ModelFieldValueChangedEvent;

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
