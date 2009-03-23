package rpgeeze.model.entity;

import rpgeeze.model.skill.SkillContainer;

public class Smasher extends Occupation {
	public Smasher(Stats stats, SkillContainer skills) {
		super("Smasher", stats, skills);
	}

	public Smasher(Stats stats) {
		super("Smasher", stats);
	}
}
