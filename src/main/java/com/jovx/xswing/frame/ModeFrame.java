package com.jovx.xswing.frame;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.jovx.xswing.model.ModelConfig;
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
		jPanel.setLayout(new VerticalFlowLayout(VerticalFlowLayout.MIDDLE, 20,
				10));

		for (ModelConfig modelConfig : infoBuilder.getColumnConfigs()) {
			JLabel jLabel = new JLabel();
			jLabel.setText(modelConfig.getHeader() + " :");
			jPanel.add(jLabel);
			JLabel value = new JLabel();
			try {
				Object o = modelConfig.getGetMethod()
						.invoke(t, new Object[] {});

				if (o != null) {
					value.setText(o.toString());
				} else {
					value.setText("");
				}
				jPanel.add(value);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		jPanel.setBorder(titledBorder);
		jPanel.setPreferredSize(new Dimension(100, 200));
		getContentPane().add(jPanel);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(100, 200));

	}
};
