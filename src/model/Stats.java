package model;

public class Stats {
//how many more times the entity can die before the game is over.
int livesLeft;
//primary attribute of the Smasher
int strength;
//primary attribute of the Sneak
int agility;
//primary attribute of the Summoner
int intellect;
//measures how resistant a character is to physical abuse
int hardiness;
//measures how much an entity knows about her occupation; earned by adventuring, solving problems, etc.
int experience;
//the max. distance an entity may move over ideal terrain per unit time
int movement;

	public Stats()
	{
	 livesLeft = 0;
	 strength = 0;
	 agility = 0;
	 intellect = 0;
	 hardiness = 0;
	 experience = 0;
	 movement = 0;
	}
	
	public Stats(int livesLeft, int strength, int agility, int intellect, int hardiness, int experience, int movement)
	{
		this.livesLeft = livesLeft;
		this.strength = strength;
		this.agility = agility;
		this.intellect = intellect;
		this.hardiness = hardiness;
		this.experience = experience;
		this.movement = movement;
	}
	
	public void setLivesLeft(int livesLeft)
	{
		this.livesLeft = livesLeft;
	}
	
	public int getLivesLeft()
	{
		return livesLeft;
	}
	
	public void setStrength(int strength)
	{
		this.strength = strength;
	}
	
	public int getStrength()
	{
		return strength;
	}
	
	public void setAgility(int agility)
	{
		this.agility = agility;
	}
	
	public int getAgility()
	{
		return agility;
	}
	
	public void setIntellect(int intellect)
	{
		this.intellect = intellect;
	}
	
	public int getIntellect()
	{
		return intellect;
	}
	
	public void setHardiness(int hardiness)
	{
		this.hardiness = hardiness;
	}
	
	public int getHardiness()
	{
		return hardiness;
	}
	
	public void setExperience(int experience)
	{
		this.experience = experience;
	}
	
	public int getExperience()
	{
		return experience;
	}
	
	public void setMovement(int movement)
	{
		this.movement = movement;
	}
	
	public int getMovement()
	{
		return movement;
	}
}

