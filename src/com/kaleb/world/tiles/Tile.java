package com.kaleb.world.tiles;

import com.kaleb.content.BitmapLoader;
import com.kaleb.graphics.Bitmap;

public class Tile {
	private int imageID;
	private int id;
	
	public Tile(int imid, int i) {
		Bitmap temp = BitmapLoader.getInstance().getBitmap(imid);
		imageID = imid;
		id = i;
		temp.dispose();
	}
	
	public int getID() { return id; }
	public Bitmap getImage() { return BitmapLoader.getInstance().getBitmap(imageID); }
}
