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
import rpgeeze.model.entity.StatsModifiable;

public abstract class Entity extends Subject implements Cloneable, Visitable, StatsModifiable {

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
	private Tile startingTile;
	public Tile getStartingTile() {
		return startingTile;
	}
	public void setStartingTile(Tile startingTile) {
		this.startingTile = startingTile;
	}
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
			Tile oldTile = tile;
			destination.setEntity(this);
			//tile = destination;
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

	public abstract void accept(Visitor visitor);

	public Entity clone()
	{
		Entity e = null;
		try {
			e = (Entity)super.clone();
			e.equipment = (this.equipment != null ) ? this.equipment.clone(): null;
			e.inventory = (this.inventory != null ) ? this.inventory.clone(): null;
			e.stats = (this.stats != null) ? this.stats.clone(): null;
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

	public boolean addItem(TakeableItem item){
		return inventory.addItem(item, true);
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
		//boolean ret = false;
		return inventory.addItem(item, true);
		//	
		//	ret = true;
		//}
		//return ret;
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

	public boolean hasEnoughHP(int value){
		return value <= getStats().life;
	}

	public boolean hasEnoughMP(int value)
	{
		return value <= getStats().mana;
	}	

	public int maxHealth()
	{
		return getStats().MAX_LIFE;
	}

	public int maxMana()
	{
		return getStats().MAX_MP;
	}

	public int getLevel()
	{
		return getStats().getLevel();
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	public void setStats(Stats stats) {
		this.stats = stats;
	}
}