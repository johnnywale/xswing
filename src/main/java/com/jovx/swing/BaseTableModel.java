package com.jovx.swing;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class BaseTableModel<T> extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<T> datas = new ArrayList<T>();
	private ModelInfoBuilder<T> modelInfoBuilder;
	private EventService eventService = new EventService();

	public <X extends EventListener> void add(Class<X> t, EventListener<X> l) {
		eventService.put(t, l);
	}

	public void addNewEmpty() {
		try {
			T t = modelInfoBuilder.getModelType().newInstance();
			this.add(t);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add(T... ts) {
		ModelAddEvent<T> addEvent = new ModelAddEvent<T>();
		List<T> ori = new ArrayList<T>();
		List<T> added = new ArrayList<T>();
		addEvent.setAddedList(added);
		addEvent.setOriginalList(ori);
		ori.addAll(datas);
		for (T t : ts) {
			datas.add(t);
			added.add(t);
		}
		addEvent.setCurrentList(datas);
		eventService.fireEvent(addEvent);
		this.fireTableDataChanged();
	}

	public void addDatas(List<T> l) {
		datas.addAll(l);
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
			changedEvent.setModel(datas.get(i));
			changedEvent.setFieldName(modelInfoBuilder.getFieldName(j));
			changedEvent.setNow(obj);
			eventService.fireEvent(changedEvent);
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

}
