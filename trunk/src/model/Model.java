package model;

import java.util.*;

import util.Observer;

public class Model implements util.Subject{
	protected final Queue<Command> commands = new LinkedList<Command>();
	protected final List<Observer> observers = new LinkedList<Observer>();
	
	private Map.Matrix snapshot;
	
	private Entity avatar;
	private Map map;
	private boolean stats_up=false;
	private boolean inventory_up=false;
	public Model(Map map, Entity avatar) {		
		/* Made constructor public so that RunGame would compile. If you
		 * come up with another way of retrieving a Model, update
		 * RunGame accordingly.
		 * -- Miorel
		 */
		
		//create map
			//create tiles and encompassing stuff
		//create entity
		//create task queue
		//		--Jose
		
		this.map = map;
		this.avatar = avatar;
		// The following code should probably be moved elsewhere. -- Miorel
		ClassLoader loader = getClass().getClassLoader();
		Scanner scanner = new Scanner(loader.getResourceAsStream("res/entities.txt"));
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		Tile tile = map.getTile(x, y);
		tile.setEntity(avatar);
		avatar.setTile(tile);
	}
	
	public Map getMap() {
		return map;
	}
	
	public synchronized void invoke(Command cmd) {
		commands.add(cmd);
	}	

	public void update() {
		//read task queue
		
		synchronized(this)
		{
			while(!commands.isEmpty())
			{
				System.out.println("commands " + commands.size());
				Command c = commands.remove();
				while(commands.contains(c))
				{
					commands.remove(c);
				}
				c.execute();
			}
		}
		
		snapshot = map.getMatrix();
		updateObservers();
		
		
		
		//update map
			//update items
			//apply  AoE's
			//update NPC's
		//update entity
		//		--Jose
	}
	
	public void register(Observer o)
	{
		observers.add(o);
	}
	public void unregister(Observer o)
	{
		observers.remove(o);
	}
	public void updateObservers()
	{
		Iterator<Observer> iter = observers.iterator();
		while(iter.hasNext())
			iter.next().update(this);			
	}
	public Map.Matrix publishState()
	{	
		return snapshot;
	}
	
	public Entity getAvatar() {
		return avatar;
	}
	
	public void moveAvatar(Direction d) {
		Tile from = avatar.getTile();
		int newX = from.getLocation().getX() + d.getX();
		int newY = from.getLocation().getY() + d.getY();
		Tile to = map.getTile(newX, newY);
		
		// watch out for race conditions here
		to.accept(avatar);
		avatar.setFacingDirection(d);
	}	
	public void equipItem(int index) {
		avatar.equipItem(index);
	}
	
	public void uneqipItem(int where) {
		avatar.unequipItem(where);
	}
	
	public void dropItem()
	{
		avatar.dropItem();
	}
	
	public void setStatsVisible(boolean visible){
		this.stats_up = visible;
	}
	public boolean isStatsUp(){
		if(stats_up){
			return true;
		}
		return false;
	}
	public void setInventoryVisible(boolean visible){
		this.inventory_up = visible;
	}
	public boolean isInventoryUp(){
		if(inventory_up){
			return true;
		}
		return false;
	}
}