package com.kaleb.world.entities;

import com.kaleb.GameTime;
import com.kaleb.input.InputManager;
import com.kaleb.world.World;

public class PlayerEntity extends Entity {
	public PlayerEntity(World w, int i, int x, int y) {
		super(w, i, x, y);
	}
	
	public void update(GameTime gt, InputManager manager) {
		if (manager.getKeys().up.isDown()) incY(-1);
		if (manager.getKeys().down.isDown()) incY(1);
		if (manager.getKeys().left.isDown()) incX(-1);
		if (manager.getKeys().right.isDown()) incX(1);	
	}
}
