package com.kaleb.gui.controls;

import java.awt.Graphics;

import com.kaleb.GameTime;
import com.kaleb.input.InputManager;
import com.kaleb.input.Mouse;
import com.kaleb.math.Rectangle;

public class DraggableControl extends BitmapControl {
	private Rectangle bounds;

	public DraggableControl(int imageID, int x, int y) {
		super(imageID, x, y);
		
		bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	public void update(GameTime gt, InputManager manager) {
		super.update(gt, manager);
		bounds.update(getXOffset(), getYOffset(), getWidth(), getHeight());
		
		Mouse m = manager.getMouse();
		
		if (bounds.contains(m.getX(), m.getY())) {
			if (m.left.keyPressed()) {
			}
			
			if (m.left.isDown()) {
				setX(getX() + m.getDeltaX());
				setY(getY() + m.getDeltaY());
			}
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
	}
}
