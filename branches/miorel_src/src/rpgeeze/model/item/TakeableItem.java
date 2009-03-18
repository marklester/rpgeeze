package rpgeeze.model.item;

import rpgeeze.model.Entity;

public abstract class TakeableItem extends Item {
	public void activate(Entity entity) {
		// add self to entity's inventory
		
		// don't worry about checking if there is space:
		// by TDA entity will just ignore request to add to inventory if it's full 
	}
}
