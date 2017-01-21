package com.icemetalpunk.gametheory.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class GTMouseEvent extends GTEvent implements MouseListener {

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
	public void attachTo(GTEventProcessor processor, GTEventHandler source) {
		processor.attach(this, source);
	}

	@Override
	public void detachFrom(GTEventProcessor processor, GTEventHandler source) {
		processor.detach(this, source);
	}
}
