package com.kaleb.gui;

public abstract class Control {
	private int xPos;
	private int yPos;
	
	private int width;
	private int height;
	
	private boolean hasFocus;
	
	private String name;
	
	public Control() { }
	
	public Control(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	public Control(int x, int y, int w, int h) {
		xPos = x;
		yPos = y;
		width = w;
		height = h;
	}
	
	public int getX() { return xPos; }
	public int getY() { return yPos; }
	public void setX(int val) { xPos = val; }
	public void setY(int val) { yPos = val; }
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public boolean hasFocus() { return hasFocus; }
	public void setFocus(boolean val) { hasFocus = val; }
	
	public String getName() { return name; }
	public void setName(String val) { name = val; }
}
