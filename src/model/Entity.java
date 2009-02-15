package model;

import model.item.Item;
import view.Console;
import view.Drawable;
import view.Drawer;
import util.Iterator;

public class Entity implements Drawable, Cloneable {
	private Stats stats;
	private Occupation occupation;
	private Inventory inventory;
	private Tile tile = null;
		
	private Item head;
	private Item armor;
	private Item boots;
	private Item weapon;
	private Item auxilary;	

	private int speed;

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

	public void equipItem(Item  i) {
		//Item i = this.inventory.removeItemAt(index);
		//inventory.removeItemAt
//		if(this.rightHandItem == null)
//			this.rightHandItem = i;
//		else if(this.leftHandItem == null)
//			this.leftHandItem = i;
	}

	public void equipHead(Item i)
	{
		inventory.removeItem(i);		
		if(head != null)
			inventory.addItem(i);
		head = i;
	}
	public void equipBoots(Item i)
	{
		inventory.removeItem(i);		
		if(boots != null)
			inventory.addItem(i);
		boots = i;
	}
	public void equipArmor(Item i)
	{
		inventory.removeItem(i);		
		if(armor != null)
			inventory.addItem(i);
		armor = i;
	}
	public void equipWeapon(Item i)
	{
		inventory.removeItem(i);		
		if(weapon != null)
			inventory.addItem(i);
		weapon = i;
	}
	public void equipAuxilary(Item i)
	{
		inventory.removeItem(i);		
		if(auxilary != null)
			inventory.addItem(i);
		auxilary = i;
	}
	
	
//	public void unequipItem(int where) {
//		// Item i = inventory.removeItemAt(index);
//		switch(where) {
//		case ENT_LEFT_H: // inventory.dropItem(leftHandItem);
//		case ENT_RIGHT_H: // inventory.dropItem(rightHandItem);
//		case ENT_HEAD: // inventory.dropItem(feetItem);
//		case ENT_FEET: // inventory.dropItem(headItem);
//		case ENT_ARMOR: // inventory.dropItem(armorItem);
//		}
//	}

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

	public void actionAtIndex(java.awt.Point p)
	{
		//from action listener in viewer
		int x = p.x;
		int y = p.y;
		
		int index = ((x + 1) * (y + 1)) - 1;
		if(index >= inventory.count())
			return;
		
		Iterator<Item> iter = inventory.iterator();
		for( int i = 0; i < index; i++)
			iter.advance();
		iter.current().activate(this);
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
