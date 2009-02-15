package model;

public class Location implements Cloneable{
	
	private int x;
	private int y;
	
	public Location() {
		x = 0;
		y = 0;
	}
	
	public Location(int x, int y) {
		this.x = x; this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Location clone() throws CloneNotSupportedException
	{
		return (Location)super.clone();
	}
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
}
