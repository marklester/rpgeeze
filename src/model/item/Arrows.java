package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.Entity;
import view.Drawer;

public class Arrows extends TakeableItem {

	public Arrows() {
		super("Arrows");
	}

	public void draw(Drawer d) {
		d.drawArrows(this);
	}

	public void activate(Entity e) {
		use(e);
	}

	public void use(Entity e) {
		
	}
}
