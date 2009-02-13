package model;

import view.Drawer;

/*
 * 
 * Obstacle - makes tile permanently impassable
 * 
 */


public abstract class Obstacle extends Item {
	public Obstacle(Location location) {
		super("Obstacle",location);
	}
	
	public void draw(Drawer d) {
		d.drawObstacle(this);
	}
	
	public void activate()
	{
		//do nothing
	}
}

