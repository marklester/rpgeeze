package model.items;

import model.Location;

/*
 * 
 * Obstacle - makes tile permanently impassable
 * 
 */

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
