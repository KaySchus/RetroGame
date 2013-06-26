package com.kaleb;

public class GameTime {
	public final int NSCONSTANT = 1000000000;
	public final int OPTIMAL_FPS = 60;
	public final long OPTIMAL_TIME = NSCONSTANT / OPTIMAL_FPS;
	private long lastLoopTime;
	private long now;
	private long updateLength;
	private double deltaTime;
	
	private long totalFrameTime = 0;
	private long elapsedTimeS = 0;
	
	private int frames = 0;
	private int lastFrames = 0;
	
	public void init() {
		lastLoopTime = System.nanoTime();
		now = System.nanoTime();
		updateLength = now - lastLoopTime;
		deltaTime = updateLength / OPTIMAL_TIME;
	}
	
	public double update() {
		now = System.nanoTime();
		updateLength = now - lastLoopTime;
		lastLoopTime = now;
		deltaTime = (double) updateLength / (double) NSCONSTANT;
		
		totalFrameTime += updateLength;
		frames++;
		
		if (totalFrameTime >= 1000000000) {
			totalFrameTime = 0;
			elapsedTimeS++;
			lastFrames = frames;
			frames = 0;
		}
		
		return deltaTime;
	}
	
	public int getFPS() { return lastFrames; }
	public double getDelta() { return deltaTime; }
	public long getElapsedTime() { return elapsedTimeS; }
	
	public long currentTime() { return System.nanoTime(); }
	public long lastLoopTime() { return lastLoopTime; }
}
