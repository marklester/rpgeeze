package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.Entity;
import view.Drawer;

public class Boots extends TakeableItem {
	public Boots() {
		super("Boots");
	}

	public void draw(Drawer d) {
		d.drawBoots(this);
	}
	
	public void activate(Entity e) {
		use(e);
	}
	
	public void use(Entity e) {
		e.equipBoots(this);
	}
}