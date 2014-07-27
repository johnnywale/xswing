package com.jovx.swing;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.jovx.swing.table.JTablePanel;

public class Example extends JFrame {

	public Example() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JTablePanel<Test> jTablePanel = new JTablePanel<Test>(Test.class);
		getContentPane().add(jTablePanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Example e = new Example();
			}
		});
	}
}