package model.entity;

import model.skill.*;

public class Smasher extends Occupation {
	public Smasher() {
		super("Smasher", new Stats(1,100,20,15,(new PrimaryStats(3,20,5,5,2,1))));
		populateSpecificSkills();
	}
	
	public void populateSpecificSkills() {
		skills.add(new OneHandedWeapon());
		skills.add(new TwoHandedWeapon());
		skills.add(new Brawling());
	}
	
	
}
