package com.icemetalpunk.testpackage;

import java.awt.event.MouseEvent;

import com.icemetalpunk.gametheory.events.GTMouseEvent;

public class GlobalClick extends GTMouseEvent {

	@Override
	public void mouseClicked(MouseEvent event) {
		this.window.gotoNextRoom();
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub

	}

}
