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
	public InteractiveItem() {
		super("Interactive Item");
	}
	
	public void draw(Drawer d) {
		d.drawInteractiveItem(this);
	}
	public void activate() {
		
	}
	
}

