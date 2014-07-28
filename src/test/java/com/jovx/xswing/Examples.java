package com.jovx.xswing;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXTable;

import com.jovx.xswing.factory.XSwingFactory;
import com.jovx.xswing.log.SimpleLog;
import com.jovx.xswing.table.JTablePanel;
import com.jovx.xswing.table.TableConfig;

public class Examples extends JFrame {
	@Override
	public Dimension getPreferredSize() {

		return new Dimension(100, 800);
	}

	public Examples() throws FileNotFoundException {

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
					Examples e = new Examples();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}