package com.kaleb.content;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.kaleb.graphics.Bitmap;

public class BitmapLoader {
	private static BitmapLoader instance = null;
	private Map<Integer, Bitmap> bitmaps;
	private int count = 0;
	
	public static BitmapLoader getInstance() { 
		if (instance == null) {
			instance = new BitmapLoader();
		}
		
		return instance;
	}
	
	private BitmapLoader() {
		bitmaps = new HashMap<Integer, Bitmap>();
	}
	
	public Bitmap getBitmap(int index) {
		Bitmap b = bitmaps.get(index);
		if (b != null) {
			return b;
		}
		
		System.out.println("No image referenced at index " + index +".");
		return null;
	}
	
	public int loadBitmap(String filename) {
		try {
			BufferedImage im = ImageIO.read(new File(filename));
			
			Bitmap b = new Bitmap(im);
			bitmaps.put(count, b);
			count++;
			return count - 1;
		} catch (IOException e) {
			System.out.println("Error loading image. Error below.");
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public int loadBitmap(int width, int height, Color col) {
		Bitmap b = new Bitmap(width, height, col);
		bitmaps.put(count,  b);
		count++;
		return count - 1;
	}
	
	public int loadBitmap(BufferedImage im) {
		if (im != null) {
			Bitmap b = new Bitmap(im);
			bitmaps.put(count, b);
			count++;
			
			return count - 1;
		}
		
		return -1;
	}
}
