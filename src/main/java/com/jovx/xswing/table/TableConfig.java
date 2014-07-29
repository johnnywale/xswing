package com.jovx.xswing.table;

public class TableConfig<T> {
	private boolean autoPopMenu = true;
	private Class<T> clazz;
	private String tableInstance;

	public String getTableInstance() {
		return tableInstance;
	}

	public void setTableInstance(String tableInstance) {
		this.tableInstance = tableInstance;
	}

	public TableConfig(Class<T> clazz) {
		this.clazz = clazz;
	}

	public boolean isAutoPopMenu() {
		return autoPopMenu;
	}

	public void setAutoPopMenu(boolean autoPopMenu) {
		this.autoPopMenu = autoPopMenu;
	}

	public Class<T> getClazz() {
		return clazz;
	}

}
