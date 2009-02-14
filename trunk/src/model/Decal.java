package model;

/*
 * Decals augment the terrain and primarily serve as eye-candy. They do not
 * intrinsically affect game play -- though one may be used to mark a tile to
 * indicate an area-effect, &c.
 */

import view.*;

public abstract class Decal implements Drawable, Cloneable {
	
    protected final String name;	
	
	protected Decal(String name) {
	    this.name = name;
	}
	
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
	
    public String toString() {
	    return name;
	}
}

