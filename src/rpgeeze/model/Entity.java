package rpgeeze.model;

import rpgeeze.math.Vector;
import rpgeeze.util.Direction;

public class Entity {
	private Direction facing;
	private Tile tile;
	
	public Vector getFacingDirection() {
		return facing;
	}

	public Tile getTile() {
		return tile;
	}
	
	public void setTile(Tile newTile) {
		tile = newTile;
	}
}
