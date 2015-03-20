package com.jovx.xswing.console;

import java.io.PrintStream;

import com.jovx.xswing.widget.ConsoleDialog;
import com.jovx.xswing.widget.JTextAreaOutputStream;

public class ConsoleAdapt {
	private ConsoleDialog consoleDialog;
	public static ConsoleAdapt instance;
	private PrintStream outputStream = System.out;

	/**
	 * @return the instance
	 */
	public static ConsoleAdapt getInstance() {
		if (instance == null) {
			instance = new ConsoleAdapt();
			instance.init();
		}
		return instance;
	}

	/**
	 * @return the consoleDialog
	 */
	public ConsoleDialog getConsoleDialog() {
		return consoleDialog;
	}

	private void init() {
		consoleDialog = new ConsoleDialog(null, "Console", false);
		JTextAreaOutputStream op = new JTextAreaOutputStream(
				consoleDialog.getTextComp()) {
			@Override
			public void write(String string) {
				super.write(string);
				outputStream.print(string);
			}
		};
		System.setOut(new PrintStream(op, true));
		System.setOut(new PrintStream(op, true));
	}

}
