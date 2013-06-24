package com.kaleb.world.entities;

import java.awt.Graphics;

import com.kaleb.GameTime;
import com.kaleb.content.BitmapLoader;
import com.kaleb.graphics.Bitmap;
import com.kaleb.input.InputManager;

public class Entity {
	private int imageID;
	
	private int xPos;
	private int yPos;
	
	private boolean shouldRender = true;
	
	public Entity(int i, int x, int y) {
		imageID = i;
		xPos = x;
		yPos = y;
	}
	
	public void update(GameTime gt, InputManager mag) {
	}
	
	public void render(Graphics g, int x, int y) {
		if (shouldRender) {
			BitmapLoader.getInstance().getBitmap(imageID).render(g, x, y);
		}
	}
	
	public void setX(int val) { xPos = val; }
	public void setY(int val) { yPos = val; }
	public int getX() { return xPos; }
	public int getY() { return yPos; }
	public Bitmap getBitmap() { return BitmapLoader.getInstance().getBitmap(imageID); }
}
