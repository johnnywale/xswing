package com.jovx.xswing.table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JDialog;

public class ModelEditDialog<T> extends JDialog {
	private ModelEditPanel<T> panel = new ModelEditPanel<T>();

	public void init(T t) {
		panel.setInstance(t);
		panel.init();
		getContentPane().add(panel, BorderLayout.CENTER);
		pack();
		panel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {

				ModelEditDialog.this.pack();

			}
		});
	}

	@Override
	public Dimension getPreferredSize() {
		return panel.getPreferredSize();
	}
}
