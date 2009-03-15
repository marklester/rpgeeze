package model.skill;

public abstract class Skill {
	
	int points;
	String name;
	
	public Skill(String name) {
		this(name, "", 0);
	}
	
	public Skill(String name, String descr) {
		this(name, descr, 0);
	}
	
	public Skill(String name, String descr, int initPoints) {
		this.name = name;
		this.points = initPoints;
	}
	
	public String toString() {
		return name;
	}
}