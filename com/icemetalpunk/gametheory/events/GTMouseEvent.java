package com.icemetalpunk.gametheory.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class GTMouseEvent extends GTGameEvent implements MouseListener {

	@Override
	public abstract void mouseClicked(MouseEvent event);

	@Override
	public abstract void mouseEntered(MouseEvent event);

	@Override
	public abstract void mouseExited(MouseEvent event);

	@Override
	public abstract void mousePressed(MouseEvent event);

	@Override
	public abstract void mouseReleased(MouseEvent event);

	@Override
	public void attachTo(GTEventHandler comp) {
		comp.attachListener(this);
	}

	@Override
	public void detachFrom(GTEventHandler comp) {
		comp.detachListener(this);
	}
}
