package com.jovx.xswing.table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import com.jovx.xswing.factory.XSwingFactory;

public class BasicModelEditFrame<T> extends JFrame implements WindowListener {
	private ModelEditPanel<T> modelEditPanel;

	public T getInstance() {
		return modelEditPanel.getInstance();
	}

	public BasicModelEditFrame(final T instance) {
		modelEditPanel = new ModelEditPanel<T>(instance);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(modelEditPanel, BorderLayout.CENTER);
		setResizable(false);
//		setUndecorated(true);
		this.addWindowListener(this);
	}

	public void init() {
		modelEditPanel.init();
	}

	@Override
	public Dimension getPreferredSize() {
		return modelEditPanel.getPreferredSize();

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		XSwingFactory.simpleModify(getInstance());
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
