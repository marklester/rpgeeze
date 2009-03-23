package rpgeeze.model.item;

import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;

/**
 * An Item that is activated and removed from the Map when touched by an
 * Entity.
 */

public abstract class OneShotItem extends Item {
	public OneShotItem(String name) {
		super(name);
	}

	public final void activate(Entity entity, Tile tile) {
		doActivate(entity, tile);
		tile.setItem(null);
	}
	
	protected abstract void doActivate(Entity entity, Tile tile);
}


