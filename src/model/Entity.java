package model;

import view.*;

public class Entity implements Drawable, Cloneable {
	private Stats stats;
	private Inventory inventory;
	private Occupation occupation;
	private Item leftHandItem;
	private Item rightHandItem;
	private Item headItem;
	private Item armorItem;
	private Item feetItem;
	
	public static final int ENT_LEFT_H 	= 10;
	public static final int ENT_RIGHT_H	= 11;
	public static final int ENT_FEET 	= 12;
	public static final int ENT_HEAD 	= 13;
	public static final int ENT_ARMOR 	= 14;
	
	private Direction facing = Direction.EAST;
	
//    Location
//    Name
//    EquippedItems
	
	public Entity(Occupation occupation) {
		this.occupation = occupation;
		inventory = new Inventory();
		this.stats = new Stats();
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
		//Check if there is an item on this tile. If so, add it to inventory, if not full.
		//We could eventually prompt to ask if user wants to add to inventory
		Item temp = tile.getItem();
		if (temp != null) {
			switch (inventory.addItem(temp)) {
			case Inventory.INV_FULL : System.out.println("Inventory Full"); break;
			case Inventory.INV_SUCCESS : 
				System.out.println("Added Successfully. " + temp.name);
				tile.setItem(null);
				break;
			
			}
		}
			
	}
	
	public Direction getFacingDirection() {
		return facing;
	}
	
	void setFacingDirection(Direction d) {
		facing = d;
	}

	
	public Object clone() throws CloneNotSupportedException 
	{
        Entity e = (Entity)super.clone();
        if(this.stats != null) e.stats = (Stats)this.stats.clone();
        if(this.inventory != null)e.inventory = (Inventory)this.inventory.clone();
        return e;
	}

	
	public void equipItem(int index) {
		Item i = inventory.removeItemAt(index);
		if (rightHandItem == null)
			rightHandItem = i;
		else if (leftHandItem == null)
			leftHandItem = i;
	}
	
	public void unequipItem(int where) {
		//Item i = inventory.removeItemAt(index);
		switch (where) {
		case ENT_LEFT_H 	: //inventory.dropItem(leftHandItem);
		case ENT_RIGHT_H 	: //inventory.dropItem(rightHandItem);
		case ENT_HEAD 		: //inventory.dropItem(feetItem);
		case ENT_FEET 		: //inventory.dropItem(headItem);
		case ENT_ARMOR 		: //inventory.dropItem(armorItem);
		}
	}
	public Stats getStats(){
		return this.stats;
	}
}

