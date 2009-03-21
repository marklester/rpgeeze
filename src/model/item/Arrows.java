package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.entity.Entity;
import view.Drawer;

public class Arrows extends TakeableItem {

	public Arrows() {
		super("Arrows");
	}

	public void activate(Entity e) {
		use(e);
	}

	public void use(Entity e) {
		//view.Console.getInstance().writeLine("You have picked up Arrows.");
	}
}
