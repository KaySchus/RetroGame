package com.kaleb.world.entities;

import com.kaleb.GameTime;
import com.kaleb.input.InputManager;
import com.kaleb.math.Rectangle;
import com.kaleb.world.World;
import com.kaleb.world.tiles.Tile;

public class MobEntity extends Entity {
	public static final int NORTH = 1;
	public static final int EAST = 2;
	public static final int SOUTH = 3;
	public static final int WEST = 4;
	
	private int facing;
	private double xVel;
	private double yVel;
	
	public MobEntity(World w, int i, int x, int y, int ww, int hh) {
		super(w, i, x, y, ww, hh);
		
		facing = NORTH;
	}
	
	public void update(GameTime gt, InputManager manager) {
		super.update(gt, manager);
		
		if (getWorld() != null) {
			int[][] tiles = getWorld().tilesAround(getTileX(), getTileY());
			
			double incX = xVel * gt.getDelta();
			double incY = yVel * gt.getDelta();
			
			incX(incX);
			incY(incY);
			
			for (int x = -1; x < tiles.length - 1; x++) {
				for (int y = -1; y < tiles[0].length - 1; y++) {
					Tile t = getWorld().getTiles().getTileMap().get(tiles[x + 1][y + 1]);
					if (t != null) {
						Rectangle tBounds = t.getBounds();
						tBounds.update((getTileX() + x) * 32, (getTileY() + y) * 32);
							
						if (getBounds().intersects(tBounds) && t.isSolid()) {
							if (xVel > 0) {
								setX(tBounds.getX() - getBounds().getWidth());
								while (getBounds().intersects(tBounds)) {
									incX(-1);
								}
							}
								
							else if (xVel < 0) {
								setX(tBounds.getX() + tBounds.getWidth());
								while (getBounds().intersects(tBounds)) {
									incX(1);
								}
							}
								
							else if (yVel > 0) {
								setY(tBounds.getY() - getBounds().getWidth());
								while (getBounds().intersects(tBounds)) {
									incY(-1);
								}
							}
								
							else if (yVel < 0) {
								setY(tBounds.getY() + tBounds.getHeight());
								while (getBounds().intersects(tBounds)) {
									incY(1);
								}
							}
						}
					}
				}
			}
		}
	}
	
	public double getXVel() { return xVel; }
	public double getYVel() { return yVel; }
	public void setXVel(double val) { xVel = val; }
	public void setYVel(double val) { yVel = val; }
}
