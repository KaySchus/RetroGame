package com.kaleb.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import com.kaleb.Game;
import com.kaleb.input.InputManager;

public class GameCanvas extends Canvas implements KeyListener {
	private static final long serialVersionUID = 3706001658145650745L;
	private Game parentRef = null;
	
	private Color backgroundColor = Color.BLACK;
	private Graphics g;
	private BufferStrategy bs;
	
	public GameCanvas(Game parent, int width, int height, InputManager mang) {
		setSize(width, height);
		parentRef = parent;
		
		addKeyListener(mang);
	}
	
	public void render() {
		if (parentRef != null) {
			bs = getBufferStrategy();
			if (bs == null) {
				createBufferStrategy(3);
				requestFocus();
				return;
			}
			
			g = bs.getDrawGraphics();
			g.setColor(backgroundColor);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			parentRef.render(g);
			
			g.dispose();
			bs.show();
		}
	}
	
	public Color getBackgroundColor() { return backgroundColor; }
	public void setBackgroundColor(Color bg) { backgroundColor = bg; }

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
