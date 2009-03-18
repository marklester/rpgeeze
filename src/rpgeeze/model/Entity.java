package rpgeeze.model;

import rpgeeze.math.Vector;
import rpgeeze.phys.GameObject;
import rpgeeze.util.Direction;

public class Entity extends GameObject {
	private Direction facing;
	
	public Vector getFacingDirection() {
		return facing;
	}
	
	public double getMass() {
		return 1;
	}

	public Vector getAcceleration() {
		return null;
	}

	public Vector getPosition() {
		return null;
	}

	public Vector getVelocity() {
		return null;
	}
}
