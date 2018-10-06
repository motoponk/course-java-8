package org.codefx.courses.java8.default_.decorator;

import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 * Logs the event to the console.
 */
public final class LogEventsToConsole extends AbstractHyperlinkListenerDecorator {

	public LogEventsToConsole(HyperlinkListener decoratedListener) {
		super(decoratedListener);
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent event) {
		System.out.println("Link " + event.getEventType() + ".");
		super.hyperlinkUpdate(event);
	}

}
