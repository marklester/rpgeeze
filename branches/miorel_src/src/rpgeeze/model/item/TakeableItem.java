package rpgeeze.model.item;

import rpgeeze.model.entity.*;
import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;

/**
 * An Item that is added to an Entity's Inventory on touch.
 */

public abstract class TakeableItem extends Item {
	public TakeableItem(String name) {
        super(name);
	}

	public final void activate(Entity entity, Tile tile) {
		if(entity.pickUp(this))
			tile.setItem(null);
	}
	
	public abstract void use(Entity entity);
}
