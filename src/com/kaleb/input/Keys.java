package com.kaleb.input;

import java.util.ArrayList;
import java.util.List;

public class Keys {
	public class Key {
		private boolean nextState = false;
		private boolean isDown = false;
		private boolean wasDown = false;
		
		public Key() {
			keys.add(this);
		}
		
		public void toggle(boolean setting) {
			nextState = setting;
		}
		
		public void update() {
			wasDown = isDown;
			isDown = nextState;
		}
		
		public void release() {
			nextState = false;
		}
		
		public boolean isDown() { return isDown; }
		public boolean wasDown() { return wasDown; }
		public boolean keyPressed() { return !wasDown && isDown; }
		public boolean keyReleased() { return wasDown && !isDown; }
	}
	
	private List<Key> keys = new ArrayList<Key>();
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key enter = new Key();
	public Key exit = new Key();
	
	public void update() {
		for (Key key : keys) {
			key.update();
		}
	}
	
	public List<Key> getAll() { return keys; }
}
