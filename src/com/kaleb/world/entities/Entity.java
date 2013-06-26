package com.kaleb.world.entities;

import java.awt.Graphics;

import com.kaleb.GameTime;
import com.kaleb.content.BitmapLoader;
import com.kaleb.graphics.Bitmap;
import com.kaleb.input.InputManager;
import com.kaleb.math.Rectangle;
import com.kaleb.world.World;

public class Entity {
	private int imageID;
	private World world;
	
	private double xPos;
	private double yPos;
	
	private Rectangle bounds;
	
	private boolean shouldRender = true;
	
	public Entity(World w, int i, int x, int y) {
		world = w;
		imageID = i;
		xPos = x;
		yPos = y;
		
		bounds = new Rectangle((int) xPos, (int) yPos, 16, 16);
	}

	public void update(GameTime gt, InputManager mag) {
		bounds.update((int) xPos, (int) yPos);
	}
	
	public void render(Graphics g, int x, int y) {
		if (shouldRender) {
			BitmapLoader.getInstance().getBitmap(imageID).render(g, x, y);
		}
	}
	
	public World getWorld() { return world; }
	public void setX(int val) { xPos = val; }
	public void setY(int val) { yPos = val; }
	public void incX(double val) { xPos += val; }
	public void incY(double val) { yPos += val; }
	public int getX() { return (int) xPos; }
	public int getY() { return (int) yPos; }
	public Rectangle getBounds() { return bounds; }
	public Bitmap getBitmap() { return BitmapLoader.getInstance().getBitmap(imageID); }
}
