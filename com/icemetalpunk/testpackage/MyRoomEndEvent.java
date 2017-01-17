package com.icemetalpunk.testpackage;

import com.icemetalpunk.gametheory.events.GTRoomEvent;

public class MyRoomEndEvent extends GTRoomEvent.RoomEnd {

	@Override
	public void trigger() {
		System.out.println("Ended room " + this.source + "!");
	}

}
