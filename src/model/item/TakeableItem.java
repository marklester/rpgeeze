package model.item;

/**
 * An Item that is added to an Entity's Inventory on touch.
 */

import model.Location;
import model.Entity;

public abstract class TakeableItem extends Item {
	public TakeableItem(String name, Location location) {
		super(name, location);
	}

	public final void activate(Entity e) {
	}
}
