package model;

/*
 * 
 * An item is an immobile thing which has a specific location.
 *    
 */

import view.*;

public abstract class Item implements Drawable, Cloneable {

	public String name;
	public Location location;
		
	public Item(String name, Location location)
	{
		this.name = name;
		this.location = location;
	}
	
	public boolean isPassable()
	{
		return true;
	}
	
	public String toString() {
		return name;
	}
	
	public Object clone() throws CloneNotSupportedException
	{
		Item i = (Item)super.clone();
		i.location = (Location)i.location.clone();
		return i;
	}

}

