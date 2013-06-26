package com.kaleb.input;

import java.util.ArrayList;
import java.util.List;

public class Mouse {
	public class MouseButton {
		private boolean nextState = false;
		private boolean isDown = false;
		private boolean wasDown = false;
		
		public MouseButton() {
			buttons.add(this);
		}
		
		public void toggle(boolean setting) {
			nextState = setting;
		}
		
		public void update() {
			wasDown = isDown;
			isDown = nextState;
		}
		
		public void release() {
			nextState = false;
		}
		
		public boolean isDown() { return isDown; }
		public boolean wasDown() { return wasDown; }
		public boolean keyPressed() { return !wasDown && isDown; }
		public boolean keyReleased() { return wasDown && !isDown; }
	}

	private List<MouseButton> buttons = new ArrayList<MouseButton>();
	
	public MouseButton left = new MouseButton();
	public MouseButton right = new MouseButton();
	
	private int xPos;
	private int yPos;
	
	private int lastX;
	private int lastY;
	
	public void update() {	
		lastX = xPos;
		lastY = yPos;
		
		for (MouseButton mb : buttons) {
			mb.update();
		}
	}
	
	public void move(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	public List<MouseButton> getButtons() { return buttons; }
	public int getX() { return xPos; }
	public int getY() { return yPos; }
	public int getDeltaX() { return xPos - lastX; }
	public int getDeltaY() { return yPos - lastY; }
}
