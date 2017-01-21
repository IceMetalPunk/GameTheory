package com.icemetalpunk.testpackage;

import com.icemetalpunk.gametheory.events.objectevents.GTObjectEvent;

public class MyOutsideRoom extends GTObjectEvent.OutsideRoom {

	@Override
	public void trigger() {
		System.out.println("Outside!");
	}

}
