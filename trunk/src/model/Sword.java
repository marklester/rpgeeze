package model;

import view.Drawer;

public class Sword extends TakeableItem {
	
	public Sword(Location location) {
		super("Sword",location);
	}
	
	public void draw(Drawer d) {
		d.drawSword(this);
	}
}
