package model;

import java.util.*;

public class Model {
//Paused
	protected Queue<Task> tasks = new LinkedList<Task>();
	
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
		map.getTile(x, y).setEntity(avatar);
	}
	
	public Map getMap() {
		return map;
	}
	
	public synchronized void invoke(Task t) {
		tasks.add(t);
	}	

	public void update() {
		//read task queue
		while(!tasks.isEmpty()) {
			tasks.remove().operation();
		}
		//update map
			//update items
			//apply  AoE's
			//update NPC's
		//update entity
		//		--Jose
	}
	
	public void moveEntity(Distance d)
	{
		//forward move request to Entity
		//		--Jose
	}
}

