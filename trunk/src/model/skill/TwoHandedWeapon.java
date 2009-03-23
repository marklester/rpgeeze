package model.skill;

import model.entity2.Stats;

public class TwoHandedWeapon extends Skill implements PermanentSkill {

	
	public TwoHandedWeapon() {
		this(0);
	}
	
	public TwoHandedWeapon(int points) {
		super("Two-handed weapon", "Ability to fight with a two-handed weapon", points);
	}
	
	public void modifyStats(Stats s) {
		// TODO Auto-generated method stub

	}

}
