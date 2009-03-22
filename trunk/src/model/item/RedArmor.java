package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.entity.PC;

public class RedArmor extends TakeableItem {
	public RedArmor() {
		super("Red Armor");
	}

	public void use(PC pc) {
		pc.equipArmor(this);
		view.Console.getInstance().writeLine("Armor has been equipped");
	}
}
