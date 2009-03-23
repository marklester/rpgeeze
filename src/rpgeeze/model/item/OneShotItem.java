package rpgeeze.model.item;

import rpgeeze.model.entity.PC;
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

	public void activate(PC pc) {
		use(pc);
	}
	
	public void use(PC pc)	{
	}
}


