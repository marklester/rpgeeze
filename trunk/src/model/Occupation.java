package model;

public abstract class Occupation implements Cloneable {
	// OccupationType
	// StatSet
	protected final String name;
	protected Stats stats;

	protected Occupation(String name, Stats stats) {
		this.name = name;
		this.stats = stats;
	}
	
	public Occupation clone() {
		Occupation o = null;
		try {
			o = (Occupation) super.clone();
		}
		catch(CloneNotSupportedException e) {
		}
		o.stats = this.stats.clone();
		return o;
		
	}
	
	public String toString() {
		return this.name;
	}
	
	public String toXml() {
		return toXml("");
	}
	
	public String toXml(String indent) {
		StringBuilder sb = new StringBuilder();
		sb.append(indent + "<occupation>" + name + "</occupation>");
		return sb.toString();
	}
}
