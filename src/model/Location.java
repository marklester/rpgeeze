package model;

public class Location implements Cloneable {

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

	public Location clone() throws CloneNotSupportedException {
		return (Location) super.clone();
	}

	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}

}
