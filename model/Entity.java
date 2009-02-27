package model;

import java.awt.Color;

import model.item.Item;
import model.item.OneShotItem;
import model.item.InteractiveItem;
import view.Console;
import view.Drawable;
import view.Drawer;
import util.Iterator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Entity implements Drawable, Cloneable {
	private static final Pattern pattern = Pattern.compile("<entity>(<stats>.*</stats>)(<occupation>.*</occupation>)(<inventory>.*</inventory>)(<equipment>.*</equipment>)(<tile>.*</tile>)<facing>(.*)</facing></entity>");
	
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
	
	private Entity() {
	}
	
	public Entity(Occupation occupation, Map map) {
		this.map = map;
		this.inventory = new Inventory();
		this.occupation = occupation;
		this.stats = (Stats) occupation.stats.clone();
		this.equipment = new Equipment();
	}

	public void draw(Drawer d) {
		d.drawEntity(this);
	}

	public Tile getTile() {
		return this.tile;
	}

	void setTile(Tile tile) {
		// UUUUUUUUUUUUUUUGGGGGGGGGGGGGLLLLLLLLYYYYYYYYYY
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
			else if(item instanceof InteractiveItem) {
				item.activate(this);
			}
			else {
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

	public void move(Location l) {
		Tile from = this.getTile();
		if(from.hasAE())
			from.getAE().setMessageSentFlag(false);
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
		unequipHead();
		equipment.head = i;
	}

	public void equipBoots(Item i) {
		if(equipment.boots == i)
			return;
		inventory.removeItem(i);
		unequipBoots();
		equipment.boots = i;
	}
	
	public void equipArmor(Item i) {
		if(equipment.armor == i)
			return;
		inventory.removeItem(i);
		unequipArmor();
		equipment.armor = i;
	}

	public void equipWeapon(Item i) {
		if(equipment.weapon == i)
			return;
		inventory.removeItem(i);
		unequipWeapon();
		equipment.weapon = i;
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
	}

	public void unequipHead() {
		if(equipment.head != null)
		{
			inventory.addItem(equipment.head);
			equipment.head.deActivate(this);
		}
		equipment.head = null;
	}

	public void unequipBoots() {
		if(equipment.boots != null)
		{
			inventory.addItem(equipment.boots);
			equipment.boots.deActivate(this);
		}
			
		equipment.boots = null;
	}

	public void unequipArmor() {
		if(equipment.armor != null)
		{
			inventory.addItem(equipment.armor);
			equipment.armor.deActivate(this);
		}
		equipment.armor = null;
	}
	
	public void unequipWeapon() {
		if(equipment.weapon != null)
		{
			inventory.addItem(equipment.weapon);
			equipment.weapon.deActivate(this);
		}
		equipment.weapon = null;
	}
	
	public void unequipAuxiliary() {
		if(equipment.auxiliary != null)
		{
			inventory.addItem(equipment.auxiliary);
			equipment.auxiliary.deActivate(this);
		}
		equipment.auxiliary = null;
	}
	
	public void unequipAll()
	{
		unequipHead();
		unequipBoots();
		unequipArmor();
		unequipWeapon();
		unequipAuxiliary();
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
		sb.append(equipment.toXml(indent + "\t") + "\n");
		sb.append(tile.toXml(indent + "\t") + "\n");
		sb.append(indent + "\t<facing>\n");
		sb.append(facing.toLocation().toXml(indent + "\t\t") + "\n");
		sb.append(indent + "\t</facing>\n");
		sb.append(indent + "</entity>");
		return sb.toString();
	}
	
	public static Entity fromXml(String xml) {
		Matcher mat = pattern.matcher(xml);
		if(!mat.matches())
			throw new RuntimeException("Bad XML for Entity");
		Entity ret = new Entity();
		ret.stats = Stats.fromXml(mat.group(1));
		ret.occupation = Occupation.fromXml(mat.group(2));
		ret.inventory = Inventory.fromXml(mat.group(3));
		ret.equipment = Equipment.fromXml(mat.group(4));
		ret.tile = Tile.fromXml(mat.group(5));
		ret.facing = Location.fromXml(mat.group(6)).closestDirection();
		return ret;
	}

	public static Entity fromXml(Occupation occ, Map map, String xml) {
		Entity ret = fromXml(xml);
		if(occ != null) 
		{
		 ret.occupation = occ;
		 ret.stats = (Stats) ret.occupation.stats.clone();
		}
		ret.map = map;
		return ret;
	}
}
