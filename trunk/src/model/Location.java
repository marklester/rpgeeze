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
	
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
	
	/*
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setXY(int x, int y) {
		this.x = x; this.y = y;
	}
	/* I think Location should be immutable. -- Miorel */
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
}
