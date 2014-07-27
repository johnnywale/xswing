package com.stee.ims.monitoring.anno;

import java.util.List;

public class ModelAddEvent<T> {
	private List<T> originalList;
	private List<T> addedList;
	private List<T> currentList;

	public List<T> getAddedList() {
		return addedList;
	}

	public void setAddedList(List<T> addedList) {
		this.addedList = addedList;
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
		return "ModelAddEvent [originalList=" + originalList.size()
				+ ", addedList=" + addedList.size() + ", currentList="
				+ currentList.size() + "]";
	}

}
