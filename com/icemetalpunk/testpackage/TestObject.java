package com.icemetalpunk.testpackage;

import com.icemetalpunk.gametheory.objects.GTObject;

public class TestObject extends GTObject {

	public TestObject(int xpos, int ypos) {
		super(xpos, ypos);
	}

	@Override
	public void step() {
		super.step();
		//System.out.println("(" + this.x + ", " + this.y + ")");
	}
}
