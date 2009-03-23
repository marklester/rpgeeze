package rpgeeze.model.skill;

import rpgeeze.model.entity.PC;

public class RangedWeapon extends Skill implements UsableSkill {

		
	public RangedWeapon() {
		this(0);
	}
	
	public RangedWeapon(int points) {
		super("Ranged Weapon", "Does damage - based on your skill level", 0);
	}

	/*
	public void accept(Visitor v) {
		//v.visit(this);
		
	}
	*/

	@Override
	public void use(PC pc) {
		// TODO Auto-generated method stub
		
	}
	
}
