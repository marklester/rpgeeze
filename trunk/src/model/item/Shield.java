package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.Location;
import view.Drawer;

public class Shield extends TakeableItem {

	public Shield(Location location) {
		super("Shield", location);
	}

	public void draw(Drawer d) {
		d.drawShield(this);
	}
	
	public void activate(model.Entity e)
	{
		
	}
}
