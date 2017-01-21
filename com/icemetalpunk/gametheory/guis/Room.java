package com.icemetalpunk.gametheory.guis;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.icemetalpunk.gametheory.events.GTEvent;
import com.icemetalpunk.gametheory.events.GTEventHandler;
import com.icemetalpunk.gametheory.events.GTEventProcessor;
import com.icemetalpunk.gametheory.events.GTResizeEvent;
import com.icemetalpunk.gametheory.events.GTRoomEvent;
import com.icemetalpunk.gametheory.events.GTStepEvent;
import com.icemetalpunk.gametheory.events.GTStepHandler;
import com.icemetalpunk.gametheory.objects.GTObject;

public class Room extends GTEventHandler implements GTStepHandler {

	private int width, height, speed;
	private Game game;
	private String title;
	public static int DEFAULT_ROOM_WIDTH = 640, DEFAULT_ROOM_HEIGHT = 480, DEFAULT_ROOM_SPEED = 30;
	public List<GTEvent> events = new ArrayList<GTEvent>();
	public List<GTObject> objects = new ArrayList<GTObject>();
	public final List<GTRoomEvent.RoomStart> startEvents = new ArrayList<GTRoomEvent.RoomStart>();
	public final List<GTRoomEvent.RoomEnd> endEvents = new ArrayList<GTRoomEvent.RoomEnd>();
	private GTBackground background = null;

	public Room(int w, int h, String t, int s, Game g) {
		this.width = w;
		this.height = h;
		this.title = t;
		this.game = g;
		this.speed = s;
	}

	public Room(int w, int h, String t, int s) {
		this(w, h, t, s, null);
	}

	public Room(String t, int s) {
		this(DEFAULT_ROOM_WIDTH, DEFAULT_ROOM_HEIGHT, t, s, null);
	}

	public Room(int w, int h, int s) {
		this(w, h, "", s, null);
	}

	public Room(int s) {
		this(DEFAULT_ROOM_WIDTH, DEFAULT_ROOM_HEIGHT, "", s, null);
	}

	public Room(int w, int h, String t) {
		this(w, h, t, DEFAULT_ROOM_SPEED, null);
	}

	public Room(String t) {
		this(DEFAULT_ROOM_WIDTH, DEFAULT_ROOM_HEIGHT, t, DEFAULT_ROOM_SPEED, null);
	}

	public Room(int w, int h) {
		this(w, h, "", DEFAULT_ROOM_SPEED, null);
	}

	public Room() {
		this(DEFAULT_ROOM_WIDTH, DEFAULT_ROOM_HEIGHT, "", DEFAULT_ROOM_SPEED, null);
	}

	@Override
	public void attach(GTEvent event) {
		event.attachTo(new GTEventProcessor(), this);
	}

	@Override
	public void detach(GTEvent event) {
		event.detachFrom(new GTEventProcessor(), this);
	}

	// Backgrounds
	public void setBackground(String img) throws IOException {
		if (this.background == null) {
			this.background = new GTBackground(img);
		} else {
			this.background.setImage(img);
			this.refreshBackground();
		}
	}

	public void setBackground(URL img) throws IOException {
		if (this.background == null) {
			this.background = new GTBackground(img);
		} else {
			this.background.setImage(img);
			this.refreshBackground();
		}
	}

	public void setBackground(GTBackground img) {
		this.background = img;
		this.refreshBackground();
	}

	public void setBackground(BufferedImage img) throws IOException {
		if (this.background == null) {
			this.background = new GTBackground(img);
		} else {
			this.background.setImage(img);
			this.refreshBackground();
		}
	}

	private void refreshBackground() {
		if (this.game != null) {
			if (this.background != null) {
				this.background.setSize(this.game.getFrame().getSize());
				this.game.getFrame().setContentPane(this.background);
			} else {
				this.game.resetBackground();
			}
		}
	}

	// Other getters/setters
	public int getSpeed() {
		return this.speed;
	}

	public void setSpeed(int s) {
		this.speed = s;
	}

	// Loading
	public void load() throws NullPointerException {
		if (this.game == null) {
			throw new NullPointerException();
		} else {

			GTEventProcessor processor = new GTEventProcessor();
			for (GTEvent event : this.events) {
				event.attachTo(processor, this.game);
			}

			for (GTObject obj : this.objects) {
				obj.attachSprite(this.game);
			}

			if (this.background != null) {
				this.game.getFrame().setContentPane(this.background);
			}
			this.game.getFrame().setSize(this.width, this.height);
			this.game.getFrame().setTitle(this.title);
			this.game.getFrame().setVisible(true);

			for (GTRoomEvent.RoomStart event : this.startEvents) {
				event.trigger();
			}

		}
	}

	public void triggerResize(int w, int h) {
		for (GTResizeEvent event : this.resizeEvents) {
			event.trigger(w, h);
		}
	}

	// Unloading
	public void unload() throws NullPointerException {
		if (this.game == null) {
			throw new NullPointerException();
		} else {

			for (GTRoomEvent.RoomEnd event : this.endEvents) {
				event.trigger();
			}

			this.game.resetBackground();
			for (GTObject obj : this.objects) {
				obj.detachSprite(this.game);
			}

			GTEventProcessor processor = new GTEventProcessor();
			for (GTEvent event : this.events) {
				event.detachFrom(processor, this.game);
			}
		}
	}

	// Event handlers
	public void attachListener(GTEvent event) {
		event.setSource(this);
		this.events.add(event);
	}

	public void setGame(Game g) {
		this.game = g;

		for (GTEvent event : this.events) {
			event.setSource(this);
		}
		for (GTObject obj : this.objects) {
			obj.setRoom(this);
		}

	}

	public Game getGame() {
		return this.game;
	}

	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setTitle(String t) {
		this.title = t;
	}

	public String getTitle() {
		return this.title;
	}

	public void addObject(GTObject obj) {
		obj.setRoom(this);
		this.objects.add(obj);
	}

	@Override
	public String toString() {
		if (game == null) {
			return "Unattached room";
		} else {
			return String.valueOf(this.game.getID(this));
		}
	}

	@Override
	public void step() {
		for (GTStepEvent event : this.stepEvents) {
			event.step();
		}
		for (GTObject object : this.objects) {
			object.step();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public JFrame getFrame() {
		if (this.game == null) {
			return null;
		} else {
			return this.game.getFrame();
		}
	}

	@Override
	public Game getWindow() {
		if (this.game == null) {
			return null;
		} else {
			return this.game.getWindow();
		}
	}
}
