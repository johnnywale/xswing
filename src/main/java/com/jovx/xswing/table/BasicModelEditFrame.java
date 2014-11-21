package com.jovx.xswing.table;

import java.awt.Dimension;

import javax.swing.JFrame;

public class BasicModelEditFrame<T> extends JFrame {
	private ModelEditPanel<T> modelEditPanel;

	public T getInstance() {
		return modelEditPanel.getInstance();
	}

	public BasicModelEditFrame(final T instance) {
		modelEditPanel = new ModelEditPanel<T>(instance);
		setContentPane(modelEditPanel);
		setResizable(false);
		setUndecorated(true);

	}

	public void init() {
		modelEditPanel.init();
	}

	@Override
	public Dimension getPreferredSize() {
		return modelEditPanel.getPreferredSize();

	}

}
