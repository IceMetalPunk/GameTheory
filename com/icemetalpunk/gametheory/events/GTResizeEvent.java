package com.icemetalpunk.gametheory.events;

public abstract class GTResizeEvent extends GTEvent {
	public abstract void trigger(int width, int height);

	@Override
	public void attachTo(GTEventProcessor processor, GTEventHandler source) {
		processor.attach(this, source);
	}

	@Override
	public void detachFrom(GTEventProcessor processor, GTEventHandler source) {
		processor.detach(this, source);
	}
}
