package com.icemetalpunk.gametheory.guis;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

// FIXME: Force draw below everything.
public class GTBackground extends JPanel {

	private static final long serialVersionUID = 3887191903097969199L;

	BufferedImage image = null;

	public GTBackground() {
	}

	public GTBackground(BufferedImage img) {
		this.setImage(img);
	}

	public GTBackground(URL res) throws IOException {
		this(ImageIO.read(res));
	}

	public GTBackground(String path) throws IOException {
		this(ImageIO.read(new File(path)));
	}

	public void setImage(BufferedImage img) {
		this.image = img;
	}

	public void setImage(URL res) throws IOException {
		this.setImage(ImageIO.read(res));
	}

	public void setImage(String path) throws IOException {
		this.setImage(ImageIO.read(new File(path)));
	}

	@Override
	protected void paintComponent(Graphics g) {
		int w = this.getWidth(), h = this.getHeight();
		int tw = this.image.getWidth(), th = this.image.getHeight();
		for (int xx = 0; xx < w; xx += tw) {
			for (int yy = 0; yy < h; yy += th) {
				g.drawImage(this.image, xx, yy, this);
			}
		}
	}

}
