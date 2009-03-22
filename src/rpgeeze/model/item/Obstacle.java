package rpgeeze.model.item;

import rpgeeze.model.Entity;
import rpgeeze.model.IllegalMoveException;
import rpgeeze.model.Tile;

/**
 * Makes the Tile that contains it permanently impassable.
 */


public abstract class Obstacle extends Item {
	public Obstacle(String name) {
		super(name);
	}

	public void activate(Entity entity, Tile tile) throws IllegalMoveException {
		throw new IllegalMoveException("Obstacle present");
	}
}

