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
}