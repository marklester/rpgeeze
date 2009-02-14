package model.items;

import model.Location;

/*
 * 
 * Take-able Item - added to inventory on touch
 * 
 */


public abstract class TakeableItem extends Item {
	
	public TakeableItem(String name, Location location) {
		super(name,location);
	}
	
	public void activate() {
		
	}

}

