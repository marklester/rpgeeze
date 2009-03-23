package rpgeeze.model.item;

import rpgeeze.model.entity.Entity;

/** 
 * Potion that goes in inventory
 */


public class HealthPotion extends TakeableItem {

	public HealthPotion() {
		super("Health Potion");
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
