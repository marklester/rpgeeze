package model.item;

/**
 * An Item that is activated and removed from the Map when touched by an
 * Entity.
 */

import model.entity2.PC;

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
