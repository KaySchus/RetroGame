package com.kaleb.math;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {
	public static final int MAX_OBJECTS = 10;
	public static final int MAX_RECURSION = 5;
	
	public static final int QUADRANT1 = 0;
	public static final int QUADRANT2 = 1;
	public static final int QUADRANT3 = 2;
	public static final int QUADRANT4 = 3;
	public static final int QUADRANT12 = 4;
	public static final int QUADRANT23 = 5;
	public static final int QUADRANT34 = 6;
	public static final int QUADRANT41 = 7;
	public static final int QUADRANTALL = 8;
	
	private int level;
	private List<Rectangle> objects;
	private Rectangle bounds;
	private QuadTree[] nodes;
	
	public QuadTree(int l, Rectangle b) {
		level = l;
		bounds = b;
		
		objects = new ArrayList<Rectangle>();
		nodes = new QuadTree[4];
	}
	
	public void clear() {
		objects.clear();
		
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] != null) nodes[i].clear();
			nodes[i] = null;
		}
	}
	
	public void split() {
		int width = (int) bounds.getWidth() / 2;
		int height = (int) bounds.getHeight() / 2;
		int xPos = bounds.getX();
		int yPos = bounds.getY();
		
		System.out.println("X: " + xPos + ", Y: " + yPos + "W: " + width + ", H: " + height);
		
		nodes[0] = new QuadTree(level + 1, new Rectangle(xPos, yPos, width, height));
		nodes[1] = new QuadTree(level + 1, new Rectangle(xPos + width, yPos, width, height));
		nodes[2] = new QuadTree(level + 1, new Rectangle(xPos, yPos + height, width, height));
		nodes[3] = new QuadTree(level + 1, new Rectangle(xPos + width, yPos + height, width, height));
	}
	
	public void insert(Rectangle data) {
		System.out.println(data);
		objects.add(data);
		
		if (objects.size() == MAX_OBJECTS) {
			if (nodes[0] == null) {
				split();
				System.out.println("Splitting!");
			}
			
			for (Rectangle r : objects) {
				int i = getIndex(r);
				
				System.out.println(i);
				
				if (i >= QUADRANT1 && i <= QUADRANT4) {
					nodes[i].insert(r);
				}
				
				if (i == QUADRANT12) {
					nodes[QUADRANT1].insert(data);
					nodes[QUADRANT2].insert(data);
				}
				
				if (i == QUADRANT23) {
					nodes[QUADRANT2].insert(data);
					nodes[QUADRANT3].insert(data);
				}
				
				if (i == QUADRANT34) {
					nodes[QUADRANT3].insert(data);
					nodes[QUADRANT4].insert(data);
				}
				
				if (i == QUADRANT41) {
					nodes[QUADRANT4].insert(data);
					nodes[QUADRANT1].insert(data);
				}
				
				if (i == QUADRANTALL) {
					nodes[QUADRANT1].insert(data);
					nodes[QUADRANT2].insert(data);
					nodes[QUADRANT3].insert(data);
					nodes[QUADRANT4].insert(data);
				}
	
				if (i == -1) System.out.println("Error!");
			}
			
			objects.clear();
		}
	}
	
	public int getIndex(Rectangle rect) {
		int w = (int) bounds.getWidth() / 2;
		int h = (int) bounds.getHeight() / 2;
		int x = bounds.getX();
		int y = bounds.getY();
		
		Rectangle q1 = new Rectangle(x, y, w, h);
		Rectangle q2 = new Rectangle(x + w, y, w, h);
		Rectangle q3 = new Rectangle(x, y + h, w, h);
		Rectangle q4 = new Rectangle(x + w, y + h, w, h);
		
		if (q1.contains(rect)) return QUADRANT1;
		if (q2.contains(rect)) return QUADRANT2;
		if (q3.contains(rect)) return QUADRANT3;
		if (q4.contains(rect)) return QUADRANT4;
		
		if (rect.intersects(q1) && rect.intersects(q2) 
				&& !rect.intersects(q3) && !rect.intersects(q4)) return QUADRANT12;
		if (!rect.intersects(q1) && rect.intersects(q2) 
				&& rect.intersects(q3) && !rect.intersects(q4)) return QUADRANT23;
		if (!rect.intersects(q1) && !rect.intersects(q2) 
				&& rect.intersects(q3) && rect.intersects(q4)) return QUADRANT34;
		if (rect.intersects(q1) && !rect.intersects(q2) 
				&& !rect.intersects(q3) && rect.intersects(q4)) return QUADRANT41;
		if (rect.intersects(q1) && rect.intersects(q2) 
				&& rect.intersects(q3) && rect.intersects(q4)) return QUADRANTALL;
		
		return -1;
	}
	
	public List<Rectangle> getObjects() { return objects; }
}
