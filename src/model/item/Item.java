package model.item;

/**
 * An immobile Map element with a specific location.    
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Hashtable;


import model.Location;
import view.Drawable;
import model.Entity;

public abstract class Item implements Drawable, Cloneable {
	protected final String name;
	protected Location location;
	
	private static Pattern pattern = Pattern.compile("<item>(.*)</item>");
	private static Hashtable<String, Item> prototypes = new Hashtable<String, Item>();
	
	static {
		for(Item i: new Item[] {
			new Sword(null),
			new Boulder(null),
			new CrossBow(null),
			new PotionLife(null),
			new Shield(null),
			new RedArmor(null),
			new Boots(null),
			new Arrows(null),
			new Mana(null),
		})
			prototypes.put(i.toString(), i);
	}
	
	public Item(String name, Location location) {
		this.name = name;
		this.location = location;
	}
	
	public Item(String name) {
		this(name, null);
	}

	public Location getLocation() {
		return this.location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}

	public boolean isPassable() {
		return true;
	}

	public String toString() {
		return this.name;
	}
	
	public abstract void activate(Entity e);

	public Item clone() {
		Item i = null;
		try {
			i = (Item) super.clone();
		}
		catch(CloneNotSupportedException e) {
		}
		return i;
	}
	
	public String toXml() {
		return toXml("");
	}
		
	public String toXml(String indent) {
		StringBuilder sb = new StringBuilder();
		sb.append(indent + "<item>" + name + "</item>");
		return sb.toString();
	}
	
	public static Item fromXml(String xml) {
		Matcher mat = pattern.matcher(xml);
		if(!mat.matches())
			throw new RuntimeException("Bad XML for Item");
		return prototypes.get(mat.group(1)).clone();
	}
}
