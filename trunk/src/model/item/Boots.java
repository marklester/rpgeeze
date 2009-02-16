package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.Entity;
import model.Location;
import view.Drawer;

public class Boots extends TakeableItem {

	public Boots() { //Location location) {
		super("Boots");//, location);
	}

	public void draw(Drawer d) {
		d.drawBoots(this);
	}
	
	@Override
	public void activate(Entity e) {
		use(e);
	}
	public void use(Entity e)
	{
		e.equipBoots(this);
	}
}