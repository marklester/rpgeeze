package model;

public abstract class Occupation {
//OccupationType
//StatSet
	protected final String name;
	
	protected Occupation(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}

