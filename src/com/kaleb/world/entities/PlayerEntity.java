package com.kaleb.world.entities;

import com.kaleb.GameTime;
import com.kaleb.input.InputManager;
import com.kaleb.math.Rectangle;
import com.kaleb.world.World;
import com.kaleb.world.tiles.Tile;

public class PlayerEntity extends Entity {
	private boolean intersecting = false;
	private double xVel;
	private double yVel;
	
	private boolean leftBounded = false;
	private boolean rightBounded = false;
	private boolean topBounded = false;
	private boolean bottomBounded = false;
	
	public PlayerEntity(World w, int i, int x, int y, int ww, int hh) {
		super(w, i, x, y, ww, hh);
	}

	public void update(GameTime gt, InputManager manager) {
		super.update(gt, manager);
		
		xVel = 0;
		yVel = 0;
		
		if (getWorld() != null) {
			int[][] tiles = getWorld().tilesAround(getTileX(), getTileY());
			
			if (manager.getKeys().up.isDown() && !topBounded) yVel = -64;
			else if (manager.getKeys().down.isDown() && !bottomBounded) yVel = 64;
			else if (manager.getKeys().left.isDown() && !leftBounded) xVel = -64;
			else if (manager.getKeys().right.isDown() && !rightBounded) xVel = 64;	
			
			double incX = xVel * gt.getDelta();
			double incY = yVel * gt.getDelta();
			
			incX(incX);
			incY(incY);
			
			intersecting = false;
			
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
	
	public boolean isIntersecting() { return intersecting; }
}
