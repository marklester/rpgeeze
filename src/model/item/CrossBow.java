package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.Location;
import view.Drawer;

public class CrossBow extends InteractiveItem {
	public CrossBow() {
		super("Cross Bow");//, location);
	}

	public void draw(Drawer d) {
		d.drawCrossBow(this);
	}
	public void activate(model.Entity e) {
		e.equipWeapon(this);
		e.unequipAuxilary();
	}
}
