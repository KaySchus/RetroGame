package com.kaleb.world;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.kaleb.GameTime;
import com.kaleb.content.BitmapLoader;
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
	
	private int tileX = -1;
	private int tileY = -1;
	
	private String fps = "";
	
	private PlayerEntity player;
	
	private int[][] map = new int[xSize][ySize];
	private List<Entity> entities = new ArrayList<Entity>();
	
	public World() {
		camera = new Camera(this, 800, 600);
		tiles = new Tiles();
		rand = new Random();
		
		font = new BitmapFont(Bitmaps.testFont, 16, 16);
		
		player = new PlayerEntity(this, Bitmaps.orange, 128, 128);
		entities.add(player);
		
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
		if (manager.getKeys().shift.keyPressed()) generate();
		
		if (manager.getMouse().left.keyPressed()) {
			tileX = manager.getMouse().getX() / blockSize;
			tileY = manager.getMouse().getY() / blockSize;
			System.out.println("Tile: " + getTiles().getTileMap().get(map[tileX][tileY]));
		}
		
		else if (manager.getMouse().right.keyPressed()) {
			tileX = manager.getMouse().getX() / blockSize;
			tileY = manager.getMouse().getY() / blockSize;
			map[tileX][tileY] = 1;
			System.out.println("Tile: " + getTiles().getTileMap().get(map[tileX][tileY]));
		}
		
		fps = "FPS: " + gameTime.getFPS();
	}
	
	public void render(Graphics g) {
		camera.render(g);
		
		int playerXTile = player.getX() / blockSize;
		int playerYTile = player.getY() / blockSize;
		
		if (playerXTile > 0 && playerYTile > 0 && playerXTile < xSize && playerYTile < ySize) {
			for (int x = -1; x < 2; x++) {
				for (int y = -1; y < 2; y++) {
					int tile = tilesAround(playerXTile, playerYTile)[x + 1][y + 1];
					if (getTiles().getTileMap().get(tile).isSolid()) {
						BitmapLoader.getInstance().getBitmap(Bitmaps.redOver).render(g, (playerXTile + x) * blockSize, (playerYTile + y) * blockSize);
					}
					BitmapLoader.getInstance().getBitmap(Bitmaps.border).render(g, (playerXTile + x) * blockSize, (playerYTile + y) * blockSize);
				}
			}
		}
		
		BitmapLoader.getInstance().getBitmap(Bitmaps.blueOver).render(g, tileX * 32, tileY * 32);
		BitmapLoader.getInstance().getBitmap(Bitmaps.yellowborder).render(g, player.getBounds().getX(), player.getBounds().getY());
		//System.out.println(player.getBounds().getX());
		
		font.renderColoredString(g, fps, 0, 0, Color.BLUE);
		if (player.isIntersecting()) font.renderString(g, "Int", 0, 16);
	}
	
	public int[][] tilesAround(int x, int y) {
		int[][] tiles = new int[3][3];
		if (x >= 0 && y >= 0 && x <= xSize && y <= ySize) {
			for (int yy = -1; yy < 2; yy++) {
				for (int xx = -1; xx < 2; xx++) {
					if (x + xx >= 0 && x + xx<= xSize && y + yy >= 0 && y + yy <= ySize) {
						tiles[xx + 1][yy + 1] = map[x + xx][y + yy];
					}
					
					else tiles[xx + 1][yy + 1] = -1;
				}
			}
		}
		
		else for (int yy = 0; yy < 3; yy++) {
			for (int xx = 0; xx < 3; xx++) {
				tiles[xx][yy] = -1;
			}
		}
		
		return tiles;
	}
	
	public int[][] getMap() { return map; }
	public int getTileSize() { return blockSize; }
	public Tiles getTiles() { return tiles; }
	public int getXSize() { return xSize; }
	public int getYSize() { return ySize; }
	public List<Entity> getEntities() { return entities; }
}
