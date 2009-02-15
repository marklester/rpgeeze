package model;

import java.util.*;
import java.awt.Point;

import util.Observer;

public class Model implements util.Subject{
	protected final LinkedList<Command> commands = new LinkedList<Command>();
	protected final List<Observer> observers = new LinkedList<Observer>();
	
	private Map.Matrix snapshot;
	
	private Entity avatar;
	private Map map;
	
	
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
		LinkedList<Command> tempQ = null;
		synchronized(this)
		{			
			tempQ = (LinkedList<Command>)commands.clone();
			commands.clear();
		}
		while(!tempQ.isEmpty())
		{
			tempQ.remove().execute(this);
		}
		
		avatar.update();
		
		snapshot = map.getMatrix();
		updateObservers();
		
		
		
		//update map
			//update items
			//apply  AoE's
			//update NPC's
		//update entity
		//		--Jose
	}
	
	
	//Observer stuff
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
	
	//MoveCommand
	public Entity getAvatar() {
		return avatar;
	}	
	public void moveAvatarRequest(Direction d) {
		if (avatar.canMove())
			moveAvatar(d);
	}	
	private void moveAvatar(Direction d)
	{
		Tile from = avatar.getTile();
		int newX = from.getLocation().getX() + d.getX();
		int newY = from.getLocation().getY() + d.getY();
		Tile to = map.getTile(newX, newY);
		
		// watch out for race conditions here
		to.accept(avatar);
		avatar.setFacingDirection(d);
	}
	
	//MouseMoveCommand
	public void mouseOnscreenAt(int x, int y)
	{
		//Drawer.getTileFromPosition(x,y) or isOver(someMenu/Inventory) 
		//Make some high level decision about pressing on screen buttons
	}
	
	//MouseClickedCommand
//	public void mouseClickedAt(int x, int y)
//	{
//		if(avatar.getInventory().isVisible() &&  avatar.getInventory().isOnInventory(x, y)) {}
//			//avatar.getInventory().click(x, y);
//			//System.out.println("clicked inventory space");
//	}
	public void mouseRightClickAt(Point p)
	{
		if(avatar.getInventory().isVisible() &&  avatar.getInventory().isOnInventory(p));
			avatar.getInventory().rightClick(p);
	}
	public void mouseLeftClickAt(Point p)
	{
		if(avatar.getInventory().isVisible() &&  avatar.getInventory().isOnInventory(p));
			avatar.getInventory().leftClick(p);
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
}