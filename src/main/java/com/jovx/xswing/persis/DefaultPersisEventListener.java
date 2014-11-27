package com.jovx.xswing.persis;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.jovx.xswing.event.ModelAddEvent;
import com.jovx.xswing.event.ModelDeleteEvent;
import com.jovx.xswing.event.ModelFieldValueChangedEvent;
import com.jovx.xswing.event.ModelFullUpdateEvent;

public class DefaultPersisEventListener implements PersisEventListener {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());

	@Override
	public <T> void onModelAddEvent(ModelAddEvent<T> modelAddEvent) {
		logger.log(Level.FINE, "onModelAddEvent");

	}

	@Override
	public <T> void onModelDeleteEvent(ModelDeleteEvent<T> modelDeleteEvent) {
		logger.log(Level.FINE, "onModelDeleteEvent");

	}

	@Override
	public <T> void onModelFieldValueChangedEvent(
			ModelFieldValueChangedEvent<T> modelFieldValueChangedEvent) {
		logger.log(Level.FINE, "onModelFieldValueChangedEvent");

	}

	@Override
	public <T> void onModelFullUpdateEvent(ModelFullUpdateEvent<T> o) {
		logger.log(Level.FINE, "onModelFullUpdateEvent");
	}

}
 