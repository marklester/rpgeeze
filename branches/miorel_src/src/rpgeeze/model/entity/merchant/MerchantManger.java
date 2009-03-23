package rpgeeze.model.entity.merchant;

import rpgeeze.model.entity.NPCEntityManager;

public class MerchantManger extends NPCEntityManager{

	Merchant entity;
	public MerchantManger(Merchant entity) {
		super(entity);
		this.entity = entity;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void killOnce() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Merchant getEntity() {
		return this.entity;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}

}
