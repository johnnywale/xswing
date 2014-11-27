package com.jovx.xswing.model;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.TableCellEditor;

public class EditorStore {
	public static Map<String, TableCellEditor> maps = new HashMap<String, TableCellEditor>();
	static {
		maps.put(Color.class.getName(), new ColorEditor());

	}

	public static TableCellEditor getTableCellEditor(Class clazz) {
		return maps.get(clazz.getName());
	}
}
