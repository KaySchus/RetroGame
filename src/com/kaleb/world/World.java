package com.kaleb.world;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.kaleb.GameTime;
import com.kaleb.content.BitmapLoader;
import com.kaleb.content.Bitmaps;
import com.kaleb.graphics.Camera;
import com.kaleb.graphics.fonts.BitmapFont;
import com.kaleb.input.InputManager;
import com.kaleb.world.entities.Entity;
import com.kaleb.world.entities.PlayerEntity;
import com.kaleb.world.entities.TestEntity;
import com.kaleb.world.levels.Level;
import com.kaleb.world.tiles.Tiles;

public class World {
	private Tiles tiles;
	private Camera camera;
	private Level level;
	
	private BitmapFont font;
	
	private int tileX = -1;
	private int tileY = -1;
	
	private String fps = "";
	
	private PlayerEntity player;
	private List<Entity> entities = new ArrayList<Entity>();
	
	private boolean debug = false;
	
	public World() {
		camera = new Camera(this, 800, 600);
		tiles = new Tiles();
		level = new Level("testLevel.png", tiles);
		
		font = new BitmapFont(Bitmaps.testFont, 16, 16);
		
		player = new PlayerEntity(this, Bitmaps.glasses, 170, 170, 6, 0, 22, 32);
		
		entities.add(player);
		entities.add(new TestEntity(this, Bitmaps.orange, 300, 300, 16, 16));
	}
	
	public void update(GameTime gameTime, InputManager manager) {
		for (Entity e : entities) {
			e.update(gameTime, manager);
		}
		
		camera.center(player.getX(), player.getY());
		camera.update();
		
		if (manager.getKeys().enter.keyPressed()) debug = !debug;
		
		
		
		if (manager.getMouse().left.keyPressed()) {
			tileX = (manager.getMouse().getX() + camera.getX()) / level.getBlockSize();
			tileY = (manager.getMouse().getY() + camera.getY()) / level.getBlockSize();
			System.out.println("Tile: " + getTiles().getTileMap().get(level.getMap()[tileX][tileY]));
		}
		
		else if (manager.getMouse().right.keyPressed()) {
			tileX = (manager.getMouse().getX() + camera.getX()) / level.getBlockSize();
			tileY = (manager.getMouse().getY() + camera.getY()) / level.getBlockSize();
			level.setTile(tiles.stone.getID(), tileX, tileY);
			System.out.println("Tile: " + getTiles().getTileMap().get(level.getMap()[tileX][tileY]));
		}
		
		fps = "FPS: " + gameTime.getFPS();
	}
	
	public void render(Graphics g) {
		camera.render(g);
		
		if (debug) {
			int playerXTile = player.getTileX();
			int playerYTile = player.getTileY();
			 
			if (playerXTile > 0 && playerYTile > 0 && playerXTile < level.getTilesHigh() && playerYTile < level.getTilesWide()) {
				for (int x = -1; x < 2; x++) {
					for (int y = -1; y < 2; y++) {
						int tile = tilesAround(playerXTile, playerYTile)[x + 1][y + 1];
						if (getTiles().getTileMap().get(tile).isSolid()) {
							BitmapLoader.getInstance().getBitmap(Bitmaps.redOver).render(g, ((playerXTile + x) * level.getBlockSize()) - camera.getX(), ((playerYTile + y) * level.getBlockSize()) - camera.getY());
						}
						BitmapLoader.getInstance().getBitmap(Bitmaps.border).render(g, ((playerXTile + x) * level.getBlockSize()) - camera.getX(), ((playerYTile + y) * level.getBlockSize()) - camera.getY());
					}
				}
			} 
		
			BitmapLoader.getInstance().getBitmap(Bitmaps.yellowborder).render(g, (player.getBounds().getX()) - camera.getX(), (player.getBounds().getY()) - camera.getY());
		}
	
		font.renderColoredString(g, fps, 0, 0, Color.BLUE);
		font.renderColoredString(g, "X: " + player.getX(), 0, 16, Color.BLUE);
		font.renderColoredString(g, "Y: " + player.getY(), 0, 32, Color.BLUE);
	}
	
	public int[][] tilesAround(int x, int y) {
		int[][] tiles = new int[3][3];
		if (x >= 0 && y >= 0 && x <= level.getTilesWide() && y <= level.getTilesHigh()) {
			for (int yy = -1; yy < 2; yy++) {
				for (int xx = -1; xx < 2; xx++) {
					if (x + xx >= 0 && x + xx<= level.getTilesWide() && y + yy >= 0 && y + yy <= level.getTilesHigh()) {
						tiles[xx + 1][yy + 1] = level.getMap()[x + xx][y + yy];
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
	
	public Level getLevel() { return level; }
	public int[][] getMap() { return level.getMap(); }
	public int getTileSize() { return level.getBlockSize(); }
	public Tiles getTiles() { return tiles; }
	public int getXSize() { return level.getTilesWide(); }
	public int getYSize() { return level.getTilesHigh(); }
	public List<Entity> getEntities() { return entities; }
}
