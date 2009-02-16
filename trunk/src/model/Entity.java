package model;

import java.awt.Color;

import model.item.*;
import view.Console;
import view.Drawable;
import view.Drawer;
import util.Iterator;

public class Entity implements Drawable, Cloneable {
	private Map map;
	private Stats stats;
	private Occupation occupation;
	private Inventory inventory;
	private Tile tile = null;
			
	private Equipment equipment;

	private int speed;

	private Direction facing = Direction.EAST;

	// Location
	// Name
	// EquippedItems
	
	public class Equipment implements Cloneable {
		public Item head;
		public Item armor;
		public Item boots;
		public Item weapon;
		public Item auxiliary;
		
		public Equipment() 	{
			head = null;
			armor = null;
			boots = null;
			weapon = null;
			auxiliary = null;
		}
		
		public synchronized Equipment clone() {
			Equipment ret = new Equipment();
			ret.head = head == null ? null : head.clone();
			ret.armor = armor == null ? null : armor.clone();
			ret.boots = boots == null ? null : boots.clone();
			ret.weapon = weapon == null ? null : weapon.clone();
			ret.auxiliary = auxiliary == null ? null : auxiliary.clone();
			return ret;
		}
	}
	
	public Entity(Occupation occupation, Map map) {
		this.map = map;
		this.inventory = new Inventory();
		this.stats = (Stats) occupation.stats.clone();
		this.occupation = occupation;
		this.equipment = new Equipment();
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
			//One Shot Item Code
			if(item instanceof OneShotItem){
				item.activate(this);
				tile.setItem(null);
			}
			else if (item instanceof InteractiveItem) {
				item.activate(this);
				tile.setItem(null);
			}
			else{
				int ret = this.inventory.addItem(item);
				if(ret == Inventory.INV_FULL)
					Console.getInstance().writeLine("Inventory Full");
				else {
					String message = item + " has been added to your Inventory.";
					Console.getInstance().writeLine(message,Color.YELLOW);
					tile.setItem(null);
				}
			}
		}
	}

	public void moveAvatar(Location l) {
		Tile from = this.getTile();
		if (from.hasAE())
			from.getAE().setMessageFlag(false);
		int newX = from.getLocation().getX() + l.getX();
		int newY = from.getLocation().getY() + l.getY();
		Tile to = map.getTile(newX, newY);

		// watch out for race conditions here
		to.accept(this);
		this.setFacingDirection(l.closestDirection());
	}
	
	public Direction getFacingDirection() {
		return this.facing;
	}

	void setFacingDirection(Direction d) {
		this.facing = d;
	}

	public void equipHead(Item i) {
		if(equipment.head == i)
			return;
		inventory.removeItem(i);
		if(equipment.head != null)
			inventory.addItem(equipment.head);
		equipment.head = i;
		Console.getInstance().writeLine("Equipped " + i);
	}

	public void equipBoots(Item i) {
		if(equipment.boots == i)
			return;
		inventory.removeItem(i);
		if(equipment.boots != null)
			inventory.addItem(equipment.boots);
		equipment.boots = i;
		Console.getInstance().writeLine("Equipped " + i);
	}
	
	public void equipArmor(Item i) {
		if(equipment.armor == i)
			return;
		inventory.removeItem(i);
		if(equipment.armor != null)
			inventory.addItem(equipment.armor);
		equipment.armor = i;
		Console.getInstance().writeLine("Equipped " + i);
	}

	public void equipWeapon(Item i) {
		if(equipment.weapon == i)
			return;
		inventory.removeItem(i);
		if(equipment.weapon != null )
			inventory.addItem(equipment.weapon);
		equipment.weapon = i;
		Console.getInstance().writeLine("Equipped " + i);
	}

	public void equipAuxiliary(Item i) {		
		if(equipment.auxiliary == i)
			return;
		inventory.removeItem(i);
		if(equipment.auxiliary != null)
			inventory.addItem(equipment.auxiliary);
		equipment.auxiliary = i;
		if(equipment.weapon != null)
			equipment.weapon.activate(this);
		Console.getInstance().writeLine("Equipped " + i);
	}

	public void unequipHead() {
		if(equipment.head != null)
			inventory.addItem(equipment.head);
		equipment.head = null;
	}

	public void unequipBoots() {
		if(equipment.boots != null)
			inventory.addItem(equipment.boots);
		equipment.boots = null;
	}

	public void unequipArmor() {
		if(equipment.armor != null)
			inventory.addItem(equipment.armor);
		equipment.armor = null;
	}
	public void unequipWeapon()
	{
		if(equipment.weapon != null)
			inventory.addItem(equipment.weapon);
		equipment.weapon = null;
	}
	public void unequipAuxiliary() 	{
		if(equipment.auxiliary != null)
			inventory.addItem(equipment.auxiliary);
		equipment.auxiliary = null;
	}
	
	public Equipment getEquipment() {
		return equipment;
	}
	
	public Inventory getInventory() {
		// shallow copy of inventory held in clone.
		// a deep copy will be more costly
		return this.inventory;
	}

	public void dropItem(int index) {
		if(!getTile().hasItem()) {
			Item i = this.inventory.removeItemAt(index);
			getTile().setItem(i);
			Console.getInstance().writeLine("Dropped " + i);
		}
	}

	public void equipActionAtIndex(int index) {
		//from action listener in viewer
		if(index >= inventory.count())
			return;
		inventory.getItemAt(index).activate(this);	
	}

	public void dropActionAtIndex(int index) {
		//from action listener in viewer
		if(index >= inventory.count())
			return;

		//Drop item here
		dropItem(index);
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

	public Entity clone() {
		Entity ret = null;
		try {
			ret = (Entity) super.clone();
		}
		catch(CloneNotSupportedException e) {
		}
		ret.stats = this.stats.clone();
		ret.inventory = this.inventory.clone();
		return ret;
	}
	
	public Stats resetStats(int numOfLives) {
		stats = (Stats) occupation.stats.clone();
		stats.getPrimary().setLivesLeft(numOfLives);
		return stats;		
	}
	
	public Occupation getOccupation() {
		return this.occupation;
	}
	
	public String toXml() {
		return toXml("");
	}
	
	public String toXml(String indent) {
		StringBuilder sb = new StringBuilder();
		sb.append(indent + "<entity>\n");
		sb.append(stats.toXml(indent + "\t") + "\n");
		sb.append(occupation.toXml(indent + "\t") + "\n");
		sb.append(inventory.toXml(indent + "\t") + "\n");
		sb.append(tile.toXml(indent + "\t") + "\n");
		sb.append(indent + "</entity>");
		return sb.toString();
	}

}
