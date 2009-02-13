package model;

import view.Drawer;

/*
 * 
 * One-shot Item - activated and removed from map when touched by an Entity
 * 
 */


public abstract class OneShotItem extends Item {
	public OneShotItem() {
		super("One Shot Item");
	}
	
	public void draw(Drawer d) {
		d.drawOneShotItem(this);
	}
	
	public void activate() {
		//Do nothing
	}
}

