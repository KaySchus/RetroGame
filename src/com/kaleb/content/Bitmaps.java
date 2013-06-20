package com.kaleb.content;

import java.awt.Color;

public class Bitmaps {
	private static BitmapLoader bl = BitmapLoader.getInstance();
	public static final int bitmapGreen = bl.loadBitmap(32, 32, Color.GREEN);
	public static final int bitmapTest = bl.loadBitmap("test.png");
}
