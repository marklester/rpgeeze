package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.Entity;
import model.Location;
import view.Drawer;

public class Arrows extends TakeableItem {

	public Arrows(Location location) {
		super("Arrows", location);
	}

	public void draw(Drawer d) {
		d.drawArrows(this);
	}

	@Override
	public void activate(Entity e) {
		
	}
	
}
