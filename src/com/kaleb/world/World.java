package com.kaleb.world;

import java.awt.Graphics;

import com.kaleb.GameTime;
import com.kaleb.input.InputManager;
import com.kaleb.world.tiles.Tiles;

public class World {
	private Tiles tiles;
	private int[][] map = new int[25][25];
	
	public World() {
		tiles = new Tiles();
		
		for (int y = 0; y < 25; y++) {
			for (int x = 0; x < 25; x++) {
				map[x][y] = 2;
			}
		}
	}
	
	public void update(GameTime gameTime, InputManager manager) {
	}
	
	public void render(Graphics g) {
		for (int y = 0; y < 25; y++) {
			for (int x = 0; x < 25; x++) {
				tiles.getTileMap().get(map[x][y]).getImage().render(g, x * 32, y * 32);
			}
		}
	}
}
