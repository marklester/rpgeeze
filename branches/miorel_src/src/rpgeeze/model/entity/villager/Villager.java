package rpgeeze.model.entity.villager;

import rpgeeze.model.Visitor;
import rpgeeze.model.entity.NPC;

public class Villager extends NPC{

	public Villager(){}
	@Override
	public boolean isAlive() {
		return false;
	}

	@Override
	public void update() {
		
	}
	@Override
	public void accept(Visitor visitor) {		// 
		visitor.visitEntity(this);
	}
	
	

}
