package rpgeeze.model.entity.monster;

import rpgeeze.model.*;
import rpgeeze.model.entity.EntityManagerCollection;
import rpgeeze.util.Direction;
import java.util.LinkedList;

public class MonsterSpawner{
	private MonsterType type;
	private int spawnRate = 700;
	private int counter = 5;
	private int mobLimit = 3;
	private Tile tile;
	private LinkedList<Monster> mobs;
	
	
	public MonsterSpawner(Tile t, MonsterType type){
		this.tile = t;
		this.type = type;
		mobs = new LinkedList<Monster>();
		mobLimit = 3;
	}	
	public void setTile(Tile t){
		tile = t;
	}
	public void setLimit(int i)
	{
		mobLimit = i;
	}
	public Tile getTile(){
		return tile;
	}
	
	public void setSpawnRate(int rate){
		spawnRate = rate;
	}
	
	public void update(){
		
		
		if(counter-- <= 0)
		{
			java.util.Iterator<Monster> iter = mobs.iterator();
			while(iter.hasNext())
			{
				if(!iter.next().isAlive())
					iter.remove();
			}
			if(mobs.size() < mobLimit)	
			{
				this.spawn(type);
			}
			counter = spawnRate;
		}
	}
	protected void spawn(MonsterType type){					
		
		if(tile.getEntity()==null){			
			System.out.println("Spawning a new Monster");
			Monster m = MonsterCreator.getInstance().createMonster(type);
			mobs.add(m);
			tile.setEntity(m);
			m.setFacingDirection(Direction.SOUTH);
			EntityManagerCollection.getInstance().addManager(new MonsterManager(m));
		}		
	}
	
}
