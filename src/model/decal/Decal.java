package model.decal;

/*
 * Decals augment the terrain and primarily serve as eye-candy. They do not
 * intrinsically affect game play -- though one may be used to mark a tile to
 * indicate an area-effect, &c.
 */

import java.util.Hashtable;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import view.Drawable;

public abstract class Decal implements Drawable, Cloneable {
	private static Pattern pattern = Pattern.compile("<decal><name>(.*)</name></decal>");
	private static Hashtable<String, Decal> prototypes = new Hashtable<String, Decal>();
	
	static {
		for(Decal d: new Decal[] {
			new GoldStar(),
			new RedCross(),
			new SkullAndCrossbones(),
		})
			prototypes.put(d.toString(), d);
	}
	
	protected final String name;

	protected Decal(String name) {
		this.name = name;
	}

	public Decal clone() {
		Decal clone = null;
		try {
			clone = (Decal) super.clone();
		}
		catch(CloneNotSupportedException e) {
		}
		return clone;
	}

	public String toString() {
		return this.name;
	}
	
	public String toXml() {
		return toXml("");
	}
	
	public String toXml(String indent) {
		StringBuilder sb = new StringBuilder();
		sb.append(indent + "<decal>\n");
		sb.append(indent + "<name>" + name + "</name>\n");
		sb.append(indent + "</decal>");
		return sb.toString();
	}
	
	public static Decal fromXml(String xml) {
		Matcher mat = pattern.matcher(xml);
		if(!mat.matches())
			throw new RuntimeException("Bad XML for Decal");
		Decal ret = prototypes.get(mat.group(1)).clone();
		return ret;
	}
}
