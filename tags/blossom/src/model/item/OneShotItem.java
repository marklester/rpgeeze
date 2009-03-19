package model.item;

/**
 * An Item that is activated and removed from the Map when touched by an
 * Entity.
 */

import model.Entity;

public abstract class OneShotItem extends Item {
	public OneShotItem(String name) {
		super(name);
	}
	
	public void activate(Entity e) {
		use(e);
	}

	public void use(Entity e)	{
	}
}