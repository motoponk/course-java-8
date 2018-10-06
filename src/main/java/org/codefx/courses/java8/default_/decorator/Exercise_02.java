package org.codefx.courses.java8.default_.decorator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkEvent.EventType;
import javax.swing.event.HyperlinkListener;

import org.codefx.courses.java8.default_.decorator.AbstractHyperlinkListenerDecorator;
import org.codefx.courses.java8.default_.decorator.DecoratingHyperlinkListener;

public class Exercise_02 {

	/*
	 * OBSERVE: Launch the class and interact with the window that it creates. Observe the effects
	 *          on the window and in the terminal.
	 *
	 * TASK: Introduce an interface `DecoratingHyperlinkListener` that implements fluent decoration.
	 *       Apply it in `createHyperlinkListener()`. (This should not change functionality.)
	 *
	 * OBSERVE: How few classes had to be touched for that change.
	 */

	public static void main(String[] args) {
		new Exercise_02().run();
	}

	private static final String HTML =
			"Here's a text with two links. One <a href=\"http://blog.codefx.org\">here</a> "
					+ "and one <a href=\"http://courses.codefx.org\">here</a>.";

	private final JFrame window;
	private final JEditorPane htmlView;
	private final JLabel urlLabel;

	private Exercise_02() {
		window = createFrame();
		htmlView = createHtmlView();
		urlLabel = createUrlLabel();
		putWindowTogether();

		HyperlinkListener listener = createHyperlinkListener();
		htmlView.addHyperlinkListener(listener);
	}

	private static JFrame createFrame() {
		JFrame window = new JFrame("Decorating With Java 8");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setMinimumSize(new Dimension(200, 0));
		window.setPreferredSize(new Dimension(200, 300));
		window.setMaximumSize(new Dimension(200, Integer.MAX_VALUE));
		return window;
	}

	private static JEditorPane createHtmlView() {
		JEditorPane htmlView = new JEditorPane();
		htmlView.setEditable(false);
		htmlView.setContentType("text/html");
		htmlView.setText(HTML);

		return htmlView;
	}

	private static JLabel createUrlLabel() {
		JLabel urlLabel = new JLabel();
		urlLabel.setOpaque(true);
		return urlLabel;
	}

	private void putWindowTogether() {
		window.getContentPane().add(htmlView, BorderLayout.CENTER);
		window.getContentPane().add(urlLabel, BorderLayout.SOUTH);
	}

	private HyperlinkListener createHyperlinkListener() {
		return DecoratingHyperlinkListener
				.from(this::changeHtmlViewBackgroundColor)
				.onHoverMakeVisible(urlLabel)
				.onHoverSetUrlOn(urlLabel)
				.logEvents()
				.decorate(l -> new OnActivateHighlightComponent(l, urlLabel))
				.decorate(OnEnterLogUrl::new);
	}

	private void changeHtmlViewBackgroundColor(HyperlinkEvent event) {
		if (event.getEventType() == EventType.ENTERED)
			htmlView.setBackground(Color.ORANGE);
		else if (event.getEventType() == EventType.EXITED)
			htmlView.setBackground(Color.WHITE);
	}

	private void run() {
		window.pack();
		window.setVisible(true);
	}

	// PRIVATE CLASSES

	private static class OnEnterLogUrl extends AbstractHyperlinkListenerDecorator {

		private static final String URL_NULL = "n.a.";

		protected OnEnterLogUrl(HyperlinkListener decoratedListener) {
			super(decoratedListener);
		}

		@Override
		public void hyperlinkUpdate(HyperlinkEvent event) {
			if (event.getEventType() == EventType.ENTERED)
				logUrl(event.getURL());

			super.hyperlinkUpdate(event);
		}

		private void logUrl(URL url) {
			String urlString = url != null ? url.toExternalForm() : URL_NULL;
			System.out.println("Link entered with URL " + urlString);
		}

	}

	private static class OnActivateHighlightComponent extends AbstractHyperlinkListenerDecorator {

		private static final long RESET_DELAY_IN_MS = 100;

		private final JComponent component;
		private final ScheduledThreadPoolExecutor executor;

		public OnActivateHighlightComponent(HyperlinkListener decoratedListener, JComponent component) {
			super(decoratedListener);
			Objects.requireNonNull(component, "The argument 'component' must not be null.");
			this.component = component;
			this.executor = new ScheduledThreadPoolExecutor(1);
		}

		@Override
		public void hyperlinkUpdate(HyperlinkEvent event) {
			if (event.getEventType() == EventType.ACTIVATED)
				highlightBackground();

			super.hyperlinkUpdate(event);
		}

		private void highlightBackground() {
			Color colorBefore = component.getBackground();
			component.setBackground(Color.MAGENTA);
			resetBackgroundColorAfterDelay(colorBefore);
		}

		private void resetBackgroundColorAfterDelay(Color colorBefore) {
			Runnable resetBackgroundColor = () -> component.setBackground(colorBefore);
			Runnable resetBackgroundColorInDispatchThread = () -> EventQueue.invokeLater(resetBackgroundColor);
			executor.schedule(resetBackgroundColorInDispatchThread, RESET_DELAY_IN_MS, TimeUnit.MILLISECONDS);
		}

	}

}
