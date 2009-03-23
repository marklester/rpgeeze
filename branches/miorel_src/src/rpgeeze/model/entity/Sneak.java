package rpgeeze.model.entity;

import java.util.LinkedList;

import rpgeeze.model.entity.PrimaryStats;
import rpgeeze.model.entity.Stats;
import rpgeeze.model.skill.Skill;

public class Sneak extends Occupation {
	
	public Sneak() {
		super("Sneak", new Stats(1,100,20,15,new PrimaryStats(3,5,20,5,2,1)));
		populateSpecificSkills();
	}

	public Sneak(Stats stats) {
		super("Sneak", stats);
		populateSpecificSkills();		
	}
	
	protected Sneak(Stats stats, LinkedList<Skill> skills) {
		super("Sneak", stats, skills);
	}
	
	public void populateSpecificSkills() { }
}
