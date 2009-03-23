package rpgeeze.model.entity.villager;

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

}
