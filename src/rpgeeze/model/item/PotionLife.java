package rpgeeze.model.item;

import rpgeeze.model.Entity;

/** 
 * Potion that goes in inventory
 */


public class PotionLife extends TakeableItem {

	public PotionLife() {
		super("Potion Life");
	}
	
	public void activate(Entity e) {
		use(e);
	}

	private void use(Entity e) {
		/*e.getStats().incLife(100);
		e.getInventory().removeItem(this);
		view.Console.getInstance().writeLine("Used Heath Potion.");
		*/
	}
}
