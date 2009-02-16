package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.Entity;
import model.Location;
import view.Drawer;

public class Shield extends TakeableItem {
	public Shield() {
		super("Shield");
	}

	public void draw(Drawer d) {
		d.drawShield(this);
	}

	
	public void activate(Entity e) {
		use(e);
	}

	public void use(Entity e) {
		e.equipAuxiliary(this);
	}
}
