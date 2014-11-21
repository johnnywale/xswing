package com.jovx.xswing.table;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jovx.xswing.layout.KeyValueLayout;
import com.jovx.xswing.model.ModelConfig;
import com.jovx.xswing.model.ModelInfoBuilder;

public class ModelEditPanel<T> extends JPanel {
	private JPanel dataPanel;
	protected T instance;
	private ModelInfoBuilder<T> modelInfoBuilder;

	/**
	 * @return the instance
	 */
	public T getInstance() {
		return instance;
	}

	public ModelEditPanel() {

	}

	public ModelEditPanel(final T instance) {
		modelInfoBuilder = new ModelInfoBuilder<T>(
				(Class<T>) instance.getClass());
		this.instance = instance;
		setLayout(new BorderLayout());
		dataPanel = new JPanel(new KeyValueLayout());
		add(dataPanel, BorderLayout.CENTER);

	}

	@Override
	public Dimension getPreferredSize() {
		if (dataPanel != null) {
			Dimension xx = dataPanel.getPreferredSize();
			double z = Math.max(xx.getWidth(), 100);
			double w = Math.max(xx.getHeight(), 60);
			return new Dimension(Double.valueOf(z).intValue(), Double
					.valueOf(w).intValue());
		} else {
			return new Dimension(500, 300);
		}

	}

	public JTextField addField(String x, Object value) {
		Component label = new JLabel(x);
		FontMetrics xx = label.getFontMetrics(label.getFont());
		int width = xx.stringWidth(x);
		label.setPreferredSize(new Dimension(width, 30));
		dataPanel.add(label);
		String data = value == null ? "" : value.toString();
		JTextField tt = new JTextField(data);
		dataPanel.add(tt);
		return tt;
	}

	public void init() {
		List<ModelConfig> configs = modelInfoBuilder.getColumnConfigs();
		for (final ModelConfig modelConfig : configs) {
			final JTextField editField = addField(modelConfig.getHeader(),
					modelConfig.getValue(instance));
			if (!modelConfig.isEditable()) {
				editField.setEditable(false);
			}
			editField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					try {
						modelConfig.getSetMethod().invoke(instance,
								new Object[] { editField.getText() });

					} catch (Throwable e1) {
						throw new RuntimeException(e1);
					}
				}

			});
		}
	}
}
