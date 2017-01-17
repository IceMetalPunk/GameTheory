package com.icemetalpunk.testpackage;

import com.icemetalpunk.gametheory.events.GTStepEvent;

public class MyStepEvent extends GTStepEvent {

	private int counter = 1;

	@Override
	public void step() {
		System.out.println("Step " + (counter++) + "...");
	}

}
