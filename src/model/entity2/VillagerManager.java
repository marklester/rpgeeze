package model.entity2;

public class VillagerManager extends NPCEntityManager{

	private Villager entity;
	public VillagerManager(Villager entity) {
		super(entity);
		this.entity = entity;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void killOnce() {
		// TODO Auto-generated method stub
	}

	@Override
	public Villager getEntity() {
		// TODO Auto-generated method stub
		return this.entity;
	}

}
