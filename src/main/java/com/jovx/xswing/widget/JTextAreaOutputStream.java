package com.jovx.xswing.widget;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

public class JTextAreaOutputStream extends OutputStream {
	JTextArea textArea;

	ScheduledExecutorService updater;
	Vector<String> queue = new Vector<String>();

	public JTextAreaOutputStream(final JTextArea textArea) {
		this(textArea, null);
	}

	public JTextAreaOutputStream(final JTextArea textArea,
			ScheduledExecutorService scheduledExecutorService) {
		if (scheduledExecutorService == null) {
			this.updater = Executors.newScheduledThreadPool(1);
		} else {
			this.updater = scheduledExecutorService;
		}
		this.textArea = textArea;
		updater.scheduleWithFixedDelay(new Runnable() {
			public void run() {
				flushQueue();
			}
		}, 10, 400, TimeUnit.MILLISECONDS);
	}

	public void write(int i) {
		write(Character.toString((char) i));
	}

	public void write(byte[] buffer) {
		write(new String(buffer));
	}

	public void write(byte[] buffer, int off, int len) {
		write(new String(buffer, off, len));
	}

	public void write(final String string) {
		queue.add(string);
	 
	}

	public void flushQueue() {
		ArrayList<String> strings;
		synchronized (queue) {
			if (0 == queue.size())
				return;
			strings = new ArrayList<String>();
			strings.addAll(queue);
			queue.clear();
		}

		StringBuilder sb = new StringBuilder();
		for (String s : strings)
			sb.append(s);

		synchronized (textArea) {
			int lineCount = textArea.getLineCount();
			// Eliminate the first 100 lines when reaching 1100 lines:
			if (lineCount > 1100)
				try {
					textArea.replaceRange("", 0,
							textArea.getLineEndOffset(lineCount - 1000));
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			textArea.append(sb.toString());
			textArea.setCaretPosition(textArea.getDocument().getLength());

		}
	}

	public void flush() {
		flushQueue();
		textArea.repaint();
	}

	public void close() {
		flush();
		updater.shutdown();
	}

	/**
	 * Stop printing services, finishing to print any remaining tasks in the
	 * context of the calling thread.
	 */
	public void shutdown() {
		List<Runnable> tasks = updater.shutdownNow();
		if (null == tasks)
			return;
		for (Runnable t : tasks)
			t.run();
	}

	/** Stop printing services immediately, not printing any remaining text. */
	public void shutdownNow() {
		updater.shutdownNow();
	}
}