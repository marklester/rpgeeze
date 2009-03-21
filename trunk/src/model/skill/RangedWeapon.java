package model.skill;

import model.skill.Visitor;

public class RangedWeapon extends Skill implements UsableSkill {

		
	public RangedWeapon() {
		this(0);
	}
	
	public RangedWeapon(int points) {
		super("Ranged Weapon", "Does damage - based on your skill level", 0);
	}
	
	public void use() {
		// TODO Auto-generated method stub

	}

	public void accept(Visitor v) {
		//v.visit(this);
		
	}
	
}
