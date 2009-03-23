package rpgeeze.model.entity.monster;

import rpgeeze.model.entity.NPCEntityManager;

public class MonsterManager extends NPCEntityManager{

	private Monster entity;
	private int updateRate = 100;
	private int count = updateRate;
	public MonsterManager(Monster entity) {
		super(entity);
		this.entity = entity;
	}

	@Override
	public void killOnce() {
		entity.getTile().setEntity(null);
	}

	@Override
	public Monster getEntity() {
		return this.entity;
	}
	
	@Override
	public void update()
	{
		super.update();
		if(count-- <= 0)
		{
			entity.update();
			count = updateRate;
		}
	}
}