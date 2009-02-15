package model.item;

/**
 * An Item that is activated and removed from the Map when touched by an
 * Entity.
 */

import model.Location;

public abstract class OneShotItem extends Item {
	public OneShotItem(String name, Location location) {
		super(name, location);
	}
}
