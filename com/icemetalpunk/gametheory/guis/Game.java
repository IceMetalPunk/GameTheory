package com.icemetalpunk.gametheory.guis;

import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.icemetalpunk.gametheory.events.GTEventHandler;
import com.icemetalpunk.gametheory.events.GTKeyEvent;
import com.icemetalpunk.gametheory.events.GTMouseEvent;
import com.icemetalpunk.gametheory.events.GTRoomEvent;
import com.icemetalpunk.gametheory.events.GTStepEvent;

public class Game extends JFrame implements GTEventHandler {

	private static final long serialVersionUID = 41L;

	private int currentRoom;
	private Room thisRoom;
	private final List<Room> roomList = new ArrayList<Room>();
	private final Container oContentPane;

	public Game() {
		this.currentRoom = -1;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.oContentPane = this.getContentPane();
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Game source = (Game) e.getSource();
				source.getContentPane().setSize(source.getSize());
				source.triggerResize();
			}
		});
	}

	public void triggerResize() {
		if (this.thisRoom != null) {
			this.thisRoom.triggerResize(this.getWidth(), this.getHeight());
		}
	}

	public void resetBackground() {
		this.setContentPane(this.oContentPane);
	}

	public void run() {
		while (true) {
			this.thisRoom.step();
			try {
				Thread.sleep(1000 / (this.thisRoom.getSpeed()));
			} catch (InterruptedException e) {
				System.err.println("Failed to sleep between frames!");
			}
		}
	}

	public void addRoom(Room rm) {
		rm.setGame(this);
		this.roomList.add(rm);
		if (this.roomList.size() == 1) {
			this.currentRoom = 0;
			rm.load();
			this.thisRoom = rm;
		}
	}

	public Room getRoom(int n) {
		return this.roomList.get(n);
	}

	public Room getCurrentRoom() {
		return this.thisRoom;
	}

	public void gotoRoom(int n) throws ArrayIndexOutOfBoundsException {
		if (n < 0 || n >= this.roomList.size()) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			if (this.thisRoom != null) {
				this.thisRoom.unload();
			}
			this.currentRoom = n;
			this.thisRoom = this.roomList.get(this.currentRoom);
			this.thisRoom.load();
		}
	}

	public void gotoRoom(Room rm) {
		int n = this.roomList.indexOf(rm);
		if (n < 0) {

		} else {
			if (this.thisRoom != null) {
				this.thisRoom.unload();
			}
			this.currentRoom = n;
			this.thisRoom = rm;
			rm.load();
		}
	}

	public void gotoNextRoom() {
		if (this.thisRoom != null) {
			this.thisRoom.unload();
		}
		this.currentRoom = (this.currentRoom + 1) % this.roomList.size();
		this.thisRoom = this.roomList.get(this.currentRoom);
		this.thisRoom.load();
	}

	public int getID(Room rm) {
		return this.roomList.indexOf(rm);
	}

	// Attaching event listeners

	public void attachListener(GTKeyEvent event) {
		this.addKeyListener(event);
	}

	public void attachListener(GTMouseEvent event) {
		this.addMouseListener(event);
	}

	@Override
	public void detachListener(GTKeyEvent event) {
		this.removeKeyListener(event);
	}

	@Override
	public void detachListener(GTMouseEvent event) {
		this.removeMouseListener(event);
	}

	@Override
	public void attachListener(GTStepEvent event) {
	}

	@Override
	public void detachListener(GTStepEvent event) {
	}

	@Override
	public void attachListener(GTRoomEvent event) {
	}

	@Override
	public void detachListener(GTRoomEvent event) {
	}

}
