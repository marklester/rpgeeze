package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.Location;
import view.Drawer;

public class Mana extends TakeableItem {

	public Mana() {//Location location) {
		super("Mana");//, location);
	}

	public void draw(Drawer d) {
		d.drawMana(this);
	}
	public void activate(model.Entity e)
	{
		use(e);
	}
	private void use(model.Entity e)
	{
		
	}
}
