package com.icemetalpunk.gametheory.sprites;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class GTSprite {
	private final static List<GTSprite> spriteList = new ArrayList<GTSprite>();

	public final ImageIcon image;
	public final int id;

	private GTSprite(ImageIcon i, int id) {
		this.image = i;
		this.id = id;
	}

	public int width() {
		return this.image.getIconWidth();
	}

	public int height() {
		return this.image.getIconHeight();
	}

	public static GTSprite load(URL res) {
		GTSprite spr = new GTSprite(new ImageIcon(res), spriteList.size());
		spriteList.add(spr);
		return spr;
	}

}
