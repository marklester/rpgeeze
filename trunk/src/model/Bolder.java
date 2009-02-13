package model;

import view.Drawer;


public class Bolder extends Obstacle {
	public Bolder()
	{
		super("Bolder",new Location (0,0));
	}
	public Bolder(Location location) {
		super("Bolder",location);
	}
	
	
	public void draw(Drawer d) {
		d.drawBolder(this);
	}
	
	public void activate()
	{
		//do nothing
	}

}
