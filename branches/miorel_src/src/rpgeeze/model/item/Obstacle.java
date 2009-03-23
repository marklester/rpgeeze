package rpgeeze.model.item;

import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;
import rpgeeze.model.entity.IllegalMoveException;

/**
 * Makes the Tile that contains it permanently impassable.
 */


public abstract class Obstacle extends Item {
	public Obstacle(String name) {
		super(name);
	}

	public final void activate(Entity entity, Tile tile) throws IllegalMoveException {
		throw new IllegalMoveException("Obstacle present");
	}
}

