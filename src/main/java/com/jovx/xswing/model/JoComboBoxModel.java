package com.jovx.xswing.model;

import javax.swing.ComboBoxModel;

public class JoComboBoxModel<T> extends ListModel<T> implements ComboBoxModel {
	private T t;

	@Override
	public void setSelectedItem(Object anItem) {
		this.t = (T) anItem;

	}

	@Override
	public Object getSelectedItem() {

		return t;
	}

}
