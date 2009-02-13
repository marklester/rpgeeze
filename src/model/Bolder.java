package model;

import view.Drawer;


public class Bolder extends Obstacle {
	private static Bolder instance = null;
	public Bolder(Location location) {
		super("Bolder",location);
	}
	
	public static Bolder getInstance() {
		if(instance == null){
			Location location = new Location (0,0);
			instance = new Bolder(location);
		}
		return instance;
	}
	
	public void draw(Drawer d) {
		d.drawBolder(this);
	}
	
	public void activate()
	{
		//do nothing
	}

}
