package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.Location;
import view.Drawer;

public class Sword extends TakeableItem {

	public Sword(Location location) {
		super("Sword", location);
	}

	public void draw(Drawer d) {
		d.drawSword(this);
	}
}
