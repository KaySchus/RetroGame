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
	
	public PlayerEntity(World w, int i, int x, int y) {
		super(w, i, x, y);
	}

	public void update(GameTime gt, InputManager manager) {
		super.update(gt, manager);
		
		xVel = 0;
		yVel = 0;
		
		int tileX = getX() / 32;
		int tileY = getY() / 32;
		World w = getWorld();
		if (w != null) {
			int[][] tiles = w.tilesAround(tileX, tileY);
			
			if (manager.getKeys().up.isDown() && !topBounded) yVel = -64;
			if (manager.getKeys().down.isDown() && !bottomBounded) yVel = 64;
			if (manager.getKeys().left.isDown() && !leftBounded) xVel = -64;
			if (manager.getKeys().right.isDown() && !rightBounded) xVel = 64;	
			
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
						tBounds.setX((tileX + x) * 32);
						tBounds.setY((tileY + y) * 32);
						
						if (getBounds().intersects(tBounds) && t.isSolid()) {
							intersecting = true;
							if (xVel > 0) {
								int target = tBounds.getX() - 2;
								setX(target - 16);
							}
							
							if (xVel < 0) {
								int target = tBounds.getX() + tBounds.getWidth() + 2;
								setX(target);
							}
							
							if (yVel > 0) {
								int target = tBounds.getY() - 2;
								setY(target - 16);
							}
							
							if (yVel < 0) {
								int target = tBounds.getY() + tBounds.getWidth() + 2;
								setY(target);
							}
						}
						
						for (int i = 1; i < 5; i++) {
							if (getBounds().touching(i, tBounds) && t.isSolid()) {
								System.out.println("Hi!");
								if (i == 1) leftBounded = true;
								if (i == 2) topBounded = true;
								if (i == 3) rightBounded = true;
								if (i == 4) bottomBounded = true;	
							} else {
								if (i == 1) leftBounded = false;
								if (i == 2) topBounded = false;
								if (i == 3) rightBounded = false;
								if (i == 4) bottomBounded = false;
							}
						}
					}
				}
			}
		}
	}
	
	public boolean isIntersecting() { return intersecting; }
}
