package model;

import java.util.*;

public class Model {
//Paused
	protected Queue<Task> tasks = new LinkedList<Task>();
	
	private Entity avatar;
	
	public Model(Entity avatar) {		
		/* Made constructor public so that RunGame would compile. If you
		 * come up with another way of retrieving a Model, update
		 * RunGame accordingly.
		 * - Miorel
		 */
		
		//create map
			//create tiles and encompassing stuff
		//create entity
		//create task queue
		//		--Jose
		
		this.avatar = avatar;
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
}

