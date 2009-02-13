package model;

import view.Drawer;

public class Sword extends TakeableItem {
	private static Sword instance = null;
	
	public Sword(Location location) {
		super("Sword",location);
	}
	
	public static Sword getInstance() {
		if(instance == null){
			Location location = new Location (0,0);
			instance = new Sword(location);
		}
		return instance;
	}
	
	public void draw(Drawer d) {
		d.drawSword(this);
	}
}
