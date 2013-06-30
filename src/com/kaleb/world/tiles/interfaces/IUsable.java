package com.kaleb.world.tiles.interfaces;

import com.kaleb.world.World;
import com.kaleb.world.entities.MobEntity;

public interface IUsable {
	public void onUse(World w, MobEntity e, int x, int y);
}
