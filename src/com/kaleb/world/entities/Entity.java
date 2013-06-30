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
	
	private int width;
	private int height;
	
	private int xOffs;
	private int yOffs;
	
	private int tileX;
	private int tileY;
	
	private Rectangle bounds;
	
	private boolean shouldRender = true;
	
	public Entity(World w, int i, int x, int y, int ww, int hh) {
		world = w;
		imageID = i;
		
		xPos = x;
		yPos = y;
		
		width = ww;
		height = hh;
		
		xOffs = 0;
		yOffs = 0;
		
		bounds = new Rectangle((int) xPos, (int) yPos, ww, hh);
	}
	
	public Entity(World w, int i, int x, int y, int xx, int yy, int ww, int hh) {
		world = w;
		imageID = i;
		
		xPos = x;
		yPos = y;
		
		width = ww;
		height = hh;
		
		xOffs = xx;
		yOffs = yy;
		
		bounds = new Rectangle((int) xPos + xOffs, (int) yPos + yOffs, ww, hh);
	}

	public void update(GameTime gt, InputManager mag) {
		tileX = (int) (xPos + (width / 2)) / 32;
		tileY = (int) (yPos + (height / 2)) / 32;
		bounds.update((int) xPos + xOffs, (int) yPos + yOffs);
	}
	
	public void render(Graphics g, int x, int y) {
		if (shouldRender) {
			BitmapLoader.getInstance().getBitmap(imageID).render(g, x, y);
		}
	}
	
	public World getWorld() { return world; }
	
	public void setX(double val) { 
		xPos = val; 
		bounds.update((int) xPos + xOffs, (int) yPos + yOffs);
	}
	
	public void setY(double val) { 
		yPos = val; 
		bounds.update((int) xPos + xOffs, (int) yPos + yOffs);
	}
	
	public void incX(double val) { setX(xPos + val); }
	public void incY(double val) { setY(yPos + val); }
	
	public int getX() { return (int) xPos; }
	public int getY() { return (int) yPos; }
	
	public int getXOffs() { return xOffs; }
	public int getYOffs() { return yOffs; }
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public int getTileX() { return tileX; }
	public int getTileY() { return tileY; }
	
	public Rectangle getBounds() { return bounds; }
	public Bitmap getBitmap() { return BitmapLoader.getInstance().getBitmap(imageID); }
}
