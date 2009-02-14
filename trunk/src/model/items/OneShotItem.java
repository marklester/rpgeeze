package model.items;

import model.Location;
import view.Drawer;

/*
 * 
 * One-shot Item - activated and removed from map when touched by an Entity
 * 
 */


public abstract class OneShotItem extends Item {
	public OneShotItem(String name, Location location) {
		super(name,location);
	}
	
	public void activate() {
		//Do nothing
	}
}

