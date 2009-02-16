package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.Location;
import view.Drawer;

public class Boulder extends Obstacle {

	public Boulder(Location location) {
		super("Boulder", location);
	}

	public void draw(Drawer d) {
		d.drawBoulder(this);
	}
}
