package rpgeeze.model.skill;

import java.util.Hashtable;

public abstract class Skill implements Cloneable{
	private int points;
	private String name;
	private String descr;	
	private int skillLevel; 
	private final static int MaxSkillPoints = 100;
	private static Hashtable<String, Skill> prototypes = new Hashtable<String, Skill>();
	
	static {
		for(Skill s: new Skill[] {
			new BindWounds(),
			new Bargain(),
			new TwoHandedWeapon(),
			new RangedWeapon(),
			new OneHandedWeapon(),
			new Brawling(),
			new Observation(),
			new PickPocket(),
			new DetectTrap(),
			new Creep(),
			new RangedWeapon(),
			new Enchantment(),
			new Boon(),
			new Bane(),
			new Staff()
		})
			prototypes.put(s.toString(), s);
	}
	
	
	public Skill(String name) {
		this(name, "", 0);
	}
	
	public Skill(String name, String descr) {
		this(name, descr, 0);
	}
	
	public Skill(String name, String descr, int initPoints) {
		this.name = name;
		this.descr = descr;
		this.points = initPoints;
	}
	
	public String toString() {
		return name;
	}
	
	public int getPoints() {
		return points;
	}
	public int getMaxSkillPoints(){
		return MaxSkillPoints;
	}
	
	public void incPoints() throws MaxPointsAllocatedException {
		if (points < MaxSkillPoints)
			points++;
		else 
			throw new MaxPointsAllocatedException();
	}
	
	public void addPoints(int howMany) throws MaxPointsAllocatedException {
		if (points+howMany <= MaxSkillPoints)
			points += howMany;
		else 
			throw new MaxPointsAllocatedException();
	}
	
	public void setPoints(int howMany) {
		if (points+howMany <= MaxSkillPoints)
			points += howMany;
		else 
			points = 50;
	}
	
	public Skill clone() {
		Skill ret = null;
		try {
			ret = (Skill)super.clone();
			ret.descr = this.descr;
			ret.name = this.name;
			ret.points = this.points;
		}
		catch(CloneNotSupportedException e) { e.printStackTrace();}
		return ret;
	}
	
	protected void setSkillLevel(int sl){
		skillLevel = sl;	
	}
	protected int getSkillLevel(){
		return skillLevel;
	}
	public static Skill getSkillPrototype(String key){
		return prototypes.get(key).clone();
	}
	
}