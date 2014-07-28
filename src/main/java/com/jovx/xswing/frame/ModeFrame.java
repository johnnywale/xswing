package com.jovx.xswing.frame;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.jovx.xswing.model.ModelInfoBuilder;

public class ModeFrame<T> extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8980392522178325310L;
	private ModelInfoBuilder<T> infoBuilder;
	private T data;

	public ModeFrame(T t, ModelInfoBuilder<T> infoBuilder) {
		data = t;
		this.infoBuilder = infoBuilder;
		TitledBorder titledBorder = new TitledBorder("deail");
		JPanel jPanel = new JPanel();
		jPanel.setBorder(titledBorder);
		jPanel.setPreferredSize(new Dimension(100, 200));
		getContentPane().add(jPanel);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(100, 200));

	}

};
