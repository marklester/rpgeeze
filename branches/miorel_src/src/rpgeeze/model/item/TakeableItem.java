package rpgeeze.model.item;

import rpgeeze.model.Entity;
import rpgeeze.model.Tile;

/**
 * An Item that is added to an Entity's Inventory on touch.
 */

public abstract class TakeableItem extends Item {
	
	public TakeableItem(String name) {
        super(name);
	}

	public void activate(Entity entity, Tile tile) {
		if(	entity.getInventory().addItem(this, true))
			tile.setItem(null);
	}
}
