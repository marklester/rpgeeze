package model.item;

/**
 * Makes the Tile that contains it permanently impassable.
 */

import model.entity.PC;

public abstract class Obstacle extends Item {
	public Obstacle(String name) {
		super(name);
	}

	public boolean isPassable() {
		return false;
	}

	public final void activate(PC pc) {
		use(pc);
	}
	
	public void use(PC pc) {
		
	}
}
