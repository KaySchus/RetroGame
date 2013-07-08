package com.kaleb.world.tiles;

import com.kaleb.content.BitmapLoader;
import com.kaleb.graphics.Bitmap;
import com.kaleb.math.Rectangle;

public class Tile {
	protected int imageID;
	protected int id;
	protected String internalName = "";
	
	private boolean isSolid = false;
	private boolean isSquare = true;
	private boolean hasMeta = false;
	private Rectangle bounds = new Rectangle(0, 0, 32, 32);
	
	private int xOffs = 0;
	private int yOffs = 0;
	
	public Tile(int imid, int i) {
		Bitmap temp = BitmapLoader.getInstance().getBitmap(imid);
		imageID = imid;
		id = i;
		temp.dispose();
	}
	
	public void setSolid(boolean value) { isSolid = value; }
	public boolean isSolid() { return isSolid; }
	public boolean isSolidWithMeta(byte m) { return isSolid; }
	
	public void setName(String val) { internalName = val; }
	public String getName() { return internalName; }
	
	public boolean isSquare() { return isSquare; }
	public Rectangle getBounds() { return bounds; }
	public void setBounds(Rectangle r) { bounds = r; }
	
	public boolean hasMeta() { return hasMeta; }
	public void setMeta(boolean val) { hasMeta = val; }
	
	public int getID() { return id; }
	public Bitmap getImage() { return BitmapLoader.getInstance().getBitmap(imageID); }
	public Bitmap getImageFromMeta(byte meta) { return BitmapLoader.getInstance().getBitmap(imageID); }
	
	public int getYOffs() { return yOffs; }
	public int getXOffs() { return xOffs; }

	public void setXOffs(int val) { xOffs = val; }
	public void setYOffs(int val) { yOffs = val; }
	
	public String toString() { 
		return "Name: " + internalName +", ID: " + id;
	}
}
