package com.kaleb.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map;

import com.kaleb.input.Keys.Key;
import com.kaleb.input.Mouse.MouseButton;

public class InputManager implements KeyListener, MouseListener, MouseMotionListener {
	private Map<Integer, Key> mappings = new HashMap<Integer, Key>();
	private Map<Integer, MouseButton> mouseMappings = new HashMap<Integer, MouseButton>();
	
	private Keys keys;
	private Mouse mouse;
	
	public InputManager(Keys ke, Mouse m) {
		keys = ke;
		mouse = m;
		
		mappings.put(KeyEvent.VK_W, keys.up);
		mappings.put(KeyEvent.VK_UP, keys.up);
		
		mappings.put(KeyEvent.VK_S, keys.down);
		mappings.put(KeyEvent.VK_DOWN, keys.down);
		
		mappings.put(KeyEvent.VK_A, keys.left);
		mappings.put(KeyEvent.VK_LEFT, keys.left);
		
		mappings.put(KeyEvent.VK_D, keys.right);
		mappings.put(KeyEvent.VK_RIGHT, keys.right);
		
		mappings.put(KeyEvent.VK_ENTER, keys.enter);
		
		mappings.put(KeyEvent.VK_SHIFT, keys.shift);
		
		mappings.put(KeyEvent.VK_ESCAPE, keys.exit);
		
		mouseMappings.put(MouseEvent.BUTTON1, mouse.left);
		mouseMappings.put(MouseEvent.BUTTON3, mouse.right);
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
		mouse.update();
		keys.update();
	}
	
	public void toggle(KeyEvent e, boolean state) {
		Key key = mappings.get(e.getKeyCode());
		if (key != null) {
			key.toggle(state);
		}
	}
	
	public void toggle(MouseEvent e, boolean state) {
		MouseButton mb = mouseMappings.get(e.getButton());
		if (mb != null) {
			mb.toggle(state);
		}
	}
	
	public Keys getKeys() { return keys; }
	public Mouse getMouse() { return mouse; }

	public void mouseClicked(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mouseDragged(MouseEvent e) { }
	
	public void mousePressed(MouseEvent e) { 
		toggle(e, true);
	}
	
	public void mouseReleased(MouseEvent e) {
		toggle(e, false);
	}

	public void mouseMoved(MouseEvent e) {
		mouse.move(e.getX(), e.getY());
	}
}
