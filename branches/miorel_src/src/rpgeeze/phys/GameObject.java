package rpgeeze.phys;

import rpgeeze.math.Vector;

public abstract class GameObject {
	public abstract double getMass();
	public abstract Vector getPosition();
	public abstract Vector getVelocity();
	public abstract Vector getAcceleration();
}
