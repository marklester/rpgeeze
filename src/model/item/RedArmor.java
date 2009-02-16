package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.Entity;
import model.Location;
import view.Drawer;

public class RedArmor extends TakeableItem {

	public RedArmor() {//Location location) {
		super("Red Armor"); //, location);
	}

	public void draw(Drawer d) {
		d.drawRedArmor(this);
	}

	@Override
	public void activate(Entity e) {
		use(e);
	}
	public void use(Entity e)
	{
		e.equipArmor(this);
	}
}
