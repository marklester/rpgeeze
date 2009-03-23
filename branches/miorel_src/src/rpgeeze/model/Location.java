package rpgeeze.model;

import rpgeeze.util.Direction;

public class Location {
	private final int x;
	private final int y;

	public Location() {
		this.x = 0;
		this.y = 0;
	}

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public Location add(Location l) {
		return new Location(x + l.x, y + l.y);
	}

	public Location subtract(Location l) {
		return new Location(x - l.x, y - l.y);
	}
	
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}

	public Direction closestDirection() {
		Direction ret = Direction.forXY(getX(), getY());
		if(ret == null) ret = Direction.NORTH;
		return ret;
	}
	
}
