package com.jovx.xswing.table;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.jovx.xswing.event.EventListener;
import com.jovx.xswing.event.EventService;
import com.jovx.xswing.event.ModelAddEvent;
import com.jovx.xswing.event.ModelDeleteEvent;
import com.jovx.xswing.event.ModelFieldValueChangedEvent;
import com.jovx.xswing.factory.XSwingFactory;
import com.jovx.xswing.model.ModelInfoBuilder;

public class BaseTableModel<T> extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<T> datas = new ArrayList<T>();
	private ModelInfoBuilder<T> modelInfoBuilder;
	private EventService eventService = XSwingFactory.getInstance()
			.findDefaultEventService();

	public EventService getEventService() {
		return eventService;
	}

	public List<T> getDatas() {
		return datas;
	}

	public ModelInfoBuilder<T> getModelInfoBuilder() {
		return modelInfoBuilder;
	}

	public <X> void addEventListener(Class<X> t, EventListener<X> l) {
		eventService.register(t, l);
	}

	public void addNewEmpty() {
		try {
			T t = modelInfoBuilder.getModelType().newInstance();
			this.add(t);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void add(T... ts) {
		List<T> added = new ArrayList<T>();
		for (T t : ts) {
			added.add(t);
		}
		addDatas(added);
	}

	public void addDatas(List<T> l) {
		ModelAddEvent<T> addEvent = new ModelAddEvent<T>();
		List<T> ori = new ArrayList<T>();
		addEvent.setChangedList(l);
		addEvent.setOriginalList(ori);
		ori.addAll(datas);
		for (T t : l) {
			datas.add(t);
		}
		addEvent.setInstanceClass(getClassInstance());
		addEvent.setCurrentList(datas);
		fireEvent(addEvent);
		this.fireTableDataChanged();

	}

	public String getSortField(int i) {
		return modelInfoBuilder.getSort(i);
	}

	public BaseTableModel(Class<T> clazz) {
		modelInfoBuilder = new ModelInfoBuilder<T>(clazz);
	}

	public BaseTableModel(ModelInfoBuilder<T> modelInfoBuilder) {
		this.modelInfoBuilder = modelInfoBuilder;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
		this.fireTableDataChanged();
	}

	public Class<T> getClassInstance() {
		return modelInfoBuilder.getModelType();
	}

	@Override
	public String getColumnName(int i) {
		return modelInfoBuilder.getHeader(i);
	}

	@Override
	public void setValueAt(Object obj, int i, int j) {
		try {
			Object before = getValueAt(i, j);

			modelInfoBuilder.getSetMethod(j).invoke(datas.get(i), obj);
			ModelFieldValueChangedEvent<T> changedEvent = new ModelFieldValueChangedEvent<T>();
			changedEvent.setBefore(before);
			changedEvent.setInstanceClass(getClassInstance());
			changedEvent.setModel(datas.get(i));
			changedEvent.setFieldName(modelInfoBuilder.getFieldName(j));
			changedEvent.setNow(obj);
			fireEvent(changedEvent);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Class<?> getColumnClass(int i) {
		Class cs = modelInfoBuilder.getRenderClazz(i);
		if (!cs.equals(Object.class)) {
			return cs;
		} else {
			Class z = super.getColumnClass(i);
			return z;
		}

	}

	@Override
	public int getColumnCount() {
		return modelInfoBuilder.getColumnCount();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		return modelInfoBuilder.getEditable(columnIndex);
	}

	@Override
	public int getRowCount() {
		return datas.size();
	}

	@Override
	public Object getValueAt(int i, int j) {

		try {
			return modelInfoBuilder.getGetMethod(j).invoke(datas.get(i));
		} catch (Throwable e) {
			e.printStackTrace();
			return "fail to init this field";
		}

	}

	public void reloadSequence() {
		Collections.sort((List<Comparable>) datas);
		this.fireTableDataChanged();
	}

	public void removeRows(int[] rows) {
		List<T> removeList = new ArrayList<T>();
		for (int i : rows) {
			removeList.add(datas.get(i));
		}

		List<T> ori = new ArrayList<T>();
		ori.addAll(datas);
		datas.removeAll(removeList);

		ModelDeleteEvent<T> modelDeleteEvent = new ModelDeleteEvent<T>();
		modelDeleteEvent.setChangedList(removeList);
		modelDeleteEvent.setOriginalList(ori);
		modelDeleteEvent.setInstanceClass(getClassInstance());
		modelDeleteEvent.setCurrentList(datas);
		fireEvent(modelDeleteEvent);
		this.fireTableDataChanged();

	}

	public void fireEvent(Object o) {
		eventService.fireEvent(o);
	}

	public T getData(int i) {
		return datas.get(i);
	}

}
