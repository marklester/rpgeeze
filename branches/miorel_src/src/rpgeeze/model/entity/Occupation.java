package rpgeeze.model.entity;

import rpgeeze.model.skill.Bargain;
import rpgeeze.model.skill.BindWounds;
import rpgeeze.model.skill.Observation;
import rpgeeze.model.entity.Occupation;
import rpgeeze.model.entity.Stats;
import rpgeeze.model.skill.SkillContainer;
import rpgeeze.dp.Iterator;
import rpgeeze.util.ArrayIterator;

public abstract class Occupation implements Cloneable {
	public static Iterator<Occupation> getPlayerOccupations() {
		return new ArrayIterator<Occupation>(
				new Smasher(null, null),
				new Summoner(null, null),
				new Sneak(null, null)
		);
	}
	
	protected final String name;
	private Stats stats;
	protected SkillContainer skills;

	protected Occupation(String name, Stats stats) {
		this.name = name;
		this.stats = stats;
		skills = new SkillContainer();
		populateSkills();
	}
	
	public Stats getStats() {
		return stats;
	}
	
	protected Occupation(String name, Stats stats, SkillContainer skills) {
		this.name = name;
		this.stats = stats;
		this.skills = skills;
	}
	
	public Occupation clone() {
		Occupation o = null;
		try {
			o = (Occupation) super.clone();
		}
		catch(CloneNotSupportedException e) {
		}
		o.stats = this.stats.clone();
		return o;
	}
	
	public String toString() {
		return this.name;
	}
	
	public abstract String getName();
	
	public boolean equals(Object o) {
		boolean ret = false;
		if(o instanceof Occupation)
			ret = o.toString().equals(toString());
		return ret;
	}
	
	public void populateSkills() {
		skills.add(new BindWounds());
		skills.add(new Bargain());
		skills.add(new Observation());
	}
}
