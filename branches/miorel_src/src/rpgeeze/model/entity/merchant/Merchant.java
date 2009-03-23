package rpgeeze.model.entity.merchant;

import rpgeeze.model.Visitor;
import rpgeeze.model.entity.NPC;

public class Merchant extends NPC{
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visitEntity(this);	
	}

	public Merchant(){}
	
	@Override
	public boolean isAlive() {
		return true;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
