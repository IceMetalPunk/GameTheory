package com.icemetalpunk.gametheory.events;

public abstract class GTStepEvent extends GTEvent {

	public abstract void step();

	@Override
	public void attachTo(GTEventProcessor processor, GTEventHandler source) {
		processor.attach(this, source);
	}

	@Override
	public void detachFrom(GTEventProcessor processor, GTEventHandler source) {
		processor.detach(this, source);
	}

}
