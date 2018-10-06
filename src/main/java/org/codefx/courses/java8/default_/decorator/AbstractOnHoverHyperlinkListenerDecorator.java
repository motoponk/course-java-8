package org.codefx.courses.java8.default_.decorator;

import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkEvent.EventType;
import javax.swing.event.HyperlinkListener;

public abstract class AbstractOnHoverHyperlinkListenerDecorator extends AbstractHyperlinkListenerDecorator {

	protected AbstractOnHoverHyperlinkListenerDecorator(HyperlinkListener decoratedListener) {
		super(decoratedListener);
	}

	@Override
	public final void hyperlinkUpdate(HyperlinkEvent event) {
		if (event.getEventType() == EventType.ENTERED)
			onEnter(event);

		super.hyperlinkUpdate(event);

		if (event.getEventType() == EventType.EXITED)
			onExit(event);
	}

	/**
	 * Called when the event's type is {@link EventType#ENTERED ENTERED}. Called <em>before</em> delegating to the
	 * decorated listener.
	 *
	 * @param event
	 *            the event responsible for the update
	 */
	protected abstract void onEnter(HyperlinkEvent event);

	/**
	 * Called when the event's type is {@link EventType#ENTERED ENTERED}. Called <em>after</em> delegating to the
	 * decorated listener.
	 *
	 * @param event
	 *            the event responsible for the update
	 */
	protected abstract void onExit(HyperlinkEvent event);

}
