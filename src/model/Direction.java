package model;

public class Direction {
	private int x;
	private int y;
	
	public static final Direction NORTH = new Direction(0, -1);
	public static final Direction SOUTH = new Direction(0, 1);
	public static final Direction EAST = new Direction(1, 0);
	public static final Direction WEST = new Direction(-1, 0);
	
	public static final Direction NORTHEAST = new Direction(1, 1);
	public static final Direction NORTHWEST = new Direction(-1, 1);
	public static final Direction SOUTHEAST = new Direction(1, -1);
	public static final Direction SOUTWEST = new Direction(-1, -1);
	
	public Direction(int x, int y) 	{
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;	
	}
	
	public int getY() {
		return y;
	}

	public boolean equals(Object o) {
		boolean ret = false;
		if(o instanceof Direction) {
			Direction d = (Direction) o;
			if(x == d.x && y == d.y)
				ret = true;
		}
		return ret;
	}
	
	public int hashCode() {
		return x + 23 * y;
	}
}
