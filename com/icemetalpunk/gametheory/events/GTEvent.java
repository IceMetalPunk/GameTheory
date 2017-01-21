package com.icemetalpunk.gametheory.events;

import com.icemetalpunk.gametheory.guis.Game;

public class GTEvent {
	protected GTEventHandler source;

	public GTEvent() {
	}

	public GTEvent(GTEventHandler s, Game w) {
		this.source = s;
	}

	public void setSource(GTEventHandler s) {
		this.source = s;
	}

	public Game getWindow() {
		if (this.source == null) {
			return null;
		} else {
			return this.source.getWindow();
		}
	}

	public void attachTo(GTEventProcessor processor, GTEventHandler source) {
		processor.attach(this, source);
	}

	public void detachFrom(GTEventProcessor processor, GTEventHandler source) {
		processor.detach(this, source);
	}
}
