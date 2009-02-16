package model.item;

/**
 * An immobile Map element with a specific location.    
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Hashtable;

import view.Drawable;
import model.Entity;

public abstract class Item implements Drawable, Cloneable {
	protected final String name;
	
	private static Pattern pattern = Pattern.compile("<item>(.*)</item>");
	private static Hashtable<String, Item> prototypes = new Hashtable<String, Item>();
	
	static {
		for(Item i: new Item[] {
			new Sword(),
			new Boulder(),
			new CrossBow(),
			new PotionLife(),
			new Shield(),
			new RedArmor(),
			new Boots(),
			new Arrows(),
			new Mana(),
		})
			prototypes.put(i.toString(), i);
	}
	
	public Item(String name) {
		this.name = name;
	}

	public Item clone() {
		Item ret = null;
		try {
			ret = (Item) super.clone();
		}
		catch(CloneNotSupportedException e) {}
		return ret;
	}
	
	public boolean isPassable() {
		return true;
	}

	public String toString() {
		return this.name;
	}
	
	public abstract void activate(Entity e);

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
