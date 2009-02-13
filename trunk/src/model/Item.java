package model;

/*
 * 
 * An item is an immobile thing which has a specific location.
 *    
 */

import view.*;

public abstract class Item implements Drawable {

	protected final String name;
	public Location location;
	
	protected Item(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return name;
	}

}

