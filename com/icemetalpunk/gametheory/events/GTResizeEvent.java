package com.icemetalpunk.gametheory.events;

import java.awt.Component;

import com.icemetalpunk.gametheory.guis.Game;

public abstract class GTResizeEvent {
	protected Component source;
	protected Game window;

	public GTResizeEvent() {
	}

	public GTResizeEvent(Component s, Game w) {
		this.source = s;
		this.window = w;
	}

	public void setSource(Component s) {
		this.source = s;
	}

	public void setWindow(Game w) {
		this.window = w;
	}

	public abstract void trigger(int width, int height);
}
