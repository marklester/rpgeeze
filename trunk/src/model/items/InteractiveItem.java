package model.items;

import model.Location;

/*
 * 
 * Interactive Item - (potentially) activated on touch; 
 * activation may require possession of a specific item 
 * or completion of a sequence of actions (e.g., puzzle)
 * 
 */

public abstract class InteractiveItem extends Item {
	public InteractiveItem(String name, Location location) {
		super(name, location);
	}

	public void activate() {

	}

}
