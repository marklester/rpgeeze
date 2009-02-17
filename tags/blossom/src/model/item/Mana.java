package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import view.Drawer;
import model.Entity;

public class Mana extends TakeableItem {

	public Mana() {
		super("Mana");
	}

	public void draw(Drawer d) {
		d.drawMana(this);
	}
	
	public void activate(Entity e) {
		use(e);
	}

	private void use(Entity e) {
		e.getStats().incMana(100);
		e.getInventory().removeItem(this);
		view.Console.getInstance().writeLine("Used Mana Potion.");
	}
}
