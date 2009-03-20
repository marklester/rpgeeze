package rpgeeze.model.item;

import rpgeeze.model.Entity;
import rpgeeze.model.InaccessibleAreaException;

public abstract class Obstacle extends Item {
	public void activate(Entity entity) {
		// did you just try to walk through an obstacle?
		// no can do
		throw new InaccessibleAreaException();
	}
}
