package com.kaleb.gui.controls;

import java.awt.Graphics;

import com.kaleb.GameTime;
import com.kaleb.content.BitmapLoader;
import com.kaleb.input.InputManager;

public class BitmapControl extends Control {
	private int bitmapReference;
	
	public BitmapControl(int imageID, int x, int y) {
		super(x, y);
		bitmapReference = imageID;
		
		setWidth(BitmapLoader.getInstance().getBitmap(bitmapReference).getWidth());
		setHeight(BitmapLoader.getInstance().getBitmap(bitmapReference).getHeight());
		
		setVisible(true);
	}

	public void update(GameTime gt, InputManager manager) {
		super.update(gt, manager);
	}

	public void render(Graphics g) {
		super.render(g);
		BitmapLoader.getInstance().getBitmap(bitmapReference).render(g, getXOffset(), getYOffset());
	}
}
