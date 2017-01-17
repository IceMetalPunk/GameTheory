package com.icemetalpunk.gametheory.events;

public abstract class GTStepEvent extends GTGameEvent {

	@Override
	public void attachTo(GTEventHandler comp) {
		comp.attachListener(this);
	}

	@Override
	public void detachFrom(GTEventHandler comp) {
		comp.attachListener(this);
	}

	public abstract void step();

}
