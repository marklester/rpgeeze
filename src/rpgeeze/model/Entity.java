package rpgeeze.model;

import rpgeeze.math.Vector;
import rpgeeze.model.phys.GameObject;
import rpgeeze.util.Direction;

public class Entity implements GameObject {
	private Direction facing;
	private Tile tile;
	
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

	public Tile getTile() {
		return tile;
	}
	
	public void setTile(Tile newTile) {
		tile = newTile;
	}
}
