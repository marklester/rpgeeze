package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.entity.PC;

public class CrossBow extends TakeableItem {
	public CrossBow() {
		super("Crossbow");
	}

	public void activate(PC pc) {
		use(pc);
	}

	public void use(PC pc) {
		pc.equipWeapon(this);
		pc.unequipAuxiliary();
		view.Console.getInstance().writeLine("Cross Bow has been equipped.");
	}
}
