package model.items;

import model.Location;
import view.Drawer;

/*
 * 
 * Obstacle - makes tile permanently impassable
 * 
 */


public abstract class Obstacle extends Item {
	public Obstacle(String name, Location location) {
		super(name,location);
	}
	
	
	public void activate()
	{
		//do nothing
	}
}

