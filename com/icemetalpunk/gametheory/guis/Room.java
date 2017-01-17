package com.icemetalpunk.gametheory.guis;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import com.icemetalpunk.gametheory.events.GTGameEvent;
import com.icemetalpunk.gametheory.events.GTRoomEvent;
import com.icemetalpunk.gametheory.events.GTStepEvent;
import com.icemetalpunk.gametheory.events.GTStepHandler;
import com.icemetalpunk.gametheory.objects.GTObject;

public class Room extends Component implements GTStepHandler {

	private static final long serialVersionUID = 1486745284368007992L;

	private int width, height, speed;
	private Game game;
	private String title;
	public static int DEFAULT_ROOM_WIDTH = 640, DEFAULT_ROOM_HEIGHT = 480, DEFAULT_ROOM_SPEED = 30;
	List<GTGameEvent> events = new ArrayList<GTGameEvent>();
	List<GTObject> objects = new ArrayList<GTObject>();
	private GTStepEvent stepEvent = null;
	private GTRoomEvent.RoomStart startEvent = null;
	private GTRoomEvent.RoomEnd endEvent = null;

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

	public int getSpeed() {
		return this.speed;
	}

	public void setSpeed(int s) {
		this.speed = s;
	}

	public void load() throws NullPointerException {
		if (this.game == null) {
			throw new NullPointerException();
		} else {

			for (GTGameEvent event : this.events) {
				event.attachTo(this.game);
			}

			for (GTObject obj : this.objects) {
				obj.attachSprite(this.game);
			}

			this.game.setSize(this.width, this.height);
			this.game.setTitle(this.title);
			this.game.setVisible(true);

			if (this.startEvent != null) {
				this.startEvent.trigger();
			}

		}
	}

	public void unload() throws NullPointerException {
		if (this.game == null) {
			throw new NullPointerException();
		} else {

			if (this.endEvent != null) {
				this.endEvent.trigger();
			}

			for (GTObject obj : this.objects) {
				obj.detachSprite(this.game);
			}

			for (GTGameEvent event : this.events) {
				event.detachFrom(this.game);
			}
		}
	}

	public void attachListener(GTGameEvent event) {
		event.setSource(this);
		event.setWindow(this.game);
		this.events.add(event);
	}

	public void attachListener(GTStepEvent event) {
		event.setSource(this);
		event.setWindow(this.game);
		this.stepEvent = event;
	}

	public void attachListener(GTRoomEvent.RoomStart event) {
		event.setSource(this);
		event.setWindow(this.game);
		this.startEvent = event;
	}

	public void attachListener(GTRoomEvent.RoomEnd event) {
		event.setSource(this);
		event.setWindow(this.game);
		this.endEvent = event;
	}

	public void setGame(Game g) {
		this.game = g;

		for (GTGameEvent event : this.events) {
			event.setSource(this);
			event.setWindow(this.game);
		}
		for (GTObject obj : this.objects) {
			obj.setWindow(this.game);
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
		obj.setWindow(this.game);
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
		if (this.stepEvent != null) {
			this.stepEvent.step();
		}
		for (GTObject object : this.objects) {
			object.step();
		}
	}
}
