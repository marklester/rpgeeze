package rpgeeze.model.item;

import rpgeeze.model.Entity;

/** 
 * Will probably sub type weapon later on but doesn't yet
 * can only be used by Smasher Occupation 
 */



public class Sword extends TakeableItem {
	public Sword() {
		super("Sword");
	}

	public void activate(Entity e) {
		use(e);
	}

	public void use(Entity e) {
		/*e.equipWeapon(this);
		view.Console.getInstance().writeLine("Sword has been equipped.");
		*/
	}
}
