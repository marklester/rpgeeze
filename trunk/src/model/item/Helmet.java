package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.Entity;
import model.Location;
import view.Drawer;

public class Helmet extends TakeableItem {

	public Helmet() { //Location location) {
		super("Helmet");//, location);
	}

	public void draw(Drawer d) {
		d.drawHelmet(this);
	}
	
	@Override
	public void activate(Entity e) {
		use(e);
	}
	public void use(Entity e)
	{
		e.equipHead(this);
	}
}
