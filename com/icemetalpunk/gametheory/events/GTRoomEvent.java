package com.icemetalpunk.gametheory.events;

public abstract class GTRoomEvent extends GTEvent {
	public abstract void trigger();

	@Override
	public void attachTo(GTEventProcessor processor, GTEventHandler source) {
		processor.attach(this, source);
	}

	@Override
	public void detachFrom(GTEventProcessor processor, GTEventHandler source) {
		processor.detach(this, source);
	}

	public static abstract class RoomStart extends GTRoomEvent {
		@Override
		public void attachTo(GTEventProcessor processor, GTEventHandler source) {
			processor.attach(this, source);
		}

		@Override
		public void detachFrom(GTEventProcessor processor, GTEventHandler source) {
			processor.detach(this, source);
		}
	}

	public static abstract class RoomEnd extends GTRoomEvent {
		@Override
		public void attachTo(GTEventProcessor processor, GTEventHandler source) {
			processor.attach(this, source);
		}

		@Override
		public void detachFrom(GTEventProcessor processor, GTEventHandler source) {
			processor.detach(this, source);
		}
	}
}
