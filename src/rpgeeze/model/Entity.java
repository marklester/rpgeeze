package rpgeeze.model;

import rpgeeze.math.Vector;
import rpgeeze.util.Direction;

public class Entity implements Visitable {
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
	
	public void accept(Visitor visitor) {
		visitor.visitEntity(this);
	}
}
