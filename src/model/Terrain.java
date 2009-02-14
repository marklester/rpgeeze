package model;
import view.Drawable;

/*
 * 
 * Terrains indicate the physical characteristics of the "landscape."
 * 
 */


public abstract class Terrain implements Drawable, Cloneable {

	protected final String name;
	
	protected Terrain(String name) {
		this.name = name;
	}
	
	//Returning true by default will allow us to create different shades of passable terrain
	//that won't necessarily affect the player. Similar to eye-candy
	public boolean isPassable(Entity e) {
		return true;
	}
	
	public Object clone() throws CloneNotSupportedException 
	{
        return super.clone();
	}

	
	public String toString(){
		return name;
	}
	
}

