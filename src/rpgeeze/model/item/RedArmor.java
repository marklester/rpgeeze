package rpgeeze.model.item;

import rpgeeze.model.Entity;

/** 
 * Equippable Item Probably brings up Defense
 */

public class RedArmor extends TakeableItem {
	public RedArmor() {
		super("Red Armor");
	}

	public void activate(Entity e) {
		use(e);
	}

	public void use(Entity e) {
		/*e.equipArmor(this);
		view.Console.getInstance().writeLine("Armor has been equipped");
		*/
	}
}
