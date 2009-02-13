package model;

/*
 * 
 * An item is an immobile thing which has a specific location.
 *    
 */

import view.*;

public abstract class Item implements Drawable {

	public String name;
	public Location location;
		
	public Item(String name, Location location)
	{
		this.name = name;
		this.location = location;
	}
	
	public String toString() {
		return name;
	}

}

