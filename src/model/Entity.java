package model;

import model.items.Item;
import view.Console;
import view.Drawable;
import view.Drawer;
import util.Iterator;

public class Entity implements Drawable, Cloneable {
	private Stats stats;
	private Occupation occupation;
	private Inventory inventory;
	private Item leftHandItem;
	private Item rightHandItem;
	private Tile tile = null;

	private int speed;

	public static final int ENT_LEFT_H = 10;
	public static final int ENT_RIGHT_H = 11;
	public static final int ENT_FEET = 12;
	public static final int ENT_HEAD = 13;
	public static final int ENT_ARMOR = 14;

	private Direction facing = Direction.EAST;

	// Location
	// Name
	// EquippedItems
	
	public Entity(Occupation occupation) {
		this.inventory = new Inventory();
		this.stats = (Stats) occupation.stats.clone();
		this.occupation = occupation;
	}

	public void draw(Drawer d) {
		d.drawEntity(this);
	}

	public Tile getTile() {
		return this.tile;
	}

	void setTile(Tile tile) {
		this.tile = tile;
		// Check if there is an item on this tile. If so, add it to inventory,
		// if not full.
		// We could eventually prompt to ask if user wants to add to inventory
		Item item = tile.getItem();
		this.speed = this.stats.getMovement();
		if(item != null) {
			int ret = this.inventory.addItem(item);
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
		return this.facing;
	}

	void setFacingDirection(Direction d) {
		this.facing = d;
	}

	public void equipItem(int index) {
		Item i = this.inventory.removeItemAt(index);
		if(this.rightHandItem == null)
			this.rightHandItem = i;
		else if(this.leftHandItem == null)
			this.leftHandItem = i;
	}

	public void unequipItem(int where) {
		// Item i = inventory.removeItemAt(index);
		switch(where) {
		case ENT_LEFT_H: // inventory.dropItem(leftHandItem);
		case ENT_RIGHT_H: // inventory.dropItem(rightHandItem);
		case ENT_HEAD: // inventory.dropItem(feetItem);
		case ENT_FEET: // inventory.dropItem(headItem);
		case ENT_ARMOR: // inventory.dropItem(armorItem);
		}
	}

	public Inventory getInventory() {
		// shallow copy of inventory held in clone.
		// a deep copy will be more costly
		return this.inventory;
	}

	public void dropItem() {
		if(!this.inventory.isEmpty()) {
			Item i = this.inventory.removeItemAt(0);
			i.setLocation(getTile().getLocation());
			getTile().setItem(i);
			Console.getInstance().writeLine(i + " has been dropped");
		}
	}

	public Stats getStats() {
		return this.stats;
	}

	public void update() {
		if(this.speed > 0)
			--this.speed;
	}
	
	public boolean isAlive() {
		if (getStats().life < 1)
			return false;
		return true;
	}
	
	public void decALife(){
		stats.getPrimary().decLivesLeft();
	}

	public boolean canMove() {
		return this.speed <= 0;
	}

	public Iterator<Item> getInventoryItemIterator() {
		return this.inventory.iterator();
	}

	public Entity clone() throws CloneNotSupportedException {
		Entity e = (Entity) super.clone();
		e.stats = this.stats.clone();
		e.inventory = this.inventory.clone();
		return e;
	}
	
	public Stats resetStats(int numOfLives) {
		stats = (Stats) occupation.stats.clone();
		stats.getPrimary().setLivesLeft(numOfLives);
		return stats;
			
	}

}
