package com.kaleb.world.entities;

import java.awt.Graphics;

import com.kaleb.content.BitmapLoader;

public class Entity {
	private int imageID;
	
	private int xPos;
	private int yPos;
	
	public Entity(int i, int x, int y) {
		imageID = i;
		xPos = x;
		yPos = y;
	}
	
	public void render(Graphics g) {
		BitmapLoader.getInstance().getBitmap(imageID).render(g, xPos, yPos);
	}
	
	public void setX(int val) { xPos = val; }
	public void setY(int val) { yPos = val; }
	public int getX() { return xPos; }
	public int getY() { return yPos; }
}
