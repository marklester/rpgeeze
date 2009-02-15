package model.items;

/**
 * An Item that is added to an Entity's Inventory on touch.
 */

import model.Location;

public abstract class TakeableItem extends Item {

	public TakeableItem(String name, Location location) {
		super(name, location);
	}

	public void activate() {

	}

}
