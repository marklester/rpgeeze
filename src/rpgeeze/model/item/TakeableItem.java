package rpgeeze.model.item;

import rpgeeze.model.Entity;

/**
 * An Item that is added to an Entity's Inventory on touch.
 */

public abstract class TakeableItem extends Item {
	
	public TakeableItem(String name) {
        super(name);
	}

	public void activate(Entity entity) {
		// add self to entity's inventory
		
		// don't worry about checking if there is space:
		// by TDA entity will just ignore request to add to inventory if it's full 
	}
}
