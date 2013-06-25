package com.kaleb.gui.screens;

import java.awt.Graphics;

import com.kaleb.GameTime;
import com.kaleb.input.InputManager;
import com.kaleb.world.World;

public class WorldScreen implements Screen {
	private World world;
	
	public WorldScreen() {
		world = new World();
	}
	
	public Screen update(GameTime gameTime, InputManager manager) {
		world.update(gameTime, manager);
		return this;
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}
}
