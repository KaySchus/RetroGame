package com.kaleb.world.tiles;

import java.util.HashMap;
import java.util.Map;

import com.kaleb.content.Bitmaps;

public class Tiles {
	public Map<Integer, Tile> tiles = new HashMap<Integer, Tile>();
	
	public Tile green = new Tile(Bitmaps.bitmapGreen, 1);
    public Tile red = new Tile(Bitmaps.bitmapTest, 2);
	
	public Tiles() {
		tiles.put(green.getID(), green);
		tiles.put(red.getID(), red);
	}
	
	public Map<Integer, Tile> getTileMap() { return tiles; }
}
