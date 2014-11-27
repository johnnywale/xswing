package com.jovx.xswing.table;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.jovx.xswing.frame.ModeFrame;
import com.jovx.xswing.model.EditorStore;
import com.jovx.xswing.model.IModelInfoBuilder;
import com.jovx.xswing.model.ModelConfig;

public class JTablePanel<T> extends JPanel {
	private BaseTableModel<T> baseTableModel;
	protected JPopupMenu jPopupMenu;
	private JTable jTable;
	private Logger logger = Logger.getLogger("com.jovx.xswing.table");

	public BaseTableModel<T> getBaseTableModel() {
		return baseTableModel;
	}

	public JPopupMenu getjPopupMenu() {
		return jPopupMenu;
	}

	public JTable getjTable() {
		return jTable;
	}

	public ImageIcon createImageIcon(String filename, String description) {

		String path = filename;
		return new ImageIcon(getClass().getResource(path), description);

	}

	public JTablePanel(TableConfig<T> config) {
		setLayout(new BorderLayout());
		baseTableModel = new BaseTableModel<T>(config.getClazz());

		String clazz = config.getTableInstance();
		if (clazz != null) {
			try {
				jTable = (JTable) Class.forName(clazz)
						.getConstructor(new Class[] { TableModel.class })
						.newInstance(baseTableModel);
			} catch (Throwable e) {
				logger.log(Level.SEVERE, "fail to init jtable with class "
						+ clazz);
				jTable = new JTable(baseTableModel);
			}
		} else {
			jTable = new JTable(baseTableModel);
		}
		for (String key : EditorStore.maps.keySet()) {
			try {
				jTable.setDefaultEditor(Class.forName(key),
						EditorStore.maps.get(key));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		TableRowSorter sorter = new TableRowSorter(baseTableModel);
		List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();

		IModelInfoBuilder<T> xx = baseTableModel.getModelInfoBuilder();
		List<ModelConfig> configs = xx.getColumnConfigs();
		for (int i = 0; i < configs.size(); i++) {
			if (configs.get(i).isEditable()) {
				sortKeys.add(new RowSorter.SortKey(i, SortOrder.ASCENDING));
			}
		}
		sorter.setSortKeys(sortKeys);
		jTable.setRowSorter(sorter);

		JScrollPane jScrollPane = new JScrollPane(jTable);
		add(jScrollPane, BorderLayout.CENTER);
		if (config.isAutoPopMenu()) {
			jPopupMenu = new JPopupMenu();
			JMenuItem newRow = new JMenuItem("New");
			newRow.setIcon(createImageIcon("add.gif", "add new"));
			newRow.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					baseTableModel.addNewEmpty();
				}
			});

			JMenuItem deleteRow = new JMenuItem("Delete");
			deleteRow.setIcon(createImageIcon("delete.gif", "delete record"));
			deleteRow.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int[] rows = jTable.getSelectedRows();
					baseTableModel.removeRows(rows);
					logger.log(Level.FINE, "Delete");
				}
			});
			JMenuItem detailRow = new JMenuItem("View");
			detailRow.setIcon(createImageIcon("article.gif", "View record"));
			detailRow.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int[] rows = jTable.getSelectedRows();
					if (rows.length == 1) {
						T t = baseTableModel.getData(rows[0]);
						ModeFrame<T> modeFrame = new ModeFrame<T>(t,
								baseTableModel.getModelInfoBuilder());
						modeFrame
								.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						modeFrame.setVisible(true);

					}

				}
			});

			jPopupMenu.add(newRow);
			jPopupMenu.addSeparator();
			jPopupMenu.add(detailRow);
			jPopupMenu.addSeparator();
			jPopupMenu.add(deleteRow);

		}
		jTable.addMouseListener(new JTableMouseListener());
		jScrollPane.addMouseListener(new JTableMouseListener());
	}

	public JTablePanel(Class<T> clazz) {
		this(new TableConfig<T>(clazz));
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
