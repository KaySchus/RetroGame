package com.kaleb;

import java.awt.Graphics;

import com.kaleb.graphics.GameCanvas;
import com.kaleb.input.InputManager;
import com.kaleb.input.Keys;
import com.kaleb.world.World;

public class Game implements Runnable {
	private boolean running = false;
	private Thread t;

	private GameCanvas canvas;
	private GameTime gameTime;
	private int gameWidth = 800;
	private int gameHeight = 600;
	
	private World world;
	private InputManager manager;
	
	public void init() {
		manager = new InputManager(new Keys());
		canvas = new GameCanvas(this, gameWidth, gameHeight, manager);
		gameTime = new GameTime();
		
		world = new World();
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
			
			try {
				long timeToSleep = ((gameTime.lastLoopTime() - gameTime.currentTime() + gameTime.OPTIMAL_TIME) / 1000000);
				if (timeToSleep >= 0) {
					Thread.sleep(timeToSleep);
				} else {
					Thread.sleep(1);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Done");
		System.exit(0);
	}
	
	public void update() {
		if (manager.getKeys().exit.keyPressed()) {
			stop();
		}
		world.update(gameTime, manager);
		
	}
	
	public void render(Graphics g) {
		world.render(g);
	}
	
	public GameCanvas getCanvas() { return canvas; }
}
