package com.jovx.swing.model;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.TableCellEditor;

public class EditorStore {
	public static Map<Class, TableCellEditor> maps = new HashMap<Class, TableCellEditor>();

	static {
		maps.put(Color.class, new ColorEditor());

	}
}
