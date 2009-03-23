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

	public void activate(PC pc, Tile tile) {
		pc.addItem(this);
		use(pc);		
	}
}