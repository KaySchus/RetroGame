package com.kaleb.world.tiles;

import java.util.HashMap;
import java.util.Map;

import com.kaleb.content.Bitmaps;

public class Tiles {
	public Map<Integer, Tile> tiles = new HashMap<Integer, Tile>();
	
    public Tile stone = new Tile(Bitmaps.stone, 1);
    public Tile grass = new Tile(Bitmaps.grass, 2);
    public Tile bad = new Tile(Bitmaps.bitmapTest, 4);
	
	public Tiles() {
		registerTile(stone);
		registerTile(grass);
		registerTile(bad);
	}
	
	public boolean registerTile(Tile tile) {
		if (tile != null) {
			if (tiles.containsKey(tile.getID())) {
				System.out.println("Tile already contained with ID: " + tile.getID());
				System.out.println("Not registering tile.  Enable overriding.");
				return false;
			}
			
			tiles.put(tile.getID(), tile);
		}
		
		return false;
	}
	
	public Map<Integer, Tile> getTileMap() { return tiles; }
}
