package model;

public enum Direction {
	NORTH(0, -1),
	SOUTH(0, 1),
	EAST(1, 0),
	WEST(-1, 0),
	
	NORTHEAST(NORTH.x + EAST.x, NORTH.y + EAST.y),
	NORTHWEST(NORTH.x + WEST.x, NORTH.y + WEST.y),
	SOUTHEAST(SOUTH.x + EAST.x, SOUTH.y + EAST.y),
	SOUTHWEST(SOUTH.x + WEST.x, SOUTH.y + WEST.y);
	
	private final int x;
	private final int y;

	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static Direction forXY(int x, int y) {
		Direction ret = null;
		for(Direction d: Direction.values())
			if(d.x == x && d.y == y) {
				ret = d;
				break;
			}
		return ret;
	}
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public Location toLocation() {
		return new Location(x, y);
	}
	
	public Command moveCommand() {
		return new Command() {
			public void execute(Model m) {
				m.moveAvatarRequest(Direction.this.toLocation());
			}
		};
	}
}
