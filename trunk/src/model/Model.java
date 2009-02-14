package model;

import java.util.*;

public class Model{
	protected Queue<Command> commands = new LinkedList<Command>();
	
	private Entity avatar;
	private Map map;
	private boolean stats_up;
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
		this.stats_up=false;
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
		while(!commands.isEmpty())
			commands.remove().execute();
		
		//update map
			//update items
			//apply  AoE's
			//update NPC's
		//update entity
		//		--Jose
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
}