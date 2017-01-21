package com.icemetalpunk.gametheory.guis;

import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.icemetalpunk.gametheory.events.GTEvent;
import com.icemetalpunk.gametheory.events.GTEventHandler;
import com.icemetalpunk.gametheory.events.GTEventProcessor;

public class Game extends GTEventHandler {

	private int currentRoom;
	private Room thisRoom;
	private final List<Room> roomList = new ArrayList<Room>();
	private final Container oContentPane;
	private final JFrame mainFrame = new JFrame();

	@SuppressWarnings("unchecked")
	public JFrame getFrame() {
		return this.mainFrame;
	}

	public Game() {
		this.currentRoom = -1;
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.oContentPane = this.mainFrame.getContentPane();
		this.mainFrame.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Game source = Game.this;
				JFrame frame = (JFrame) source.getFrame();
				frame.getContentPane().setSize(frame.getSize());
				source.triggerResize();
			}
		});
	}

	@Override
	public void attach(GTEvent event) {
		event.attachTo(new GTEventProcessor(), this);
	}

	@Override
	public void detach(GTEvent event) {
		event.detachFrom(new GTEventProcessor(), this);
	}

	public void triggerResize() {
		if (this.thisRoom != null) {
			this.thisRoom.triggerResize(this.mainFrame.getWidth(), this.mainFrame.getHeight());
		}
	}

	public void resetBackground() {
		this.mainFrame.setContentPane(this.oContentPane);
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

	@Override
	public Game getWindow() {
		return this;
	}
}
