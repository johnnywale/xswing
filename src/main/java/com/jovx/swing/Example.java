package com.jovx.swing;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXTable;

import com.jovx.swing.factory.XSwingFactory;
import com.jovx.swing.log.SimpleLog;
import com.jovx.swing.table.JTablePanel;
import com.jovx.swing.table.TableConfig;

public class Example extends JFrame {
	@Override
	public Dimension getPreferredSize() {

		return new Dimension(100, 800);
	}

	public Example() throws FileNotFoundException {

		XSwingFactory swingFactory = XSwingFactory.getInstance();
		swingFactory.initInstance(new FileInputStream(new File("xswing.xml")));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		TableConfig<Test> config = new TableConfig<Test>(Test.class);
		config.setTableInstance("org.jdesktop.swingx.JXTable");
		JTablePanel<Test> jTablePanel = new JTablePanel<Test>(config);
		JXTable table = (JXTable) jTablePanel.getjTable();
		table.setColumnControlVisible(true);
		getContentPane().add(jTablePanel);
		pack();
		setLocationRelativeTo(null);
		SimpleLog.init();
		setVisible(true);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					Example e = new Example();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}