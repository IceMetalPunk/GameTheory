package com.icemetalpunk.testpackage;

import com.icemetalpunk.gametheory.events.GTRoomEvent;

public class MyRoomStartEvent extends GTRoomEvent.RoomStart {

	@Override
	public void trigger() {
		System.out.println("Started room " + this.source + "!");
	}

}
