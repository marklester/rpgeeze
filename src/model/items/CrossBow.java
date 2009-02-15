package model.items;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.Location;
import view.Drawer;

public class CrossBow extends InteractiveItem {
	public CrossBow(Location location) {
		super("Cross Bow", location);
	}

	public void draw(Drawer d) {
		d.drawCrossBow(this);
	}

	public void activate() {

	}

}
