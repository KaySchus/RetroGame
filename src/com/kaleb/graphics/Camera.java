package com.kaleb.graphics;

import java.awt.Graphics;
import java.util.List;
import java.util.Map;

import com.kaleb.world.World;
import com.kaleb.world.entities.Entity;
import com.kaleb.world.tiles.Tile;

public class Camera {
	private World world;
	private int width;
	private int height;
	
	private int xPos;
	private int yPos;
	
	private int tilePosX;
	private int tilePosY;
	private int tilesX;
	private int tilesY;
	private int renderXOffs;
	private int renderYOffs;
	
	public Camera(World w, int pixelWidth, int pixelHeight) {
		world = w;
		width = pixelWidth;
		height = pixelHeight;
		
		xPos = 0;
		yPos = 0;
	}
	
	public void update() {
		tilePosX = xPos / world.getTileSize();
		tilePosY = yPos / world.getTileSize();
		
		tilesX = width / world.getTileSize() + 1;
		tilesY = height / world.getTileSize() + 2;
		
		renderXOffs = xPos % world.getTileSize();
		renderYOffs = yPos % world.getTileSize();
	}
	
	public void render(Graphics g) {
		int[][] map = world.getMap();
		Map<Integer, Tile> tiles = world.getTiles().getTileMap();
		
		for (int y = 0; y < tilesY; y++) {
			for (int x = 0; x < tilesX; x++) {
				if (x + tilePosX >= 0 && x + tilePosX < world.getXSize() && y + tilePosY >= 0 && y + tilePosY < world.getYSize()) {
					tiles.get(map[x + tilePosX][y + tilePosY]).getImage().render(g, (x * world.getTileSize()) - renderXOffs, 
							(y * world.getTileSize()) - renderYOffs);
				}
			}
		}
		
		List<Entity> entities = world.getEntities();
		for (Entity e : entities) {
			if (e.getX() > xPos - e.getBitmap().getWidth() &&  e.getX() < xPos + width && e.getY() > yPos - e.getBitmap().getWidth() && e.getY() < yPos + height) {
				e.render(g, e.getX() - xPos, e.getY() - yPos);
			}
		}
	}
	
	public void move(int dx, int dy) {
		if (xPos + dx + width < world.getXSize() * world.getTileSize() && dx > 0) xPos += dx;
		if (yPos + dx + height < (world.getYSize() * world.getTileSize()) - 1 && dy > 0) yPos += dy;

		if (xPos > 0 && dx < 0) xPos += dx;
		if (yPos > 0 && dy < 0) yPos += dy;
		
		if (xPos < 0) xPos = 0;
		if (yPos < 0) yPos = 0;
		
		if (xPos > width) xPos = width;
		if (yPos > height) yPos = height;
	}
	
	public void setPos(int x, int y) {
		if (x >= 0 && x <= (world.getXSize() * world.getTileSize()) - width / 2) xPos = x;
		if (y >= 0 && y <= (world.getYSize() * world.getTileSize()) - height / 2) yPos = y;
	}
	
	public void center(int x, int y) {
		setPos(x - (width / 2), y - (height /2));
	}
	
	public int getX() { return xPos; }
	public int getY() { return yPos; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
}
