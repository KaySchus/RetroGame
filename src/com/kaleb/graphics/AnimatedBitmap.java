package com.kaleb.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.kaleb.GameTime;
import com.kaleb.content.BitmapLoader;

public class AnimatedBitmap {
	private int fps;
	private int bitmapReference;
	
	private int xStart;
	private int yStart;
	
	private int frameWidth;
	private int frameHeight;
	
	private int frames;
	private double secondsPerFrame;
	private int position = 0;
	private double time;
	
	private boolean repeat;
	
	public AnimatedBitmap(int im, int f, int x, int y, int w, int h, int num) {
		bitmapReference = im;
		fps = f;
		frames = num;
		xStart = x;
		yStart = y;
		frameWidth = w;
		frameHeight = h;
		
		secondsPerFrame = 1.0 / fps;
		position = 0;
	}
	
	public void update(GameTime gt) {
		time += gt.getDelta();
		
		if (time >= secondsPerFrame) {
			time = 0;
			position++;
			if (position > frames - 1) position = 0;
		}
	}
	
	public void render(Graphics g, int x, int y) {
		Bitmap i = BitmapLoader.getInstance().getBitmap(bitmapReference);
		BufferedImage im = i.getSubImage(xStart + (position * frameWidth), yStart, frameWidth, frameHeight);
		
		g.drawImage(im, x, y, null);
	}
	
	public int getImage() { return bitmapReference; }
	public int getFPS() { return fps; }
	public int getFrames() { return frames; }
	public int getXStart() { return xStart; }
	public int getYStart() { return yStart; }
	public int getFrameWidth() { return frameWidth; }
	public int getFrameHeight() { return frameHeight; }
	public int getPosition() { return position; }
	public boolean repeat() { return repeat; }
}
