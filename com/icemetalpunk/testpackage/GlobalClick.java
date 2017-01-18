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
		TestMain.game.getCurrentRoom().setBackground(TestMain.BG1);
	}

	@Override
	public void mouseExited(MouseEvent event) {
		TestMain.game.getCurrentRoom().setBackground(TestMain.BG2);
	}

	@Override
	public void mousePressed(MouseEvent event) {
	}

	@Override
	public void mouseReleased(MouseEvent event) {
	}

}
