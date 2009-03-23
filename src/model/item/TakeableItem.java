package model.item;

import model.entity2.*;

/**
 * An Item that is added to an Entity's Inventory on touch.
 */

public abstract class TakeableItem extends Item {
	public TakeableItem(String name) {
		super(name);
	}
	
	public void activate(PC pc)
	{
		pc.addItem(this);
		use(pc);
	}
}
