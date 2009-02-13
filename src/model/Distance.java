package model;

public class Distance {
	private int x;
	private int y;
	
	public static final Distance NORTH = new Distance(0, -1);
	public static final Distance SOUTH = new Distance(0, 1);
	public static final Distance EAST = new Distance(1, 0);
	public static final Distance WEST = new Distance(-1, 0);
	
	public Distance(int x, int y) 	{
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
