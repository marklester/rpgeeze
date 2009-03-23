package rpgeeze.model.entity;

import java.util.LinkedList;

import rpgeeze.model.entity.PrimaryStats;
import rpgeeze.model.entity.Stats;
import rpgeeze.model.skill.Brawling;
import rpgeeze.model.skill.OneHandedWeapon;
import rpgeeze.model.skill.TwoHandedWeapon;
import rpgeeze.model.skill.Skill;

public class Smasher extends Occupation {
	
	public Smasher() {
		super("Smasher", new Stats(1,100,20,15,(new PrimaryStats(3,20,5,5,2,1))));
		populateSpecificSkills();
	}	
	
	public Smasher(Stats stats) {
		super("Smasher", stats);
		populateSpecificSkills();		
	}
	
	public Smasher(Stats stats, LinkedList<Skill> skills) {
		super("Smasher", stats, skills);
	}
	
	public void populateSpecificSkills() {
		skills.add(new OneHandedWeapon());
		skills.add(new TwoHandedWeapon());
		skills.add(new Brawling());
	}
	
}
