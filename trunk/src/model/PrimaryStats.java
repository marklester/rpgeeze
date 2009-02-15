package model;

public class PrimaryStats implements Cloneable {

	// how many more times the entity can die before the game is over.
	// Start the game with 3 lives
	int livesLeft;

	// primary attribute of the Smasher
	// Everyone starts w/ 20, except smasher, who starts with 40
	int strength;

	// primary attribute of the Sneak
	// Everyone starts w/ 20, except sneak, who starts with 40
	int agility;

	// primary attribute of the Summoner
	// Everyone starts w/ 20, except summoner, who starts with 40
	int intellect;

	// measures how resistant a character is to physical abuse
	// Scale is 0-1 (a double) All start with .2
	double hardiness;

	// measures how much an entity knows about her occupation; earned by
	// adventuring, solving problems, etc.
	// Scale is 0-100
	int experience;

	// Generic stats
	public PrimaryStats() {
		this.livesLeft = 3;
		this.strength = 20;
		this.agility = 20;
		this.intellect = 20;
		this.hardiness = 2;
		this.experience = 1;
	}

	public PrimaryStats(int livesLeft, int strength, int agility,
			int intellect, double hardiness, int experience, int movement) {
		this.livesLeft = livesLeft;
		this.strength = strength;
		this.agility = agility;
		this.intellect = intellect;
		this.hardiness = hardiness;
		this.experience = experience;
	}

	public void setLivesLeft(int livesLeft) {
		this.livesLeft = livesLeft;
	}

	public int getLivesLeft() {
		return this.livesLeft;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getStrength() {
		return this.strength;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getAgility() {
		return this.agility;
	}

	public void setIntellect(int intellect) {
		this.intellect = intellect;
	}

	public int getIntellect() {
		return this.intellect;
	}

	public void setHardiness(int hardiness) {
		this.hardiness = hardiness;
	}

	public double getHardiness() {
		return this.hardiness;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getExperience() {
		return this.experience;
	}

	public PrimaryStats clone() throws CloneNotSupportedException {
		return (PrimaryStats) super.clone();
	}

}
