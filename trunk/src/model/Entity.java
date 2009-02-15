package model;

import java.util.Iterator;

import model.items.*;
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

	private Tile tile = null;
	
	private int speed;

	public static final int ENT_LEFT_H = 10;
	public static final int ENT_RIGHT_H = 11;
	public static final int ENT_FEET 	= 12;
	public static final int ENT_HEAD 	= 13;
	public static final int ENT_ARMOR = 14;

	private Direction facing = Direction.EAST;
	
//    Location
//    Name
//    EquippedItems
	
	public Entity(Occupation occupation) {
		this.occupation = occupation;
		this.inventory = new Inventory();
		this.stats = new Stats();
		this.speed = stats.getMovement();
	}
	
	public void draw(Drawer d) {
		d.drawEntity(this);
	}
	
	public Tile getTile() {
		return tile;
	}
	
	void setTile(Tile tile) {
		this.tile = tile;
		//Check if there is an item on this tile. If so, add it to inventory, if not full.
		//We could eventually prompt to ask if user wants to add to inventory
		Item item = tile.getItem();
		speed = stats.getMovement();
		if(item != null) {
			int ret = inventory.addItem(item);
			if(ret == Inventory.INV_FULL)   
				Console.getInstance().writeLine("Inventory Full"); 
			else {
				String message = item + " has been added to your Inventory.";
				Console.getInstance().writeLine(message);
				tile.setItem(null);
			}
		}
			
	}
	
	public Direction getFacingDirection() {
		return facing;
	}
	
	void setFacingDirection(Direction d) {
		facing = d;
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
	
	public void dropItem() { 
		if(!inventory.isEmpty()) {
			Item i = inventory.removeItemAt(0);
			i.setLocation(getTile().getLocation());
			getTile().setItem(i);
			Console.getInstance().writeLine(i + " has been dropped");
		}
	}
	public Stats getStats() {
		return this.stats;
	}
	
	public void update() {
		if(speed > 0)
			--speed;
	}
	
	public boolean canMove() {				
		return speed <= 0;
	}
	
	public Iterator<Item> getInventoryItems() {
		return inventory.getItems();
	}
	
	public Entity clone() throws CloneNotSupportedException {
        Entity e = (Entity) super.clone();
        e.stats = this.stats.clone();
        e.inventory = this.inventory.clone();
        return e;
	}
}
