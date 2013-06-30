package com.kaleb.content;

import java.awt.Color;

public class Bitmaps {
	private static BitmapLoader bl = BitmapLoader.getInstance();
	
	public static final int stone = bl.loadBitmap(32, 32, Color.GRAY);
	public static final int grass = bl.loadBitmap(32, 32, Color.GREEN);
	public static final int blue = bl.loadBitmap(32, 32, Color.BLUE);
	public static final int orange = bl.loadBitmap(16, 16, Color.ORANGE);
	public static final int redOver = bl.loadBitmap(32, 32, new Color(255, 0, 0, 100));
	public static final int blueOver = bl.loadBitmap(32, 32, new Color(0, 0, 255, 100));
	
	public static final int door = bl.loadBitmap("door.png");
	public static final int link = bl.loadBitmap("link.png");
	public static final int testFont = bl.loadBitmap("testfont.png");
	public static final int bitmapTest = bl.loadBitmap("test.png");
	public static final int block = bl.loadBitmap("block.png");
	public static final int border = bl.loadBitmap("border.png");
	public static final int yellowborder = bl.loadBitmap("yellowborder.png");
	public static final int glasses = bl.loadBitmap("glasses.png");
	
	//public static final AnimatedBitmap test = new AnimatedBitmap()
}
