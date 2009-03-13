package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.Entity;
import view.Drawer;

public class RedArmor extends TakeableItem {
	public RedArmor() {
		super("Red Armor");
	}

	public void activate(Entity e) {
		use(e);
	}

	public void use(Entity e) {
		e.equipArmor(this);
		view.Console.getInstance().writeLine("Armor has been equipped");
	}
}
