package rpgeeze.model.entity;

import rpgeeze.log.LogManager;
import rpgeeze.model.Location;
import rpgeeze.model.Tile;
import rpgeeze.model.Visitable;
import rpgeeze.model.Visitor;
import rpgeeze.util.Direction;
import java.util.Hashtable;
import rpgeeze.util.Subject;

public abstract class Entity extends Subject implements Cloneable, Visitable {
	
	private static Hashtable<String, Entity> prototypes = new Hashtable<String, Entity>();
	static {
		for(Entity d: new Entity[] {
			new PC()
		})
		prototypes.put(d.toString(), d);
	}
	
	private int speed;	
	private Location l;
	private Direction facing;
	private Tile tile;
	private String entityType;
	
	public void move(Location l)
	{
		Tile from = this.getTile();
		if(from.hasAE()){
			from.getAE().setMessageSentFlag(false);
		}
		
		int newX = from.getLocation().getX() + l.getX();
		int newY = from.getLocation().getY() + l.getY();
		
		//Tile to = map.getTile(newX, newY);
		
		Tile to = from.getAbsoluteTile(newX, newY);
		
		// watch out for race conditions here
		to.setEntity(this);
		this.setFacingDirection(l.closestDirection());		
	}
	
	/*
	public void move(int xOffset, int yOffset) {
		Tile destination = map.getTile(tile.getX() + xOffset, tile.getY() + yOffset);
		try {
			destination.setEntity(this);
			Tile oldTile = tile;
			tile = destination;
			oldTile.setEntity(null);
		}
		catch(IllegalMoveException e) {
			LogManager.getInstance().log("Entity move request refused: " + e.getMessage(), "MODEL");
		}
	}
	*/
	
	public void setFacingDirection(Direction dir)
	{
		this.facing = dir;
	}
	
	public Direction getFacingDirection() {
		return facing;
	}

	public Tile getTile() {
		return tile;
	}
	
	public void setTile(Tile newTile) {
		tile = newTile;
	}
	
	public void accept(Visitor visitor) {
		visitor.visitEntity(this);
	}
	
	public Entity clone()
	{
		Entity e = null;
		try {
			e = (Entity)super.clone();
		}catch(CloneNotSupportedException ce) {}		
		return e;		
	}
	public void setEntityType(String type){
		this.entityType = type;
	}
	public String getEntityType(){
		return entityType;
	}
	public abstract boolean isAlive();
	public abstract void update();

	public void move(Direction direction) {
		// TODO Auto-generated method stub
		
	}
}

/*
public class Entity implements Subject, Drawable, Cloneable {
	private Map map;
	private Stats stats;
	private SkillContainer skills;
	private Inventory inventory;
	private Tile tile = null;
	private Equipment equipment;
	private int speed;
	protected final List<Observer> observers = new LinkedList<Observer>();

	public void move(Location l) {
		this.updateObservers();
		Tile from = this.getTile();
		if(from.hasAE()){
			updateObservers();
			from.getAE().setMessageSentFlag(false);
		}
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
	
	public void updateObservers() {
		for(Observer obs: this.observers)
			obs.update(this);
	}
	
	public void register(Observer o) {
		this.observers.add(o);
	}

	public void unregister(Observer o) {
		this.observers.remove(o);
	}
	
	public SkillContainer getSkills() {
		return skills;
	}
	
	public Skill getSkillAt(int index) {
		return skills.get(index);
	}
}
*/