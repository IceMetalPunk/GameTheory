package com.icemetalpunk.gametheory.events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class GTKeyEvent extends GTGameEvent implements KeyListener {

	@Override
	public abstract void keyPressed(KeyEvent event);

	@Override
	public abstract void keyReleased(KeyEvent event);

	@Override
	public abstract void keyTyped(KeyEvent event);

	@Override
	public void attachTo(GTEventHandler comp) {
		comp.attachListener(this);
	}

	@Override
	public void detachFrom(GTEventHandler comp) {
		comp.detachListener(this);
	}

}
