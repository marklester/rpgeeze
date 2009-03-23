package rpgeeze.model.entity;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;

import rpgeeze.dp.Iterator;
import rpgeeze.model.entity.Smasher;
import rpgeeze.model.entity.Sneak;
import rpgeeze.model.entity.Summoner;
import rpgeeze.model.skill.Bargain;
import rpgeeze.model.skill.BindWounds;
import rpgeeze.model.skill.Observation;
import rpgeeze.model.entity.Occupation;
import rpgeeze.model.entity.Stats;
import rpgeeze.model.skill.Skill;
import rpgeeze.util.ArrayIterator;

public abstract class Occupation implements Cloneable {
	private final String name;
	private Stats stats;
	private LinkedList<Skill> skills;
	
	protected Occupation(String name, Stats stats, Skill... skills) {
		this.name = name;
		for(Skill s: skills)
			this.skills.add(s);
		this.skills.add(new BindWounds());
		this.skills.add(new Bargain());
		this.skills.add(new Observation());
		this.stats = stats;
	}
	
	private static HashMap<String, Occupation> prototypes = new HashMap<String, Occupation>();
	static {
		for(Occupation o: new Occupation[] {
			new Smasher(),
			new Summoner(),
			new Sneak()
		})
			prototypes.put(o.getName(), o);
	}
		
	public Stats getStats() {
		return clone().stats;
	}
	
	public Occupation clone() {
		Occupation o = null;
		try {
			o = (Occupation) super.clone();
		}
		catch(CloneNotSupportedException e) {
		}
		o.stats = stats.clone();
		o.skills = new LinkedList<Skill>();
		for(Skill s: skills)
			o.skills.add(s.clone());
		return o;
	}
	
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean equals(Object o) {
		boolean ret = false;
		if(o instanceof Occupation)
			ret = ((Occupation) o).name.equals(name);
		return ret;
	}

	public int hashCode() {
		return name.hashCode();
	}
	
	public static Occupation getOccupation(String key){
		return prototypes.get(key).clone();
	}
	
	public final LinkedList<Skill> getSkills() {
		return clone().skills;
	}

	public static Iterator<Occupation> getPlayerOccupations() {
		return new ArrayIterator<Occupation>(new Smasher(), new Summoner(), new Sneak());
	}
}
