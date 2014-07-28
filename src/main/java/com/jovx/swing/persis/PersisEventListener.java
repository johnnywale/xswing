package com.jovx.swing.persis;

import com.jovx.swing.event.ModelAddEvent;
import com.jovx.swing.event.ModelDeleteEvent;
import com.jovx.swing.event.ModelFieldValueChangedEvent;

public interface PersisEventListener {
	public <T> void onModelAddEvent(ModelAddEvent<T> modelAddEvent);

	public <T> void onModelDeleteEvent(ModelDeleteEvent<T> modelDeleteEvent);

	public <T> void onModelFieldValueChangedEvent(
			ModelFieldValueChangedEvent<T> modelFieldValueChangedEvent);
}
