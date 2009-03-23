package rpgeeze.model.skill;

import rpgeeze.log.LogManager;
import rpgeeze.model.entity.PC;
import rpgeeze.model.entity.Stats;

public class Creep extends Skill implements UsableSkill {

	
	
	public Creep() {
		super("Creep", "Creep up and kill without being detected");
	}

	@Override
	public void use(PC pc) {
		// TODO Auto-generated method stub
		if(pc.hasEnoughMP(5)){
			pc.addMana(-5);
			pc.getStats().addMovement(pc.getStats().getMovement()/2);
		}
		else{
			LogManager.getInstance().log("Not enough mana", "Detect Trap");
		}
	}

}