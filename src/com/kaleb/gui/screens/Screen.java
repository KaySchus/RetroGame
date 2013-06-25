package com.kaleb.gui.screens;

import java.awt.Graphics;

import com.kaleb.GameTime;
import com.kaleb.input.InputManager;

public interface Screen {
	public Screen update(GameTime gameTime, InputManager manager);
	public void render(Graphics g);
}
