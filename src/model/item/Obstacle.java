package model.item;

/**
 * Makes the Tile that contains it permanently impassable.
 */

import model.Location;

public abstract class Obstacle extends Item {
	public Obstacle(String name, Location location) {
		super(name, location);
	}

	public boolean isPassable() {
		return false;
	}

	public void activate() {
		// do nothing
	}
}
