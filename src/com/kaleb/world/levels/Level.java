package com.kaleb.world.levels;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.kaleb.content.BitmapLoader;
import com.kaleb.world.tiles.Tiles;

public class Level {
	public static final int blockSize = 32;
	
	private int tilesWide;
	private int tilesHigh;
	
	private int[][] map;
	private byte[][] meta;
	
	public Level(String filename, Tiles tiles) {
		BufferedImage im = BitmapLoader.getInstance().loadImage(filename);
		
		tilesWide = im.getWidth();
		tilesHigh = im.getHeight();
		
		map = new int[tilesWide][tilesHigh];
		meta = new byte[tilesWide][tilesHigh];
		
		for (int y = 0; y < im.getHeight(); y++) {
			for (int x = 0; x < im.getWidth(); x++) {
				meta[x][y] = 0;
				if (im.getRGB(x, y) == Color.red.getRGB()) map[x][y] = tiles.stone.getID();
				if (im.getRGB(x, y) == Color.blue.getRGB()) map[x][y] = tiles.grass.getID();
				if (im.getRGB(x, y) == Color.green.getRGB()) map[x][y] = tiles.door.getID();
			}
		}
	}
	
	public void setTileWithMeta(int tile, byte m, int x, int y) {
		setTile(tile, x, y);
		if (x >= 0 && x <= tilesWide && y >= 0 && y <= tilesHigh) meta[x][y] = m;
	}
	
	public void setTile(int tile, int x, int y) {
		if (x >= 0 && x <= tilesWide && y >= 0 && y <= tilesHigh) map[x][y] = tile;
	}
	
	public int[][] getMap() { return map; }
	public byte[][] getMeta() { return meta; }
	public int getTilesWide() { return tilesWide; }
	public int getTilesHigh() { return tilesHigh; }
	public int getBlockSize() { return blockSize; }
}
