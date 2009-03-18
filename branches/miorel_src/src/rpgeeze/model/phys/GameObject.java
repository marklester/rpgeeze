package rpgeeze.model.phys;

import rpgeeze.math.Vector;
import rpgeeze.model.Tile;

public interface GameObject {
	public double getMass();
	
	public Vector getPosition();
	public Vector getVelocity();
	public Vector getAcceleration();
	
	public Tile getTile();
}