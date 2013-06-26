package com.kaleb.world.tiles;

import com.kaleb.content.BitmapLoader;
import com.kaleb.graphics.Bitmap;
import com.kaleb.math.Rectangle;

public class Tile {
	private int imageID;
	private int id;
	private String internalName = "";
	
	private boolean isSolid = false;
	private boolean isSquare = true;
	private Rectangle bounds = new Rectangle(0, 0, 32, 32);
	
	public Tile(int imid, int i) {
		Bitmap temp = BitmapLoader.getInstance().getBitmap(imid);
		imageID = imid;
		id = i;
		temp.dispose();
	}
	
	public void setSolid(boolean value) { isSolid = value; }
	public boolean isSolid() { return isSolid; }
	
	public void setName(String val) { internalName = val; }
	public String getName() { return internalName; }
	
	public boolean isSquare() { return isSquare; }
	public Rectangle getBounds() { return bounds; }
	
	public int getID() { return id; }
	public Bitmap getImage() { return BitmapLoader.getInstance().getBitmap(imageID); }
	
	public String toString() { 
		return "Name: " + internalName +", ID: " + id;
	}
}
