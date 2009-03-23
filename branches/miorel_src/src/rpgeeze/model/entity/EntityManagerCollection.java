package rpgeeze.model.entity;

import java.util.*;

public class EntityManagerCollection {

	private static EntityManagerCollection instance;
	
	private LinkedList<EntityEventManager> managers;
	
	private EntityManagerCollection() {
		managers = new LinkedList<EntityEventManager>();
	}
	
	public static EntityManagerCollection getInstance()
	{
		if(instance == null)
		{
			instance = new EntityManagerCollection();
		}
		return instance;
	}
	
	public void update()
	{
		Iterator<EntityEventManager> iter = ((LinkedList<EntityEventManager>)managers.clone()).iterator();
		while(iter.hasNext())
		{
			iter.next().update();
		}
	}
	
	public void addManager(EntityEventManager manager)
	{
		managers.add(manager);
	}
	public void remove(EntityEventManager manager)
	{
		managers.remove(manager);
	}
	
	
}
