package com.icemetalpunk.gametheory.objects;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.JLabel;

import com.icemetalpunk.gametheory.events.GTEvent;
import com.icemetalpunk.gametheory.events.GTEventHandler;
import com.icemetalpunk.gametheory.events.GTEventProcessor;
import com.icemetalpunk.gametheory.events.GTStepHandler;
import com.icemetalpunk.gametheory.events.objectevents.GTObjectEvent;
import com.icemetalpunk.gametheory.guis.Game;
import com.icemetalpunk.gametheory.guis.Room;
import com.icemetalpunk.gametheory.sprites.GTSprite;

public abstract class GTObject extends GTEventHandler implements GTStepHandler {
	private static AtomicLong idCounter = new AtomicLong();
	public double x, y, hspeed, vspeed, gravity;
	public final long id;
	private GTSprite sprite;
	private final JLabel spriteLabel;
	public boolean visible;
	public final List<GTObjectEvent.OutsideRoom> outsideRoomEvents = new ArrayList<GTObjectEvent.OutsideRoom>();
	public Room room;

	public GTObject(int xpos, int ypos) {
		this.x = xpos;
		this.y = ypos;
		this.hspeed = 0;
		this.vspeed = 0;
		this.gravity = 0;
		this.visible = true;
		this.room = null;
		this.spriteLabel = new JLabel();
		this.spriteLabel.setVisible(false);
		this.id = idCounter.getAndIncrement();
	}

	public GTObject() {
		this(0, 0);
	}

	@Override
	public void attach(GTEvent event) {
		event.attachTo(new GTEventProcessor(), this);
	}

	@Override
	public void detach(GTEvent event) {
		event.detachFrom(new GTEventProcessor(), this);
	}

	public void setRoom(Room r) {
		this.room = r;
	}

	@Override
	public String toString() {
		return String.valueOf(this.id);
	}

	public void setSprite(GTSprite spr) {
		this.sprite = spr;
		if (spr != null) {
			this.spriteLabel.setIcon(spr.image);
			this.spriteLabel.setVisible(this.visible);
		} else {
			this.spriteLabel.setVisible(false);
		}
	}

	public GTSprite getSprite() {
		return this.sprite;
	}

	public void attachSprite(Game attachTo) {
		attachTo.getFrame().add(this.spriteLabel);
	}

	public void detachSprite(Game attachTo) {
		attachTo.getFrame().remove(this.spriteLabel);
	}

	public void draw() {
		this.spriteLabel.setBounds((int) this.x, (int) this.y, this.sprite.width(), this.sprite.height());
	}

	@Override
	public void step() {

		// Move
		x += hspeed;
		vspeed += gravity;
		y += vspeed;

		// Check for outside room
		if (this.outsideRoomEvents.size() > 0 && (y < 0 || x < 0 || y > this.getWindow().getFrame().getHeight()
				|| x > this.getWindow().getFrame().getWidth())) {
			for (GTObjectEvent.OutsideRoom event : this.outsideRoomEvents) {
				event.setState(x < 0, x > this.getWindow().getFrame().getWidth(), y < 0,
						y > this.getWindow().getFrame().getHeight());
				event.trigger();
			}
		}

		// Draw...
		this.draw();
	}

	// There's no Swing Component associated with an object here.
	public <T extends Component> T getFrame() {
		return null;
	}

	@Override
	public Game getWindow() {
		if (this.room == null) {
			return null;
		} else {
			return this.room.getWindow();
		}
	}
}
