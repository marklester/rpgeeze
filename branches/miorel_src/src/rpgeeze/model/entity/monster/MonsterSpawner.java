package rpgeeze.model.entity.monster;

import rpgeeze.model.*;
import rpgeeze.model.entity.EntityManagerCollection;
import rpgeeze.util.Direction;

public class MonsterSpawner{
	private MonsterType type;
	private int spawnRate = 400;
	private int counter = 0;
	private Tile tile;
	
	public MonsterSpawner(Tile t, MonsterType type){
		this.tile = t;
		this.type = type;
	}	
	public void setTile(Tile t){
		tile = t;
	}
	public Tile getTile(){
		return tile;
	}
	
	public void setSpawnRate(int rate){
		spawnRate = rate;
	}
	
	public void update(){		
		if(counter-- <= 0){
			this.spawn(type);	
			counter = spawnRate;
		}
	}
	protected void spawn(MonsterType type){
		if(tile.getEntity()==null){			
			Monster m = MonsterCreator.getInstance().createMonster(type);
			tile.setEntity(m);						
			m.setFacingDirection(Direction.SOUTH);
			EntityManagerCollection.getInstance().addManager(new MonsterManager(m));
		}		
	}
	
}
