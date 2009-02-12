package model;

import java.util.*;

public class Model {
//Paused
	LinkedList<Task> taskQueue = new LinkedList<Task>();
	
	private Model()
	{
		//create map
			//create tiles and encompassing stuff
		//create entity
		//create task queue
		//		--Jose
	}
	
	
	public synchronized void invoke(Task t)
	{
		taskQueue.addLast(t);
	}	
	public void update()
	{
		//read task queue
		while(!taskQueue.isEmpty())
		{
			taskQueue.removeFirst().operation();
		}
		//update map
			//update items
			//apply  AoE's
			//update NPC's
		//update entity
		//		--Jose
	}
}

