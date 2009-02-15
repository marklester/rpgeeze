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
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public Command moveCommand() {
		return new Command() {
			public void execute(Model m) {
				m.moveAvatarRequest(Direction.this);
			}
		};
	}
}
