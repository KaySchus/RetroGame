package com.kaleb;

import java.awt.Graphics;

import com.kaleb.graphics.GameCanvas;
import com.kaleb.gui.screens.Screen;
import com.kaleb.gui.screens.TestScreen;
import com.kaleb.input.InputManager;
import com.kaleb.input.Keys;

public class Game implements Runnable {
	private boolean running = false;
	private Thread t;

	private GameCanvas canvas;
	private GameTime gameTime;
	private int gameWidth = 800;
	private int gameHeight = 600;
	
	private Screen currentScreen;
	private InputManager manager;
	
	public void init() {
		manager = new InputManager(new Keys());
		canvas = new GameCanvas(this, gameWidth, gameHeight, manager);
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
		
		currentScreen = currentScreen.update(gameTime, manager);
		
	}
	
	public void render(Graphics g) {
		currentScreen.render(g);
	}
	
	public GameCanvas getCanvas() { return canvas; }
}
