package com.jovx.xswing.action;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.jovx.xswing.console.ConsoleAdapt;
import com.jovx.xswing.widget.ConsoleDialog;

public class ConsoleAction extends AbstractAction {
	private ConsoleDialog console = ConsoleAdapt.getInstance()
			.getConsoleDialog();
	private Component relativeTo;

	public ConsoleAction(Component relativeTo) {
		this.relativeTo = relativeTo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		console.apply2Console();
		console.pack();
		console.setLocationRelativeTo(relativeTo);
		console.setVisible(true);
	}

}
