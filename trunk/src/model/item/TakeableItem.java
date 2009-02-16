package model.item;

/**
 * An Item that is added to an Entity's Inventory on touch.
 */

public abstract class TakeableItem extends Item {
	public TakeableItem(String name) {
		super(name);
	}
}
