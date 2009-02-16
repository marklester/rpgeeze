package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import view.Drawer;
import model.Entity;

public class PotionLife extends TakeableItem {

	public PotionLife() {
		super("Potion Life");
	}

	public void draw(Drawer d) {
		d.drawPotionLife(this);
	}
	
	public void activate(Entity e) {
		use(e);
	}

	private void use(Entity e) {
	}
}
