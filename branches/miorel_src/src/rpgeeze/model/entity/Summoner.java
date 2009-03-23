package rpgeeze.model.entity;

import rpgeeze.model.skill.SkillContainer;

public class Summoner extends Occupation {
	protected Summoner(String name, Stats stats) {
		super(name, stats);
	}

	public Summoner(String name, Stats stats, SkillContainer skills) {
		super(name, stats, skills);
	}


}
