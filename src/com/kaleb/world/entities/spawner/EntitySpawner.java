package com.kaleb.world.entities.spawner;

import com.kaleb.world.World;
import com.kaleb.world.entities.Entity;

public abstract class EntitySpawner extends Entity {
	public EntitySpawner(World w, int i, int x, int y, int ww, int hh) {
		super(w, i, x, y, ww, hh);
		
		setShouldRender(false);
	}
}
