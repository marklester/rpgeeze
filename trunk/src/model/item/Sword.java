package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.entity.Entity;
import view.Drawer;

public class Sword extends TakeableItem {
	public Sword() {
		super("Sword");
	}

	public void activate(Entity e) {
		use(e);
	}

	public void use(Entity e) {
		e.equipWeapon(this);
		view.Console.getInstance().writeLine("Sword has been equipped.");
	}
}
