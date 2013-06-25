package com.kaleb.math;

public class Rectangle {
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	
	public Rectangle(int x, int y, int w, int h) {
		update(x, y, w, h);
	}
	
	public void update(int x, int y, int w, int h) {
		xPos = x;
		yPos = y;
		width = w;
		height = h;
	}
	
	public boolean intersects(Rectangle rect) {
		if ((xPos >= rect.getX() && xPos <= rect.getX() + rect.getWidth()) || 
				(xPos + width >= rect.getX() && xPos + width <= rect.getX() + rect.getWidth())) {
			if ((yPos >= rect.getY() && yPos <= rect.getY() + rect.getHeight()) ||
					(yPos + height >= rect.getY() && yPos + height <= rect.getY() + rect.getHeight())) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean contains(Rectangle rect) {
		if (rect.getX() >= xPos && rect.getX() + rect.getWidth() <= xPos + width) {
			if (rect.getY() >= yPos && rect.getY() + rect.getHeight() <= yPos + height) {
				return true;
			}
		}
		
		return false;
	}
	
	public int getX() { return xPos; }
	public int getY() { return yPos; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public String toString() { 
		return "Rect<X: " + xPos + ", Y: " + yPos + ", W: " + width + ", H: " + height + ">";
	}
}
