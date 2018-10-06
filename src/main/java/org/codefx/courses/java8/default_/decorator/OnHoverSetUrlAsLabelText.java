package org.codefx.courses.java8.default_.decorator;

import java.net.URL;
import java.util.Objects;

import javax.swing.JLabel;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 * Sets the URL on the label specified during construction while the mouse is hovering over the link.
 */
public final class OnHoverSetUrlAsLabelText extends AbstractOnHoverHyperlinkListenerDecorator {

	private static final String URL_NULL = "No URL";

	private final JLabel label;

	public OnHoverSetUrlAsLabelText(HyperlinkListener decoratedListener, JLabel label) {
		super(decoratedListener);
		Objects.requireNonNull(label, "The argument 'label' must not be null.");
		this.label = label;
	}

	@Override
	protected void onEnter(HyperlinkEvent event) {
		URL url = event.getURL();
		String urlString = url != null ? url.toExternalForm() : URL_NULL;
		label.setText(urlString);
	}

	@Override
	protected void onExit(HyperlinkEvent event) {
		label.setText(null);
	}

}
