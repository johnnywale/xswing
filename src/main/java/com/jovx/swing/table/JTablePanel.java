package com.jovx.swing.table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JTablePanel<T> extends JPanel {
	private BaseTableModel<T> baseTableModel;
	protected JPopupMenu jPopupMenu;
	private JTable jTable;

	public ImageIcon createImageIcon(String filename, String description) {

		String path = filename;
		return new ImageIcon(getClass().getResource(path), description);

	}

	public JTablePanel(Class<T> clazz) {

		setLayout(new BorderLayout());
		baseTableModel = new BaseTableModel<T>(clazz);
		jTable = new JTable(baseTableModel);
		JScrollPane jScrollPane = new JScrollPane(jTable);
		add(jScrollPane, BorderLayout.CENTER);
		jPopupMenu = new JPopupMenu();
		JMenuItem newRow = new JMenuItem("New");
		newRow.setIcon(createImageIcon("add.gif", "add new"));
		newRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				baseTableModel.addNewEmpty();
			}
		});
		jPopupMenu.add(newRow);
		JMenuItem deleteRow = new JMenuItem("Delete");
		deleteRow.setIcon(createImageIcon("delete.gif", "delete record"));
		deleteRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int[] rows = jTable.getSelectedRows();
				baseTableModel.removeRows(rows);
				System.out.println("delete");
			}
		});
		jPopupMenu.add(deleteRow);
		jTable.addMouseListener(new JTableMouseListener());
		jScrollPane.addMouseListener(new JTableMouseListener());
	}

	class JTableMouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if (e.isPopupTrigger()) {
				jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}

		public void mouseReleased(MouseEvent e) {
			if (e.isPopupTrigger()) {
				jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

}
