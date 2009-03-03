package model;

import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Occupation implements Cloneable {
	private static final Pattern pattern = Pattern.compile("<occupation>(.*?)</occupation>");

	private static Hashtable<String, Occupation> prototypes = new Hashtable<String, Occupation>();
	
	static {
		for(Occupation o: new Occupation[] {
			new Smasher(),
			new Summoner(),
			new Sneak()
		})
			prototypes.put(o.toString(), o);
	}
	
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
	
	public static Occupation fromXml(String xml) {
		Matcher mat = pattern.matcher(xml);
		if(!mat.matches())
			throw new RuntimeException("Bad XML for Occupation");
		return prototypes.get(mat.group(1)).clone();
	}
	
	public int hashCode() {
		return toString().hashCode();
	}
	
	public boolean equals(Object o) {
		boolean ret = false;
		if(o instanceof Occupation)
			ret = o.toString().equals(toString());
		return ret;
	}
}
