package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.Location;
import view.Drawer;

public class PotionLife extends TakeableItem {

	public PotionLife() {//Location location) {
		super("Potion Life"); //, location);
	}

	public void draw(Drawer d) {
		d.drawPotionLife(this);
	}
	public void activate(model.Entity e)
	{
		use(e);
	}
	private void use(model.Entity e)
	{
		
	}
}
