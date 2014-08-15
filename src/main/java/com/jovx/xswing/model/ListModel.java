package com.jovx.xswing.model;

import java.util.List;

import javax.swing.AbstractListModel;

public class ListModel<E> extends AbstractListModel {
	private List<E> datas;

	public List<E> getDatas() {
		return datas;
	}

	public void setDatas(List<E> datas) {
		this.datas = datas;
		this.fireContentsChanged(this, 0, 0);
	}

	@Override
	public E getElementAt(int i) {
		return datas.get(i);
	}

	@Override
	public int getSize() {
		if (datas != null) {
			return datas.size();
		} else {
			return 0;
		}
	}

}
