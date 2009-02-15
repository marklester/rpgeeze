package model;

public class Direction {
	private final int x;
	private final int y;

	public static final Direction NORTH = new Direction(0, -1);
	public static final Direction SOUTH = new Direction(0, 1);
	public static final Direction EAST = new Direction(1, 0);
	public static final Direction WEST = new Direction(-1, 0);

	public static final Direction NORTHEAST = NORTH.add(EAST);
	public static final Direction NORTHWEST = NORTH.add(WEST);
	public static final Direction SOUTHEAST = SOUTH.add(EAST);
	public static final Direction SOUTHWEST = SOUTH.add(WEST);

	public Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public boolean equals(Object o) {
		boolean ret = false;
		if(o instanceof Direction) {
			Direction d = (Direction) o;
			if(this.x == d.x && this.y == d.y)
				ret = true;
		}
		return ret;
	}

	public int hashCode() {
		return this.x + 23 * this.y;
	}

	private Direction add(Direction d) {
		return new Direction(this.x + d.x, this.y + d.y);
	}

	public Command moveCommand() {
		return new Command() {
			public void execute(Model m) {
				m.moveAvatarRequest(Direction.this);
			}
		};
	}
}
