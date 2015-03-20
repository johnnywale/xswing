package com.jovx.xswing.widget;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConsoleDialog extends JDialog {
	private JTextArea textComp;

	/**
	 * @return the textComp
	 */
	public JTextArea getTextComp() {
		return textComp;
	}

	/**
	 * @param textComp
	 *            the textComp to set
	 */
	public void setTextComp(JTextArea textComp) {
		this.textComp = textComp;
	}

	public ConsoleDialog(JFrame frame, String string, boolean b) {
		super(frame, string, b);
		setPreferredSize(new Dimension(500, 600));

		textComp = new JTextArea(5, 20);
		JScrollPane scrolll = new JScrollPane(textComp);
		Container content = getContentPane();
		content.add(scrolll, BorderLayout.CENTER);

	}

	public void apply2Console() {

	}
}
