package com.kaleb.gui.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.kaleb.GameTime;
import com.kaleb.content.BitmapLoader;
import com.kaleb.content.Bitmaps;
import com.kaleb.graphics.fonts.BitmapFont;
import com.kaleb.input.InputManager;
import com.kaleb.math.Rectangle;

public class TestScreen implements Screen {
	
	private BitmapFont font = new BitmapFont(Bitmaps.testFont, 16, 16);
	private String fps = "";
	private Random rand;
	
	private Rectangle t1 = new Rectangle(240, 240, 32, 32);
	private Rectangle t2 = new Rectangle(316, 316, 32, 32);
	
	private boolean flag = false;
	private boolean modifier = false;
	private int touching = 0;
	
	public TestScreen() {
		rand = new Random();
		font.setColor(Color.CYAN);
	}

	public Screen update(GameTime gameTime, InputManager manager) {
		flag = false;
		touching = 0;
		if (manager.getKeys().enter.keyPressed()) {
			return new WorldScreen();
		}
		
		if (manager.getKeys().shift.keyPressed()) {
			modifier = !modifier;
		}
		
		if (manager.getKeys().left.isDown() && !t1.touching(3, t2)) {
			if (!modifier) t1.setX(t1.getX() - 1);
			else if (rand.nextInt(5) == 0) t1.setX(t1.getX() - 1);
		}
		
		if (manager.getKeys().right.isDown() && !t1.touching(1, t2)) {
			if (!modifier) t1.setX(t1.getX() + 1);
			else if (rand.nextInt(5) == 0) t1.setX(t1.getX() + 1);
		}
		
		if (manager.getKeys().up.isDown() && !t1.touching(4, t2)) {
			if (!modifier) t1.setY(t1.getY() - 1);
			else if (rand.nextInt(5) == 0) t1.setY(t1.getY() - 1);
		}
		
		if (manager.getKeys().down.isDown() && !t1.touching(2, t2)) {
			if (!modifier) t1.setY(t1.getY() + 1);
			else if (rand.nextInt(5) == 0) t1.setY(t1.getY() + 1);
		}
		
		fps = "FPS: " + gameTime.getFPS();
		
		if (t1.touching(1, t2)) touching = 1;
		if (t1.touching(2, t2)) touching = 2;
		if (t1.touching(3, t2)) touching = 3;
		if (t1.touching(4, t2)) touching = 4;
		
		if (t1.intersects(t2)) {
			flag = true;
		}
		
		return this;
	}

	public void render(Graphics g) {
		font.renderString(g, fps, 0, 0);
		if (flag) font.renderString(g, "Intersecting", 0, 16);
		if (touching != 0) font.renderString(g, "Touching: " + touching, 0, 32);
		if (modifier) font.renderString(g, "Slow", 0, 48);
		else font.renderString(g, "Fast", 0, 48);
		
		BitmapLoader.getInstance().getBitmap(Bitmaps.redOver).render(g, t1.getX(), t1.getY());
		BitmapLoader.getInstance().getBitmap(Bitmaps.blueOver).render(g, t2.getX(), t2.getY());
	}
}
