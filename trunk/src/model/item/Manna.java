package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.Location;
import view.Drawer;

public class Manna extends OneShotItem {

	public Manna(Location location) {
		super("Manna", location);
	}

	public void draw(Drawer d) {
		d.drawManna(this);
	}
}
