package com.icemetalpunk.gametheory.sprites;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Sprite {
	private final static List<Sprite> spriteList = new ArrayList<Sprite>();

	public final ImageIcon image;
	public final int id;

	private Sprite(ImageIcon i, int id) {
		this.image = i;
		this.id = id;
	}

	public int width() {
		return this.image.getIconWidth();
	}

	public int height() {
		return this.image.getIconHeight();
	}

	public static Sprite load(URL res) {
		Sprite spr = new Sprite(new ImageIcon(res), spriteList.size());
		spriteList.add(spr);
		return spr;
	}

}
