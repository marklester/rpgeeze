package model;

import view.Drawer;

/*
 * 
 * Take-able Item - added to inventory on touch
 * 
 */


public abstract class TakeableItem extends Item {
	public TakeableItem(Location location) {
		super("Takeable Item",location);
	}
	
	public void draw(Drawer d) {
		d.drawTakeableItem(this);
	}
	
	public void activate()
	{
		
	}

}

