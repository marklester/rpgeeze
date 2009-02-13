package model;

import view.Drawer;

/*
 * 
 * Interactive Item - (potentially) activated on touch; 
 * activation may require possession of a specific item 
 * or completion of a sequence of actions (e.g., puzzle)
 * 
 */


public abstract class InteractiveItem extends Item {
	public InteractiveItem(Location location) {
		super("Interactive Item",location);
	}
	
	public void activate() {
		
	}
	
}

