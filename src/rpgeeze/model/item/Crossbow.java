package rpgeeze.model.item;

import rpgeeze.model.Entity;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

public class Crossbow extends TakeableItem {
	public Crossbow() {
		super("Crossbow");
	}

	public void activate(Entity e) {
		use(e);
	}

	public void use(Entity e) {
		/*e.equipWeapon(this);
		e.unequipAuxiliary();
		view.Console.getInstance().writeLine("Cross Bow has been equipped.");
		*/
	}
}
