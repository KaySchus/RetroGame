package com.kaleb.world.entities;

import com.kaleb.GameTime;
import com.kaleb.input.InputManager;
import com.kaleb.world.World;

public class TestEntity extends Entity {

	public TestEntity(World w, int i, int x, int y, int ww, int hh) {
		super(w, i, x, y, ww, hh);
	}
	
	public void update(GameTime gt, InputManager mag) {
		super.update(gt, mag);
	}
}
