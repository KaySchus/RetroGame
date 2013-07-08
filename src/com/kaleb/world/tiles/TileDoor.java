package com.kaleb.world.tiles;

import com.kaleb.content.BitmapLoader;
import com.kaleb.graphics.Bitmap;
import com.kaleb.math.Rectangle;
import com.kaleb.world.World;
import com.kaleb.world.entities.MobEntity;
import com.kaleb.world.tiles.interfaces.IUsable;

public class TileDoor extends Tile implements IUsable {
	public static final byte CLOSED = 0;
	public static final byte OPEN = 1;
	int im2;
	
	public TileDoor(int im1, int im, int i) {
		super(im1, i);
		im2 = im;
		
		setBounds(new Rectangle(0, 11, 32, 10));
		setYOffs(11);
	}
	
	@Override
	public boolean isSolidWithMeta(byte m) {
		if (m == CLOSED) return true;
		else return false;
	}
	
	public void onUse(World w, MobEntity e, int x, int y) {
		if (w.getLevel().getMeta()[x][y] == 0) {
			w.getLevel().setTileWithMeta(id, OPEN, x, y);
		} else w.getLevel().setTileWithMeta(id, CLOSED, x, y);
	}
	
	@Override
	public Bitmap getImageFromMeta(byte m) {
		if (m == CLOSED) return super.getImage();
		return BitmapLoader.getInstance().getBitmap(im2);
	}
}
