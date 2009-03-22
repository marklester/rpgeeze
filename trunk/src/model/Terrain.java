package model;

import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.entity.Entity;

import view.Drawable;
import view.Drawer;

/**
 * Indicates the physical characteristics of the landscape. 
 */

public abstract class Terrain implements Drawable {
	private static final Pattern pattern = Pattern.compile("<terrain>(.*)</terrain>");
	private static Hashtable<String, Terrain> prototypes = new Hashtable<String, Terrain>();
	
	static {
		for(Terrain t: new Terrain[] {
			MountainTerrain.getInstance(),
			GrassTerrain.getInstance(),
			WaterTerrain.getInstance(),
		})
			prototypes.put(t.toString(), t);
	}
	
	protected final String name;

	protected Terrain(String name) {
		this.name = name;
	}

	// Returning true by default will allow us to create different shades of
	// passable terrain
	// that won't necessarily affect the player. Similar to eye-candy
	public boolean isPassable(Entity e) {
		return true;
	}

	public String toString() {
		return this.name;
	}
	
	public void draw(Drawer d) {
		d.drawMe(name);
	}

	public String toXml() {
		return toXml("");
	}
	//Used to get a Terrain Prototype
	public static Terrain getTerrain(String key){
		return (Terrain)prototypes.get(key);
	}
	
	public String toXml(String indent) {
		StringBuilder sb = new StringBuilder();
		sb.append(indent + "<terrain>" + name + "</terrain>");
		return sb.toString();
	}
	
	public static Terrain fromXml(String xml) {
		Matcher mat = pattern.matcher(xml);
		if(!mat.matches())
			throw new RuntimeException("Bad XML for Terrain");
		return prototypes.get(mat.group(1));
	}
}
