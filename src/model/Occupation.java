package model;

public abstract class Occupation implements Cloneable {
	// OccupationType
	// StatSet
	protected final String name;
	public Stats stats;

	protected Occupation(String name, Stats stats) {
		this.name = name;
		this.stats = stats;
	}
	
	public Occupation clone() throws CloneNotSupportedException{
		Occupation o = (Occupation) super.clone();
		o.stats = this.stats.clone();
		return o;
		
	}
	
	public String toString() {
		return this.name;
	}
}