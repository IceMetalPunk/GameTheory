package com.icemetalpunk.gametheory.events.objectevents;

import java.awt.Component;

import com.icemetalpunk.gametheory.guis.Game;

public abstract class GTObjectEvent {
	protected Game window;

	public GTObjectEvent() {
	}

	public GTObjectEvent(Component s, Game w) {
		this.window = w;
	}

	public void setWindow(Game w) {
		this.window = w;
	}
}
