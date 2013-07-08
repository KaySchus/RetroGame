package com.kaleb.gui.controls;

import java.awt.Graphics;

import com.kaleb.GameTime;
import com.kaleb.gui.ControlManager;
import com.kaleb.input.InputManager;

public abstract class Control {
	private int xPos;
	private int yPos;
	
	private int xOffs;
	private int yOffs;
	
	private int width;
	private int height;
	
	private boolean hasFocus;
	private boolean isVisible;
	
	private String name;
	
	private ControlManager parentRef;
	
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
	
	public int getXOffset() { return xOffs; }
	public int getYOffset() { return yOffs; }
	
	public int getWidth() { return width; }
	public void setWidth(int val) { width = val; }
	public int getHeight() { return height; }
	public void setHeight(int val) { height = val; }
	
	public boolean hasFocus() { return hasFocus; }
	public void setFocus(boolean val) { hasFocus = val; }
	
	public boolean isVisible() { return isVisible; }
	public void setVisible(boolean val) { isVisible = val; }
	
	public String getName() { return name; }
	public void setName(String val) { name = val; }
	
	public ControlManager getParentRef() { return parentRef; }
	public void setParent(ControlManager c) { parentRef = c; }

	public void update(GameTime gt, InputManager manager) {
		if (parentRef == null) return;
		else {
			xOffs = parentRef.getXOffset() + getX();
			yOffs = parentRef.getYOffset() + getY();
		}
	}
	
	public void render(Graphics g) {
		if (parentRef == null) return;
	}
}
