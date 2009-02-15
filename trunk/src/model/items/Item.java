package model.items;

/*
 * 
 * An item is an immobile thing which has a specific location.
 *    
 */

import model.Location;
import view.Drawable;

public abstract class Item implements Drawable, Cloneable {

	public String name;
	public Location location;

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

	public Item clone() throws CloneNotSupportedException {
		Item i = (Item) super.clone();
		i.location = i.location.clone();
		return i;
	}

}
