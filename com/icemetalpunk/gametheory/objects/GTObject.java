package com.icemetalpunk.gametheory.objects;

import java.util.concurrent.atomic.AtomicLong;

import javax.swing.JLabel;

import com.icemetalpunk.gametheory.events.GTStepHandler;
import com.icemetalpunk.gametheory.events.objectevents.GTObjectEventHandler;
import com.icemetalpunk.gametheory.events.objectevents.GTOutsideRoomEvent;
import com.icemetalpunk.gametheory.guis.Game;
import com.icemetalpunk.gametheory.sprites.Sprite;

public abstract class GTObject implements GTStepHandler, GTObjectEventHandler {
	private static AtomicLong idCounter = new AtomicLong();
	public double x, y, hspeed, vspeed, gravity;
	public final long id;
	private Sprite sprite;
	private final JLabel spriteLabel;
	public boolean visible;
	private GTOutsideRoomEvent outsideRoomEvent;
	public Game window;

	public GTObject(int xpos, int ypos) {
		this.x = xpos;
		this.y = ypos;
		this.hspeed = 0;
		this.vspeed = 0;
		this.gravity = 0;
		this.visible = true;
		this.window = null;
		this.spriteLabel = new JLabel();
		this.spriteLabel.setVisible(false);
		this.id = idCounter.getAndIncrement();
	}

	public GTObject() {
		this(0, 0);
	}

	public void setWindow(Game w) {
		this.window = w;
	}

	@Override
	public String toString() {
		return String.valueOf(this.id);
	}

	public void setSprite(Sprite spr) {
		this.sprite = spr;
		if (spr != null) {
			this.spriteLabel.setIcon(spr.image);
			this.spriteLabel.setVisible(this.visible);
		} else {
			this.spriteLabel.setVisible(false);
		}
	}

	public Sprite getSprite() {
		return this.sprite;
	}

	public void attachSprite(Game attachTo) {
		attachTo.add(this.spriteLabel);
	}

	public void detachSprite(Game attachTo) {
		attachTo.remove(this.spriteLabel);
	}

	public void draw() {
		this.spriteLabel.setBounds((int) this.x, (int) this.y, this.sprite.width(), this.sprite.height());
	}

	@Override
	public void attachListener(GTOutsideRoomEvent event) {
		this.outsideRoomEvent = event;
	}

	@Override
	public void step() {
		x += hspeed;
		vspeed += gravity;
		y += vspeed;

		if (this.outsideRoomEvent != null
				&& (y < 0 || x < 0 || y > this.window.getHeight() || x > this.window.getWidth())) {
			this.outsideRoomEvent.setState(x < 0, x > this.window.getWidth(), y < 0, y > this.window.getHeight());
			this.outsideRoomEvent.trigger();
		}

		this.draw();
	}
}
