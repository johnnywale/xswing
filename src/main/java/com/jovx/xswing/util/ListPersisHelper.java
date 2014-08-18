package com.jovx.xswing.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListPersisHelper<T> {
	private Class<T> clazz;
	private List<T> content = new ArrayList<T>();

	public ListPersisHelper() {

	}

	public ListPersisHelper(Class<T> clazz) {
		this.clazz = clazz;
	}

	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

}
