package com.icemetalpunk.gametheory.events;

import java.awt.Component;

import com.icemetalpunk.gametheory.guis.Game;

public abstract class GTGameEvent {
	protected Component source;
	protected Game window;

	public GTGameEvent() {
	}

	public GTGameEvent(Component s, Game w) {
		this.source = s;
		this.window = w;
	}

	public void setSource(Component s) {
		this.source = s;
	}

	public void setWindow(Game w) {
		this.window = w;
	}

	public abstract void attachTo(GTEventHandler comp);

	public abstract void detachFrom(GTEventHandler comp);

	public void attachTo(GTStepHandler comp) {
	}

	public void detachFrom(GTStepHandler comp) {
	}
}
