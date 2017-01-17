package com.icemetalpunk.gametheory.events;

public interface GTEventHandler {
	public void attachListener(GTKeyEvent event);

	public void attachListener(GTMouseEvent event);

	public void attachListener(GTStepEvent event);

	public void attachListener(GTRoomEvent event);

	public void detachListener(GTRoomEvent event);

	public void detachListener(GTStepEvent event);

	public void detachListener(GTKeyEvent event);

	public void detachListener(GTMouseEvent event);

}
