package model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import model.Location;
import view.Drawer;

public class Mana extends OneShotItem {

	public Mana() {//Location location) {
		super("Mana");//, location);
	}

	public void draw(Drawer d) {
		d.drawMana(this);
	}
}
