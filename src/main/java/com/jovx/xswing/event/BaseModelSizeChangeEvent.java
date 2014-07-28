package com.jovx.xswing.event;

import java.util.List;

public class BaseModelSizeChangeEvent<T> implements ModelListener<T> {
	private List<T> originalList;
	private List<T> changedList;
	private List<T> currentList;
	private Class<T> instanceClass;

	public List<T> getChangedList() {
		return changedList;
	}

	public void setChangedList(List<T> changedList) {
		this.changedList = changedList;
	}

	public List<T> getOriginalList() {
		return originalList;
	}

	public void setOriginalList(List<T> originalList) {
		this.originalList = originalList;
	}

	public List<T> getCurrentList() {
		return currentList;
	}

	public void setCurrentList(List<T> currentList) {
		this.currentList = currentList;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [originalList="
				+ originalList.size() + ", changedList=" + changedList.size()
				+ ", currentList=" + currentList.size() + "]";
	}

	@Override
	public Class<T> getInstanceClass() {

		return instanceClass;
	}

	public void setInstanceClass(Class<T> instanceClass) {
		this.instanceClass = instanceClass;

	}

}
