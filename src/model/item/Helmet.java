package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.entity.Entity;
import view.Drawer;

public class Helmet extends TakeableItem {
	public Helmet() {
		super("Helmet");
	}
	
	public void activate(Entity e) {
		use(e);
	}
	public void use(Entity e) {
		e.equipHead(this);
		view.Console.getInstance().writeLine("Helmet has been equipped.");
	}
}
