package com.kaleb.gui.screens;

import java.awt.Color;
import java.awt.Graphics;

import com.kaleb.GameTime;
import com.kaleb.content.Bitmaps;
import com.kaleb.graphics.fonts.BitmapFont;
import com.kaleb.gui.ControlManager;
import com.kaleb.gui.controls.BitmapControl;
import com.kaleb.gui.controls.ButtonControl;
import com.kaleb.input.InputManager;

public class TestScreen implements Screen {
	
	private BitmapFont font = new BitmapFont(Bitmaps.testFont, 16, 16);
	private String fps = "";
	
	private ControlManager man = new ControlManager(font);
	private ButtonControl button = new ButtonControl(Bitmaps.startbutton, 500, 400);
	private BitmapControl background = new BitmapControl(Bitmaps.startbackground, 0, 0);
	
	public TestScreen() {
		font.setColor(Color.CYAN);
		
		man.registerControl(background);
		man.registerControl(button);
		button.setVisible(true);
	}

	public Screen update(GameTime gameTime, InputManager manager) {
		man.update(gameTime, manager);
		fps = "FPS: " + gameTime.getFPS();
		
		if (button.buttonReleased()) return new WorldScreen();
		
		return this;
	}

	public void render(Graphics g) {
		font.renderString(g, fps, 0, 0);
		man.render(g);
		if (button.mouseWithin()) font.renderString(g, "HI!", 0, 64);
	}
}
