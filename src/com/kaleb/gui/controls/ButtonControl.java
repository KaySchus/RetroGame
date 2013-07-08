package com.kaleb.gui.controls;

import java.awt.Color;
import java.awt.Graphics;

import com.kaleb.GameTime;
import com.kaleb.input.InputManager;
import com.kaleb.input.Mouse;
import com.kaleb.math.Rectangle;

public class ButtonControl extends BitmapControl {
	private Rectangle bounds;
	private boolean within = false;
	
	private boolean currentState = false;
	private boolean lastState = false;
	
	
	public ButtonControl(int imageID, int x, int y) {
		super(imageID, x, y);
		
		bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	public void update(GameTime gt, InputManager manager) {
		super.update(gt, manager);
		
		if (getParentRef() != null) {
			bounds.update(getXOffset(), getYOffset(), getWidth(), getHeight());
			
			Mouse m = manager.getMouse();
			
			lastState = currentState;
			currentState = false;
			within = false;
			
			if (bounds.contains(m.getX(), m.getY())) {
				within = true;
				
				if (m.left.isDown()) {
					currentState = true;
				}
			}
		}
	}

	public void render(Graphics g) {
		super.render(g);
		
		if (within) {
			g.setColor(new Color(100, 100, 100, 100));
			g.fillRect(getXOffset() + 1, getYOffset() + 1, getWidth() - 2, getHeight() - 2);
		}
		
		if (currentState) {
			g.setColor(new Color(200, 200, 200, 100));
			g.fillRect(getXOffset() + 1, getYOffset() + 1, getWidth() - 2, getHeight() - 2);
		}
	}
	
	public boolean mouseWithin() { 
		return within;
	}
	
	public boolean buttonReleased() {
		return !currentState && lastState;
	}
	
	public boolean buttonPressed() {
		return currentState;
	}
}
