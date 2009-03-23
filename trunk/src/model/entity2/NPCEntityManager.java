package model.entity2;

public abstract class NPCEntityManager extends EntityEventManager{

	private NPC entity;
	public NPCEntityManager(NPC entity)
	{
		super(entity);
		this.entity = entity;
	}
	
	@Override
	public NPC getEntity() {
		return this.entity;
	}

	@Override
	public void killOnce() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {		
		super.update();
	}	
}
