package com.kaleb.math;

public class Rectangle {
	public static final int RECTANGLE_LEFT = 1;
	public static final int RECTANGLE_TOP = 2;
	public static final int RECTANGLE_RIGHT = 3;
	public static final int RECTANGLE_BOTTOM = 4;
	
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	
	public Rectangle(int x, int y, int w, int h) {
		update(x, y, w, h);
	}
	
	public void update(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	public void update(int x, int y, int w, int h) {
		xPos = x;
		yPos = y;
		width = w;
		height = h;
	}
	
	public boolean touching(int side, Rectangle rect) {
		if (side == RECTANGLE_LEFT) {
			if (xPos + width == rect.getX() && ((yPos >= rect.getY() && yPos <= rect.getY() + rect.getHeight()) || 
					yPos + height >= rect.getY() && yPos + height <= rect.getY() + rect.getHeight())) return true;
		}
		
		if (side == RECTANGLE_TOP && ((xPos >= rect.getX() && xPos <= rect.getX() + rect.getWidth()) ||
				xPos + width >= rect.getX() && xPos + width <= rect.getX() + rect.getWidth())) {
			if (yPos + height == rect.getY()) return true;
		}
		
		if (side == RECTANGLE_RIGHT && ((yPos >= rect.getY() && yPos <= rect.getY() + rect.getHeight()) || 
				yPos + height >= rect.getY() && yPos + height <= rect.getY() + rect.getHeight())) {
			if (xPos == rect.getX() + rect.getWidth()) return true;
		}
		
		if (side == RECTANGLE_BOTTOM && ((xPos >= rect.getX() && xPos <= rect.getX() + rect.getWidth()) ||
				xPos + width >= rect.getX() && xPos + width <= rect.getX() + rect.getWidth())) {
			if (yPos == rect.getY() + rect.getHeight()) return true;
		}
		
		return false;
	}
	
	private int getLineLength(int x1, int width1, int x2, int width2) {
		if (x1 < x2) {
			if (width1 > width2) return width2;
			return width1 - (x2 - x1);
		}
		
		else {
			if (width1 > width2) return width2 - (x1 - x2);
			return width1 - (x1 - x2);
		}
	}
	
	public int intSide(Rectangle rect) {
		int lineY = getLineLength(getY(), getHeight(), rect.getY(), rect.getHeight());
		int lineX = getLineLength(getX(), getWidth(), rect.getX(), rect.getWidth());
		if (lineX > lineY) {
			return 1;
		}
		return 2;
	}
	
	public boolean intersects(Rectangle rect) {
		if ((xPos > rect.getX() && xPos < rect.getX() + rect.getWidth()) || 
				(xPos + width > rect.getX() && xPos + width < rect.getX() + rect.getWidth())) {
			if ((yPos > rect.getY() && yPos < rect.getY() + rect.getHeight()) ||
					(yPos + height > rect.getY() && yPos + height < rect.getY() + rect.getHeight())) {
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
	
	public void setX(int val) { xPos = val; }
	public void setY(int val) { yPos = val; }
	public int getX() { return xPos; }
	public int getY() { return yPos; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public String toString() { 
		return "Rect<X: " + xPos + ", Y: " + yPos + ", W: " + width + ", H: " + height + ">";
	}
}
