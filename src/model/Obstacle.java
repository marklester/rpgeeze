package model;

import view.Drawer;

/*
 * 
 * Obstacle - makes tile permanently impassable
 * 
 */


public abstract class Obstacle extends Item {
	public Obstacle() {
		super("Obstacle");
	}
	
	public void draw(Drawer d) {
		d.drawObstacle(this);
	}
	
}

