package model;

import view.*;

public class Entity implements Drawable {
	private Stats stats;
	private Inventory inventory;
	private Occupation occupation;
	
	private Direction facing = Direction.EAST;
	
//    Location
//    Name
//    EquippedItems
	
	public Entity(Occupation occupation) {
		this.occupation = occupation;
	}
	
	public void draw(Drawer d) {
		d.drawEntity(this);
	}
	
	/*
	 * I added a get/set for Tile so I can get the rest of my code to work.
	 * I don't know if I like the Entity knowing its Tile, but for now it's OK...
	 * Hopefully we can think of something better though. -- Miorel
	 * 
	 */
	
	private Tile tile = null;
	
	public Tile getTile() {
		return tile;
	}
	
	void setTile(Tile tile) {
		this.tile = tile;
	}
	
	public Direction getFacingDirection() {
		return facing;
	}
	
	void setFacingDirection(Direction d) {
		facing = d;
	}
}

