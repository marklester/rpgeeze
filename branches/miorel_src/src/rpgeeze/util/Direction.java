package rpgeeze.util;

import rpgeeze.math.Vector;

public enum Direction {
	NORTH(0, 1),
	SOUTH(0, -1),
	EAST(1, 0),
	WEST(-1, 0),
	NORTHWEST(NORTH, WEST),
	NORTHEAST(NORTH, EAST),
	SOUTHWEST(SOUTH, WEST),
	SOUTHEAST(SOUTH, EAST);
	
	private final int x, y;
	
	private Direction(Direction d0, Direction d1) {
		this(d0.x + d1.x, d0.y + d1.y);
	}
	
	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
