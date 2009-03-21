package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import view.Drawer;
import model.entity.Entity;

public class PotionLife extends TakeableItem {

	public PotionLife() {
		super("Potion Life");
	}
	
	public void activate(Entity e) {
		use(e);
	}

	private void use(Entity e) {
		e.getStats().incLife(100);
		e.getInventory().removeItem(this);
		view.Console.getInstance().writeLine("Used Heath Potion.");
	}
}
