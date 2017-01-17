package com.icemetalpunk.gametheory.events;

public abstract class GTRoomEvent extends GTGameEvent {
	public abstract void trigger();

	@Override
	public void attachTo(GTEventHandler comp) {
		comp.attachListener(this);
	}

	@Override
	public void detachFrom(GTEventHandler comp) {
		comp.attachListener(this);
	}

	public static abstract class RoomStart extends GTRoomEvent {
	}

	public static abstract class RoomEnd extends GTRoomEvent {
	}
}
