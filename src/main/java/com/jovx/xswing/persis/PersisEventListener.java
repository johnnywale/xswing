package com.jovx.xswing.persis;

import com.jovx.xswing.event.ModelAddEvent;
import com.jovx.xswing.event.ModelDeleteEvent;
import com.jovx.xswing.event.ModelFieldValueChangedEvent;

public interface PersisEventListener {
	public <T> void onModelAddEvent(ModelAddEvent<T> modelAddEvent);

	public <T> void onModelDeleteEvent(ModelDeleteEvent<T> modelDeleteEvent);

	public <T> void onModelFieldValueChangedEvent(
			ModelFieldValueChangedEvent<T> modelFieldValueChangedEvent);
}
