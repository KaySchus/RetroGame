package com.kaleb.world;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.kaleb.GameTime;
import com.kaleb.content.Bitmaps;
import com.kaleb.graphics.Camera;
import com.kaleb.graphics.fonts.BitmapFont;
import com.kaleb.input.InputManager;
import com.kaleb.world.entities.Entity;
import com.kaleb.world.entities.PlayerEntity;
import com.kaleb.world.tiles.Tiles;

public class World {
	private Tiles tiles;
	private Camera camera;
	private Random rand;
	
	private BitmapFont font;
	
	private int xSize = 100;
	private int ySize = 100;
	private int blockSize = 32;
	
	private String fps = "";
	
	private Entity e = new PlayerEntity(this, Bitmaps.orange, 48, 48);
	
	private int[][] map = new int[xSize][ySize];
	private List<Entity> entities = new ArrayList<Entity>();
	
	public World() {
		camera = new Camera(this, 800, 600);
		tiles = new Tiles();
		rand = new Random();
		
		font = new BitmapFont(Bitmaps.testFont, 16, 16);
		
		entities.add(e);
		
		generate();
	}
	
	public void generate() {
		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				map[x][y] = rand.nextInt(2) + 1;
				if (x == 0 || y == 0 || x == xSize - 1 || y == ySize - 1) {
					map[x][y] = 4;
				}
			}
		}
	}
	
	public void smooth(int numTimes) {
		for (int i = 0; i < numTimes; i++) {
			int[][] newMap = new int[xSize][ySize];
			for (int x = 0; x < xSize; x++) {
				for (int y = 0; y < ySize; y++) {
					int stoneCount = 0;
					int grassCount = 0;
					
					if (map[x][y] != 4) {
						for (int j = -1; j < 3; j++) {
							for (int k = -1; k < 3; k++) {
								if (x + j > 0 && x + j < xSize && y + k > 0 && y + k < ySize) {
									if (map[x + j][y + k] == 1) stoneCount++;
									else grassCount++;
								}
							}
						}
						
						if (stoneCount > grassCount) newMap[x][y] = 1;
						else newMap[x][y] = 2;
					}
					
					else newMap[x][y] = 4;
				}
			}
			
			for (int x = 0; x < xSize; x++) {
				for (int y = 0; y < ySize; y++) {
					map[x][y] = newMap[x][y];
				}
			}
		}
		
	}
	
	public void update(GameTime gameTime, InputManager manager) {
		for (Entity e : entities) {
			e.update(gameTime, manager);
		}
		camera.update();
		if (manager.getKeys().enter.keyPressed()) smooth(1);
		
		fps = "FPS: " + gameTime.getFPS();
	}
	
	public void render(Graphics g) {
		camera.render(g);
		font.renderColoredString(g, fps, 0, 0, Color.BLUE);
	}
	
	public int[][] getMap() { return map; }
	public int getTileSize() { return blockSize; }
	public Tiles getTiles() { return tiles; }
	public int getXSize() { return xSize; }
	public int getYSize() { return ySize; }
	public List<Entity> getEntities() { return entities; }
}
