package com.kaleb;

import java.awt.Graphics;

import com.kaleb.graphics.GameCanvas;
import com.kaleb.gui.screens.Screen;
import com.kaleb.gui.screens.TestScreen;
import com.kaleb.input.InputManager;
import com.kaleb.input.Keys;
import com.kaleb.input.Mouse;

public class Game implements Runnable {
	private boolean running = false;
	private Thread t;

	private GameCanvas canvas;
	private GameTime gameTime;
	
	private int screenWidth = 800;
	private int screenHeight = 600;
	private int gameWidth = 400;
	private int gameHeight = 300;
	
	private Screen currentScreen;
	private Screen lastScreen;
	private InputManager manager;
	
	public void init(int sw, int sh, int gw, int gh) {
		gameWidth = gw;
		gameHeight = gh;
		screenWidth = sw;
		screenHeight = sh;
		
		manager = new InputManager(new Keys(), new Mouse());
		canvas = new GameCanvas(this, manager);
		gameTime = new GameTime();
		
		currentScreen = new TestScreen();
	}
	
	public void start() {
		if (running == false) {
			running = true;
			t = new Thread(this);
			t.run();
		}
	}
	
	public void stop() {
		if (running) {
			running = false;
		}
	}
	
	public void run() {
		gameTime.init();
		while(running) {
			manager.update();
			gameTime.update();
			update();
			canvas.render();
		}
		
		System.out.println("Done");
		System.exit(0);
	}
	
	public void update() {
		if (manager.getKeys().exit.keyPressed()) {
			stop();
		}
		
		lastScreen = currentScreen;
		currentScreen = currentScreen.update(gameTime, manager);
		
	}
	
	public void render(Graphics g) {
		if (lastScreen == currentScreen) {
			currentScreen.render(g);
		}
	}
	
	public GameCanvas getCanvas() { return canvas; }
	
	public int getScreenWidth() { return screenWidth; }
	public int getScreenHeight() { return screenHeight; }
	public int getGameWidth() { return gameWidth; }
	public int getGameHeight() { return gameHeight; }
}
