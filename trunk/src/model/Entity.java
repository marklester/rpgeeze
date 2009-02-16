package model;

import java.awt.Color;

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
			
	private Equipment equipment;

	private int speed;

	private Direction facing = Direction.EAST;

	// Location
	// Name
	// EquippedItems
	
	public class Equipment implements Cloneable{
		public Item head;
		public Item armor;
		public Item boots;
		public Item weapon;
		public Item auxilary;
		
		public Equipment()
		{
			head = null;
			armor = null;
			boots = null;
			weapon = null;
			auxilary = null;
		}
		
		public Equipment clone() {
			Equipment ret = null;
			try {
				ret = (Equipment) super.clone();
			}
			catch(CloneNotSupportedException e) {
			}
			return ret;
		}
	}
	
	public Entity(Occupation occupation) {
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

	public Direction getFacingDirection() {
		return this.facing;
	}

	void setFacingDirection(Direction d) {
		this.facing = d;
	}

	public void equipHead(Item i)
	{
		if(equipment.head != null && equipment.head != i)
			inventory.addItem(equipment.head);
		equipment.head = i;
		Console.getInstance().writeLine("Equiped  " + i.toString());
	}
	public void equipBoots(Item i)
	{
		if(equipment.boots != null && equipment.boots != i)
			inventory.addItem(equipment.boots);
		equipment.boots = i;
		Console.getInstance().writeLine("Equiped  " + i.toString());
	}
	public void equipArmor(Item i)
	{	
		if(equipment.armor != null && equipment.armor != i)
			inventory.addItem(equipment.armor);
		equipment.armor = i;
		Console.getInstance().writeLine("Equiped  " + i.toString());
	}
	public void equipWeapon(Item i)
	{
		if(equipment.weapon != null && equipment.weapon != i)
			inventory.addItem(equipment.weapon);
		equipment.weapon = i;
		Console.getInstance().writeLine("Equiped  " + i.toString());
	}
	public void equipAuxilary(Item i)
	{		
		if(equipment.auxilary != null && equipment.auxilary != i)
			inventory.addItem(equipment.auxilary);
		equipment.auxilary = i;
		if(equipment.weapon != null)
			equipment.weapon.activate(this);
		Console.getInstance().writeLine("Equiped  " + i.toString());
	}

	public void unequipHead()
	{
		if(equipment.head != null)
			inventory.addItem(equipment.head);
		equipment.head = null;
	}
	public void unequipBoots()
	{
		if(equipment.boots != null)
			inventory.addItem(equipment.boots);
		equipment.boots = null;
	}
	public void unequipArmor()
	{
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
	public void unequipAuxilary()
	{
		if(equipment.auxilary != null)
			inventory.addItem(equipment.auxilary);
		equipment.auxilary = null;
	}
	
	public Equipment getEquipment()
	{
		return equipment;
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
			Console.getInstance().writeLine(i + " has been dropped ");
		}
	}

	public void actionAtIndex(java.awt.Point p)
	{
		//from action listener in viewer
		int x = p.x;
		int y = p.y;
		
		int index = x + y * 6;
		if(index >= inventory.count())
			return;
				
		inventory.removeItemAt(index).activate(this);
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
