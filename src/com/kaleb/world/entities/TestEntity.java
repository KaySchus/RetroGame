package com.kaleb.world.entities;

import java.util.Random;

import com.kaleb.GameTime;
import com.kaleb.input.InputManager;
import com.kaleb.world.World;

public class TestEntity extends MobEntity {
	private double timeElapsed = 0;
	private Random rand;

	public TestEntity(World w, int i, int x, int y, int ww, int hh) {
		super(w, i, x, y, ww, hh);
		rand = new Random();
		
		if (rand.nextBoolean()) {
			setXVel((0.5 - rand.nextDouble()) * 256);
		}
		
		else setYVel((0.5 - rand.nextDouble()) * 256);
	}
	
	public void update(GameTime gt, InputManager mag) {
		timeElapsed += gt.getDelta();
	
		if (timeElapsed >= 1.0) {
			if (rand.nextBoolean()) {
				double vel = (0.5 - rand.nextDouble()) * 256;
				setXVel(vel);
				setYVel(0);
			}
			
			else {
				double vel = (0.5 - rand.nextDouble()) * 256;
				setYVel(vel);
				setXVel(0);
			}
			
			timeElapsed = 0;
		}
		
		super.update(gt, mag);
	}
}
