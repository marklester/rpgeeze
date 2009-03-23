package rpgeeze.model.entity;

import java.util.LinkedList;

import rpgeeze.model.entity.PrimaryStats;
import rpgeeze.model.entity.Stats;
import rpgeeze.model.skill.Skill;



public class Summoner extends Occupation {
	
	
	public Summoner() {
		super("Summoner", new Stats(1,100,20,15,new PrimaryStats(3,5,5,20,2,1)));
		populateSpecificSkills();
	}
	
	public Summoner(Stats stats) {
		super("Summoner", stats);
		populateSpecificSkills();		
	}
	
	protected Summoner(Stats stats, LinkedList<Skill> skills) {
		super("Summoner", stats, skills);
	}
	
	public void populateSpecificSkills() { }


}
