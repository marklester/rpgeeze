package rpgeeze.model.entity;

import rpgeeze.model.skill.SkillContainer;

public class Sneak extends Occupation {
	protected Sneak(Stats stats, SkillContainer skills) {
		super("Sneak", stats, skills);
	}

	public Sneak(Stats stats) {
		super("Sneak", stats);
	}
}
