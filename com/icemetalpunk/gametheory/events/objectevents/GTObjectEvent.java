package com.icemetalpunk.gametheory.events.objectevents;

import com.icemetalpunk.gametheory.events.GTEvent;
import com.icemetalpunk.gametheory.events.GTEventHandler;
import com.icemetalpunk.gametheory.events.GTEventProcessor;

public abstract class GTObjectEvent extends GTEvent {

	public abstract void trigger();

	@Override
	public void attachTo(GTEventProcessor processor, GTEventHandler source) {
		processor.attach(this, source);
	}

	@Override
	public void detachFrom(GTEventProcessor processor, GTEventHandler source) {
		processor.detach(this, source);
	}

	public static abstract class OutsideRoom extends GTObjectEvent {
		protected boolean outLeft = false, outRight = false, outTop = false, outBottom = false;

		@Override
		public void attachTo(GTEventProcessor processor, GTEventHandler source) {
			processor.attach(this, source);
		}

		@Override
		public void detachFrom(GTEventProcessor processor, GTEventHandler source) {
			processor.detach(this, source);
		}

		public void setState(boolean left, boolean right, boolean top, boolean bottom) {
			this.outLeft = left;
			this.outRight = right;
			this.outTop = top;
			this.outBottom = bottom;
		}
	}
}
