package com.jovx.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class Example extends JFrame {
	private BaseTableModel<Test> baseTableModel;

	public Example() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Panel panel = new Panel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(1900, 800));
		baseTableModel = new BaseTableModel<Test>(Test.class);
		JTable table = new JTable(baseTableModel);
		JScrollPane jScrollPane = new JScrollPane(table);
		panel.add(jScrollPane, BorderLayout.CENTER);
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int z = 0; z < 40; z++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					baseTableModel.addNewEmpty();
				}
			}
		});
		thread.start();

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