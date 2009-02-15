package model.items;

/**
 * An immobile Map element with a specific location.    
 */

import model.Location;
import view.Drawable;

public abstract class Item implements Drawable, Cloneable {
	protected final String name;
	protected Location location;

	public Item(String name, Location location) {
		this.name = name;
		this.location = location;
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

	public Item clone() {
		Item i = null;
		try {
			i = (Item) super.clone();
		}
		catch(CloneNotSupportedException e) {
		}
		return i;
	}

}
