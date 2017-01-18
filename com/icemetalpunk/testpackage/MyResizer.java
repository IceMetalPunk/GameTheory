package com.icemetalpunk.testpackage;

import com.icemetalpunk.gametheory.events.GTResizeEvent;

public class MyResizer extends GTResizeEvent {

	@Override
	public void trigger(int width, int height) {
		System.out.println("New size: (" + width + " x " + height + ")");
	}

}
