package model.skill;

import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class Skill implements Cloneable{
	
	private int points;
	private String name;
	private String descr;	
	private int skillLevel; 
	private static Pattern pattern = Pattern.compile("<skill><type>(.*)</type><points>(\\d+)</points></skill>");
	private static Hashtable<String, Skill> prototypes = new Hashtable<String, Skill>();
	
	static {
		for(Skill s: new Skill[] {
			new BindWounds(),
			new Bargain(),
			new TwoHandedWeapon(),
			new RangedWeapon(),
			new OneHandedWeapon(),
			new Brawling(),
			new Observation()
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
	
	public void incPoints() throws MaxPointsAllocatedException {
		if (points < 100)
			points++;
		else 
			throw new MaxPointsAllocatedException();
	}
	
	public void addPoints(int howMany) throws MaxPointsAllocatedException {
		if (points+howMany <= 100)
			points += howMany;
		else 
			throw new MaxPointsAllocatedException();
	}
	
	public void setPoints(int howMany) {
		if (points+howMany <= 100)
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
	
	public String toXml(String indent) {
		StringBuilder sb = new StringBuilder();
		sb.append(indent + "<skill>\n");
		sb.append(indent + "\t<type>" + name + "</type>\n");
		sb.append(indent + "\t<points>" + points + "</points>\n");
		sb.append(indent + "</skill>");
		return sb.toString();
	}
	
	public static Skill fromXml(String xml) {
		Matcher mat = pattern.matcher(xml);
		if(!mat.matches())
			throw new RuntimeException("Bad XML for Skill");
		Skill ret = prototypes.get(mat.group(1)).clone();
		ret.setPoints(Integer.parseInt(mat.group(2)));
		//ret.setAttributesFromXml(mat.group(2));
		return ret;
	}
	
	protected void setAttributesFromXml(String xml) {
		if(xml.length() > 0)
			throw new RuntimeException("Bad XML for Skill");
	}
	protected void setSkillLevel(int sl)
	{
		skillLevel = sl;	
	}
	protected int getSkillLevel()
	{
		return skillLevel;
	}
	public static Skill getSkillPrototype(String key){
		return prototypes.get(key).clone();
	}
}