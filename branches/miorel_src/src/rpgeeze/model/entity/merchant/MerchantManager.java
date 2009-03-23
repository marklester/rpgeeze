package rpgeeze.model.entity.merchant;

import rpgeeze.model.entity.NPCEntityManager;
import rpgeeze.model.entity.monster.Monster;

public class MerchantManager extends NPCEntityManager{

	private Merchant entity;
	private int updateRate = 150;
	private int count = updateRate;
	
	public MerchantManager(Merchant entity) {
		super(entity);
		this.entity = entity;
	}

	@Override
	public void killOnce() {
		//Do nothing
	}

	@Override
	public Merchant getEntity() {
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
