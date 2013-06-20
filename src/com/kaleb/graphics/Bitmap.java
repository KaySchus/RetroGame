package com.kaleb.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bitmap {
	private BufferedImage image;
	private int width = 0;
	private int height = 0;
	private int ID;
	
	public Bitmap() {
		image = new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
		
		width = 0;
		height = 0;
	}
	
	public Bitmap(BufferedImage im) {
		image = im;
		
		width = im.getWidth();
		height = im.getHeight();
	}
	
	public Bitmap(int w, int h, Color col) {
		width = w; height = h;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				image.setRGB(x, y, col.getRGB());
			}
		}
	}
	
	public int getID() { return ID; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(image, x, y, null);
	}
}
