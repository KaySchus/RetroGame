package com.kaleb.world.entities;

import com.kaleb.GameTime;
import com.kaleb.input.InputManager;
import com.kaleb.world.World;

public class PlayerEntity extends MobEntity {
	public PlayerEntity(World w, int i, int x, int y, int ww, int hh) {
		super(w, i, x, y, ww, hh);
	}

	public void update(GameTime gt, InputManager manager) {
		setXVel(0);
		setYVel(0);
			
		if (manager.getKeys().up.isDown()) { setYVel(-64); }
		else if (manager.getKeys().down.isDown()) setYVel(64);
		else if (manager.getKeys().left.isDown()) setXVel(-64);
		else if (manager.getKeys().right.isDown()) setXVel(64);
		
		super.update(gt, manager);
	}
}
