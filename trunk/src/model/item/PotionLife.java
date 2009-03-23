package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.entity2.PC;

public class PotionLife extends TakeableItem {

	public PotionLife() {
		super("Potion Life");
	}

	public void use(PC pc) {
		pc.addHealth(200);
		pc.getInventory().removeItem(this);
		view.Console.getInstance().writeLine("Used Heath Potion.");
	}
}
