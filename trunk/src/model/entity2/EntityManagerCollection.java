package model.entity2;

import java.util.*;

public class EntityManagerCollection {

	private static EntityManagerCollection instance;
	
	private List<EntityEventManager> managers;
	
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
		for(EntityEventManager eem: managers)
		{
			eem.update();
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
