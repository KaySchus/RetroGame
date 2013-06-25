package com.kaleb.graphics.fonts;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.kaleb.content.BitmapLoader;
import com.kaleb.graphics.Bitmap;

public class BitmapFont {
	public int imageID;
	
	private int tilesWide;
	private int tilesHigh;
	private int tileWidth;
	private int tileHeight;
	
	private BufferedImage coloredImage;
	private Color currentCol;
	
	public BitmapFont(int i, int tw, int th) {
		imageID = i;
		tilesWide = tw;
		tilesHigh = th;
		
		int w = BitmapLoader.getInstance().getBitmap(imageID).getWidth();
		int h = BitmapLoader.getInstance().getBitmap(imageID).getHeight();
		
		tileWidth = w / tilesWide;
		tileHeight = h / tilesHigh;
	}
	
	public int getHeight() { return tileHeight; }
	public int getStringLength(String string) {
		return string.length() * tileWidth;
	}
	
	public void setColor(Color col) {
		currentCol = col;
		int color = currentCol.getRGB();
		coloredImage = BitmapLoader.getInstance().getBitmap(imageID).getImage();
		
		for (int yy = 0; yy < coloredImage.getHeight(); yy++) {
			for (int xx = 0; xx < coloredImage.getHeight(); xx++) {
				coloredImage.setRGB(xx, yy, coloredImage.getRGB(xx, yy) & color);
			}
		}
	}
	
	public void renderString(Graphics g, String string, int x, int y) {
		Bitmap im = BitmapLoader.getInstance().getBitmap(imageID);
		for (int xPos = 0; xPos < string.length(); xPos++) {
			int charID = string.charAt(xPos);
			BufferedImage i = im.getSubImage((charID % tileWidth) * tileWidth, (charID / tileWidth) * tileWidth, tileWidth, tileHeight);
			g.drawImage(i, x + (xPos * tileWidth), y, null);
		}
	}
	
	public void renderColoredString(Graphics g, String string, int x, int y, Color col) {
		if (col != currentCol) setColor(col);
		
		for (int xPos = 0; xPos < string.length(); xPos++) {
			int charID = string.charAt(xPos);
			BufferedImage i = coloredImage.getSubimage((charID % tileWidth) * tileWidth, (charID / tileWidth) * tileWidth, tileWidth, tileHeight);
			
			g.drawImage(i, x + (xPos * tileWidth), y, null);
		}
	}
}
