package com.jovx.xswing.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class KeyValueLayout implements LayoutManager, ActionListener,
		KeyListener {
	int xInset = 5;
	int yInset = 5;
	int yGap = 2;

	public void addLayoutComponent(String s, Component c) {

	}

	private void addListener(Component component) {
		if (component instanceof JTextField) {
			JTextField textField = (JTextField) component;
			ActionListener[] listeners = textField.getActionListeners();
			if (listeners != null) {
				for (ActionListener actionListener : listeners) {
					if (actionListener.equals(this)) {
						return;
					}
				}

			}
			textField.addKeyListener(this);
		}

	}

	private Container c;

	public void layoutContainer(Container c) {
		this.c = c;
		Insets insets = c.getInsets();
		int height = yInset + insets.top;

		Component[] children = c.getComponents();
		Dimension compSize = null;
		int prefLeft = 0;
		int prefRight = 0;
		for (int i = 0; i < children.length; i++) {
			addListener(children[i]);
			compSize = children[i].getPreferredSize();

			if (i % 2 == 0) {
				prefLeft = Math.max(prefLeft, compSize.width);
			} else {
				prefRight = Math.max(prefRight, compSize.width);
			}

		}
		for (int i = 0; i < children.length; i++) {
			if (i % 2 == 0) {
				children[i].setSize(prefLeft + 20, compSize.height);
				children[i].setLocation(xInset + insets.left, height);

			} else {
				children[i].setSize(prefRight + 20, compSize.height);
				children[i].setLocation(xInset + insets.left + prefLeft + 20,
						height);
				height += compSize.height + yGap;
			}

		}

	}

	public Dimension minimumLayoutSize(Container c) {
		Insets insets = c.getInsets();
		int height = yInset + insets.top;
		int width = 0 + insets.left + insets.right;

		Component[] children = c.getComponents();
		Dimension compSize = null;
		for (int i = 0; i < children.length; i++) {
			compSize = children[i].getPreferredSize();
			height += compSize.height + yGap;
			width = Math.max(width, compSize.width + insets.left + insets.right
					+ xInset * 2);
		}
		height += insets.bottom;
		return new Dimension(width, height);
	}

	public Dimension preferredLayoutSize(Container c) {
		if (c.getComponents().length > 0) {
			return minimumLayoutSize(c);
		} else {
			return new Dimension(400, 250);
		}

	}

	public void removeLayoutComponent(Component c) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		c.validate();
	}

}
