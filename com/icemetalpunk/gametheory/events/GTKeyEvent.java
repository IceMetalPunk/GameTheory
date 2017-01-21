package com.icemetalpunk.gametheory.events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class GTKeyEvent extends GTEvent implements KeyListener {

	@Override
	public abstract void keyPressed(KeyEvent event);

	@Override
	public abstract void keyReleased(KeyEvent event);

	@Override
	public abstract void keyTyped(KeyEvent event);
	
	@Override
	public void attachTo(GTEventProcessor processor, GTEventHandler source) {
		processor.attach(this, source);
	}

	@Override
	public void detachFrom(GTEventProcessor processor, GTEventHandler source) {
		processor.detach(this, source);
	}

}
