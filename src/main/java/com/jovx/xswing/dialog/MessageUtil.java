package com.jovx.xswing.dialog;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class MessageUtil {

	private static int y = 0;

	private static void prepareDialog(JDialog dialog) {
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - dialog.getWidth());
		int height = (screenSize.height);
		if (y > height - dialog.getHeight()) {
			y = 0;
		}
		dialog.setLocation(x, y);
		y += dialog.getHeight();

	}

	public static void showException(Throwable throwable) {
		showMessage(throwable.getMessage());
	}

	public static void showMessage(String message) {
		final JOptionPane optionPane = new JOptionPane(message,
				JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION,
				null, new Object[] {}, null);
		final JDialog dialog = new JDialog();
		dialog.setTitle("Message");
		dialog.setModal(false);
		dialog.setLocationRelativeTo(null);
		dialog.setContentPane(optionPane);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.pack();

		prepareDialog(dialog);

		Timer timer = new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Point x = dialog.getLocation();
				dialog.setVisible(false);
			}
		});
		timer.setRepeats(false);
		timer.start();
		dialog.setVisible(true);

	}

}
