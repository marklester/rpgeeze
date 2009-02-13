package model;

import view.Drawer;


public class Boulder extends Obstacle {
	public Boulder() {
		super("Boulder",new Location (0,0));
	}
	
	public Boulder(Location location) {
		super("Boulder", location);
	}
	
	
	public void draw(Drawer d) {
		d.drawBoulder(this);
	}
	
	public void activate()
	{
		//do nothing
	}

}
