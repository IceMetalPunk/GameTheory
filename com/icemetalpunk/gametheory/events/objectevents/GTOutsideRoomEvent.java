package com.icemetalpunk.gametheory.events.objectevents;

public abstract class GTOutsideRoomEvent extends GTObjectEvent {
	protected boolean outLeft = false, outRight = false, outTop = false, outBottom = false;

	public void setState(boolean left, boolean right, boolean top, boolean bottom) {
		this.outLeft = left;
		this.outRight = right;
		this.outTop = top;
		this.outBottom = bottom;
	}

	public abstract void trigger();
}
