package com.icemetalpunk.testpackage;

import com.icemetalpunk.gametheory.events.objectevents.GTOutsideRoomEvent;

public class MyOutsideRoom extends GTOutsideRoomEvent {

	@Override
	public void trigger() {
		System.out.println("Outside!");
	}

}
