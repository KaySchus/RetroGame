package com.kaleb.world.entities.spawner;

import java.util.Random;

import com.kaleb.GameTime;
import com.kaleb.input.InputManager;
import com.kaleb.world.World;
import com.kaleb.world.entities.TestEntity;

public class TestSpawner extends EntitySpawner {
	public int spawn_accumulator = 0;
	public Random rand;
	
	public TestSpawner(World w, int i, int x, int y, int ww, int hh) {
		super(w, i, x, y, ww, hh);
		
		rand = new Random();
	}
	
	public void update(GameTime gt, InputManager mag) {
		super.update(gt, mag);
		
		if (spawn_accumulator > 1000) {
			if (rand.nextDouble() > 0.5) {
				spawn_accumulator = 0;
				
				TestEntity toSpawn = new TestEntity(getWorld(), getBitmapID(), getX(), getY(), getWidth(), getHeight());
				getWorld().getEntitiesToAdd().add(toSpawn);
				
				System.out.println("Spawned!");
			}
		} else {
			spawn_accumulator++;
		}
	}
}
