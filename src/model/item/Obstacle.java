package model.item;

/**
 * Makes the Tile that contains it permanently impassable.
 */

import model.Location;
import model.Entity;

public abstract class Obstacle extends Item {
	public Obstacle(String name) {//, Location location) {
		super(name);//, location);
	}

	public boolean isPassable() {
		return false;
	}

	public final void activate(Entity e) {
		use(e);
	}
	public void use(Entity e)
	{
		
	}
}
