package rpgeeze.model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.entity.PC;

public class Mana extends TakeableItem {

	public Mana() {
		super("Mana");
	}

	public void use(PC pc) {
		pc.addMana(200);
		pc.removeItem(this);
		view.Console.getInstance().writeLine("Used Mana Potion.");
	}
}
