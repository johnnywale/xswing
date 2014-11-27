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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jovx.xswing.layout.KeyValueLayout;
import com.jovx.xswing.model.IModelInfoBuilder;
import com.jovx.xswing.model.ModelConfig;
import com.jovx.xswing.model.ModelInfoFactory;

public class ModelEditPanel<T> extends JPanel {
	private JPanel dataPanel;
	protected T instance;
	private IModelInfoBuilder<T> modelInfoBuilder;

	public void setInstance(T instance) {
		modelInfoBuilder = (IModelInfoBuilder<T>) ModelInfoFactory
				.forBuilder(instance.getClass());
		this.instance = instance;
		setLayout(new BorderLayout());
		dataPanel = new JPanel(new KeyValueLayout());
		add(dataPanel, BorderLayout.CENTER);

	}

	/**
	 * @return the instance
	 */
	public T getInstance() {
		return instance;
	}

	public ModelEditPanel() {

	}

	public ModelEditPanel(final T instance) {
		setInstance(instance);
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

	public JTextField addField(ModelConfig modelConfig, Object instance) {
		Component label = new JLabel(modelConfig.getHeader());
		FontMetrics xx = label.getFontMetrics(label.getFont());
		int width = xx.stringWidth(modelConfig.getHeader());
		label.setPreferredSize(new Dimension(width, 30));
		dataPanel.add(label);
		Object value = modelConfig.getValue(instance);
		String data = value == null ? "" : value.toString();
		JTextField tt;

		if (modelConfig.isProtect()) {
			tt = new JPasswordField(data);
		} else {
			tt = new JTextField(data);

		}
		tt.setEditable(modelConfig.isEditable());
		dataPanel.add(tt);
		return tt;
	}

	public void init() {
		List<ModelConfig> configs = modelInfoBuilder.getColumnConfigs();
		for (final ModelConfig modelConfig : configs) {
			final JTextField editField = addField(modelConfig, instance);

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
