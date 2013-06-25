package com.kaleb.gui.screens;

import java.awt.Graphics;
import java.util.Random;

import com.kaleb.GameTime;
import com.kaleb.content.Bitmaps;
import com.kaleb.graphics.fonts.BitmapFont;
import com.kaleb.input.InputManager;
import com.kaleb.math.QuadTree;
import com.kaleb.math.Rectangle;

public class TestScreen implements Screen {
	
	private BitmapFont font = new BitmapFont(Bitmaps.testFont, 16, 16);
	private String fps = "";
	private QuadTree testing;
	private Random rand;
	
	public TestScreen() {
		rand = new Random();
		
		testing = new QuadTree(1, new Rectangle(0, 0, 800, 600));
		testing.insert(new Rectangle(rand.nextInt(800), rand.nextInt(600), 16, 16));
		
		System.out.println(testing.getObjects().size());
	}

	public Screen update(GameTime gameTime, InputManager manager) {
		if (manager.getKeys().enter.keyPressed()) {
			return new WorldScreen();
		}
		
		if (manager.getKeys().shift.keyPressed()) {
			testing.insert(new Rectangle(16, 16, 16, 16));
		}
		
		fps = "FPS: " + gameTime.getFPS();
		
		return this;
	}

	public void render(Graphics g) {
		font.renderString(g, fps, 0, 0);
	}
}
