package rpgeeze.model.item;

import rpgeeze.model.Entity;

/** 
 *Mana Potion can be picked up and used later
 *
 */

public class ManaPotion extends TakeableItem {

	public ManaPotion() {
		super("Mana Potion");
	}
	
	public void activate(Entity e) {
		use(e);
	}

	private void use(Entity e) {
		/*e.getStats().incMana(100);
		e.getInventory().removeItem(this);
		view.Console.getInstance().writeLine("Used Mana Potion.");
		*/
	}
}
