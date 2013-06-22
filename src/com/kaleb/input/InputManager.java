package com.kaleb.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import com.kaleb.input.Keys.Key;

public class InputManager implements KeyListener {
	private Map<Integer, Key> mappings = new HashMap<Integer, Key>();
	private Keys keys;
	
	public InputManager(Keys ke) {
		keys = ke;
		
		mappings.put(KeyEvent.VK_W, ke.up);
		mappings.put(KeyEvent.VK_UP, ke.up);
		
		mappings.put(KeyEvent.VK_S, ke.down);
		mappings.put(KeyEvent.VK_DOWN, ke.down);
		
		mappings.put(KeyEvent.VK_A, ke.left);
		mappings.put(KeyEvent.VK_LEFT, ke.left);
		
		mappings.put(KeyEvent.VK_D, ke.right);
		mappings.put(KeyEvent.VK_RIGHT, ke.right);
		
		mappings.put(KeyEvent.VK_ENTER, ke.enter);
		
		mappings.put(KeyEvent.VK_ESCAPE, ke.exit);
	}
	
	public void keyPressed(KeyEvent e) {
		toggle(e, true);
	}

	public void keyReleased(KeyEvent e) {
		toggle(e, false);
	}

	public void keyTyped(KeyEvent e) {
	}
	
	public void update() {
		keys.update();
	}
	
	public void toggle(KeyEvent e, boolean state) {
		Key key = mappings.get(e.getKeyCode());
		if (key != null) {
			key.toggle(state);
		}
	}
	
	public Keys getKeys() { return keys; }
}
