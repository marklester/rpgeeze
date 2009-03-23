package rpgeeze.model.entity;

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.Location;
import rpgeeze.model.Tile;
import rpgeeze.model.Visitable;
import rpgeeze.model.Visitor;
import rpgeeze.model.item.EquippableItem;
import rpgeeze.model.item.Item;
import rpgeeze.model.item.TakeableItem;
import rpgeeze.util.Direction;
import java.util.Hashtable;
import rpgeeze.util.Subject;

public abstract class Entity extends Subject implements Cloneable, Visitable {
	
	private static Hashtable<String, Entity> prototypes = new Hashtable<String, Entity>();
	static {
		for(Entity d: new Entity[] {
			new PC()
		})
		prototypes.put(d.getEntityType(), d);
	}
	protected Stats stats;
	

	private int speed;	
	private Location l;
	private Direction facing = Direction.EAST;
	private Tile tile;
	private String entityType;
	protected Equipment equipment;
	protected Inventory inventory;
	private Occupation occupation;
	
	protected Entity(){}
	protected Entity(Occupation occ){
		this.occupation = occ;
		this.inventory = new Inventory();
		this.equipment = new Equipment();
		this.stats = occupation.getStats().clone();
	}

	public void move(Location l) {
		move(l.getX(),l.getY());
	}
	
	
	public void move(int xOffset, int yOffset) {
		Tile destination = getTile().getRelativeTile(xOffset, yOffset);//tile.getX() + xOffset, tile.getY() + yOffset);
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
	
	public void setFacingDirection(Direction dir){
		this.facing = dir;
	}
	
	public Occupation getOccupation() {
		return occupation;
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
	public Stats getStats(){
		return stats;
	}
	
	public final void accept(Visitor visitor) {
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
	public Equipment getEquipment(){
		return equipment;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public void addItem(TakeableItem item){
		inventory.addItem(item, true);
	}
	
	public void addItemSilently(TakeableItem item){
		inventory.addItem(item, false);
	}
	
	private void dropItem(int index) {
		if(!getTile().hasItem()) {
			Item i = this.inventory.removeItemAt(index);
			getTile().setItem(i);
			LogManager.getInstance().log("Dropped " + i, "MODEL", Message.Type.GAME);
		}
	}

	public void equipActionAtIndex(int index) {
		//from action listener in viewer
		if(index >= inventory.itemCount())
			return;
		inventory.getItemAt(index).activate(this, this.getTile());	
	}
	
	public void dropActionAtIndex(int index) {
		//from action listener in viewer
		if(index >= inventory.itemCount())
			return;	
		//Drop item here
		dropItem(index);
	}
	
	public void removeItem(Item item){
		inventory.removeItem(item);
	}
	
	public abstract boolean isAlive();
	public abstract void update();

	public void move(Direction direction) {
		setFacingDirection(direction);
		move(direction.getX(), direction.getY());
	}
	
	public static Entity getEntityPrototype(String key){
		return (Entity)prototypes.get(key).clone();
	}
	
	public boolean pickUp(TakeableItem item) {
		return inventory.addItem(item, true);
	}
	
	public void dropItemAt(int i) {
		Item item = inventory.getItemAt(i);
		if(item != null && tile.setItem(item))
			inventory.removeItemAt(i);
	}
	
	public void addHealth(int value)
	{
		getStats().addHealth(value);
	}

	public void addMana(int value)

	{
		getStats().addMana(value);
	}
	
	public void addMovement(int value)
 	{
		getStats().addMovement(value);
	}
	
	public void addLevel(int value)
	{
		getStats().addLevel(value);
	}
	
	public void equipHead(EquippableItem i) {
		if(equipment.getHead() == i){	
		}else{
			inventory.removeItem((TakeableItem) i);
			unequipHead();
			equipment.setHead(i);
		}
	}

	public void equipBoots(EquippableItem i) {
		if(equipment.getBoots() == i){	
		}else{
			inventory.removeItem((TakeableItem) i);
			unequipBoots();
			equipment.setBoots(i);
			i.equip(this);
		}
	}
	
	public void equipArmor(EquippableItem i) {
		if(equipment.getArmor() == i){	
		}else{
			inventory.removeItem((TakeableItem) i);
			unequipArmor();
			equipment.setArmor(i);
			i.equip(this);
		}
	}
	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	public void setStats(Stats stats) {
		this.stats = stats;
	}
	public void equipWeapon(EquippableItem i) {
		if(equipment.getWeapon() == i){	
		}else{
			inventory.removeItem((TakeableItem) i);
			unequipWeapon();
			equipment.setWeapon(i);
			i.equip(this);
		}
	}
//logic for switching here
	public void equipAuxiliary(EquippableItem i) {
		if(equipment.getAuxiliary() == i)
			return;
		
		if(equipment.getAuxiliary() != null){
			inventory.addItem((TakeableItem) equipment.getAuxiliary(),false);
		    equipment.setAuxiliary(i);
		    inventory.removeItem((TakeableItem) i);
		}
		if(equipment.getWeapon() == null){
			equipWeapon(i);
			inventory.removeItem((TakeableItem) i);
		}
	}

        public void unequipHead() {
                if(equipment.getHead() != null){
                        inventory.addItem((TakeableItem) equipment.getHead(),false);
                        //equipment.getHead().unequip(this);
                }
                equipment.setHead(null);
        }

	public void unequipBoots() {
		if(equipment.getBoots() != null){
			inventory.addItem((TakeableItem) equipment.getBoots(),false);
			equipment.getBoots().unequip(this);
		}
		equipment.setBoots(null);
	}


	public void unequipArmor() {
		if(equipment.getArmor() != null){
			inventory.addItem((TakeableItem) equipment.getArmor(),false);
			equipment.getArmor().unequip(this);
		}
		equipment.setArmor(null);
	}
	
	public void unequipWeapon() {
		if(equipment.getWeapon() != null){
			inventory.addItem((TakeableItem) equipment.getWeapon(),false);
			equipment.getWeapon().unequip(this);
		}
		equipment.setWeapon(null);
	}
	
	public void unequipAuxiliary() {
		if(equipment.getAuxiliary() != null){
			inventory.addItem((TakeableItem) equipment.getAuxiliary(),false);
			equipment.getAuxiliary().unequip(this);
		}
		equipment.setAuxiliary(null);
	}
	
	public void unequipAll(){
		unequipHead();
		unequipBoots();
		unequipArmor();
		unequipWeapon();
		unequipAuxiliary();
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