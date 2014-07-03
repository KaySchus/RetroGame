package com.kaleb.world.entities;

import java.awt.Graphics;

import com.kaleb.GameTime;
import com.kaleb.input.InputManager;
import com.kaleb.world.World;
import com.kaleb.world.tiles.Tile;
import com.kaleb.world.tiles.interfaces.IUsable;

public class PlayerEntity extends MobEntity {
	private int speed = 64;
	
	public PlayerEntity(World w, int i, int x, int y, int ww, int hh) {
		super(w, i, x, y, ww, hh);
	}
	
	public PlayerEntity(World w, int i, int x, int y, int xx, int yy, int ww, int hh) {
		super(w, i, x, y, xx, yy, ww, hh);
	}
	
	@Override
	public void update(GameTime gt, InputManager manager) {
		setXVel(0);
		setYVel(0);
		
		if (manager.getKeys().up.isDown()) setYVel(-speed);
		else if (manager.getKeys().down.isDown()) setYVel(speed);
		else if (manager.getKeys().right.isDown()) setXVel(speed);
		else if (manager.getKeys().left.isDown()) setXVel(-speed);
		
		if (manager.getKeys().use.keyPressed()) {
			Tile t = null;
			int xOffs = 0;
			int yOffs = 0;
			
			if (getFacing() == MobEntity.NORTH) {
				t = getWorld().getTiles().getTileMap().get(getWorld().getMap()[getTileX()][getTileY() - 1]);
				yOffs = -1;
			}
			
			if (getFacing() == MobEntity.EAST) {
				t = getWorld().getTiles().getTileMap().get(getWorld().getMap()[getTileX() + 1][getTileY()]);
				xOffs = 1;
			}

			if (getFacing() == MobEntity.SOUTH) {
				t = getWorld().getTiles().getTileMap().get(getWorld().getMap()[getTileX()][getTileY() + 1]);
				yOffs = 1;
			}
			
			if (getFacing() == MobEntity.WEST) {
				t = getWorld().getTiles().getTileMap().get(getWorld().getMap()[getTileX() - 1][getTileY()]);
				xOffs = -1;
			}
			
			if (t instanceof IUsable) {
				((IUsable) t).onUse(getWorld(), this, getTileX() + xOffs, getTileY() + yOffs);
			}
		}
		
		super.update(gt, manager);
	}
	
	@Override
	public void render(Graphics g, int x, int y) {
		super.render(g, x, y);
	}
}
