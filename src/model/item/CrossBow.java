package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.Entity;
import view.Drawer;

public class CrossBow extends TakeableItem {
	public CrossBow() {
		super("Crossbow");
	}

	public void activate(Entity e) {
		use(e);
	}

	public void use(Entity e) {
		e.equipWeapon(this);
		e.unequipAuxiliary();
		view.Console.getInstance().writeLine("Cross Bow has been equipped.");
	}
}
