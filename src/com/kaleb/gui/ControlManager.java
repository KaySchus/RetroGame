package com.kaleb.gui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.kaleb.GameTime;
import com.kaleb.graphics.fonts.BitmapFont;
import com.kaleb.gui.controls.Control;
import com.kaleb.input.InputManager;

public class ControlManager {
	private List<Control> controls;
	private List<ControlManager> controlGroups;
	private BitmapFont controlFont;
	private ControlManager parent;
	
	private boolean isVisible = true;
	
	private int x = 0;
	private int y = 0;
	
	public ControlManager(BitmapFont font) {
		controls = new ArrayList<Control>();
		controlGroups = new ArrayList<ControlManager>();
		controlFont = font;
	}
	
	public void registerControl(Control c) {
		controls.add(c);
		c.setParent(this);
	}
	
	public void registerControlGroup(ControlManager group) {
		controlGroups.add(group);
	}
	
	public void update(GameTime gt, InputManager manager) {
		for (ControlManager m : controlGroups) {
			m.update(gt, manager);
		}
		
		for (Control c: controls) {
			c.update(gt, manager);
		}
	}
	
	public void render(Graphics g) {
		if (isVisible) {
			for (ControlManager m : controlGroups) {
				if (m.isVisible()) {
					m.render(g);
				}
			}
			
			for (Control c : controls) {
				if (c.isVisible()) {
					c.render(g);
				}
			}
		}
	}
	
	public BitmapFont getFont() { return controlFont; }
	public int size() { return controls.size(); }
	
	public void setParent(ControlManager par) { parent = par; }
	public ControlManager getParent() { return parent; }
	
	public int getX() { return x; }
	public void setX(int val) { x = val; }
	public int getY() { return y; }
	public void setY(int val) { y = val; }
	
	public boolean isVisible() { return isVisible; }
	public void setVisible(boolean val) { isVisible = val; }
	
	public int getXOffset() {
		int offs = 0;
		
		if (parent != null) {
			offs += parent.getXOffset();
		}
		
		return offs + x;
	}
	
	public int getYOffset() {
		int offs = 0;
		
		if (parent != null) {
			offs += parent.getYOffset();
		}
		
		return offs + y;
	}
}
