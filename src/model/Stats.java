package model;

public class Stats {

	//measures how good the entity is at her occupation;based on experience
	//between 1-5
	int level;
	
	//how close the entity is to death; based upon hardiness and level
	//between 0-100
	int life;
	
	//how much energy the entity has to fuel her spells; based on intellect and level
	//between 0-100 - play starts with 20
	int mana;
	
	//damage dealt when attacking;based on the equipped weapon,strength and level
	//between 0-110
	int offensiveRating;
	
	//how difficult it is to successfully attack this entity;based on agility and level
	//between 0-110
	int defensiveRating;
	
	//armor absorbs a fixed amount of damage;based on equipped armor and hardiness
	//between 0-110
	int armorRating;
	
	//the max. distance an entity may move over ideal terrain per unit time
	//1 unit for now... Portal, here we come
	int movement;
	
	PrimaryStats primaryStats;

	
	public Stats()
	{
		level = 1;
		life = 100;
		mana = 20;
		offensiveRating = 1;
		defensiveRating = 1;
		armorRating = 1;
		movement = 35;
		primaryStats = new PrimaryStats();
	}
	
	public Stats(int level, int life, int mana, int movement, PrimaryStats stats)
	{
		this.level = level;
		this.life = life;
		this.mana = mana;
		this.movement = movement;
		this.primaryStats = stats;
	}
	
	public void calculateLevel()
	{
		if((primaryStats.experience >= level * level * 5) && level < 5)
		{
			level += 1;
		}
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public int getMana()
	{
		return mana;
	}

	public int getOffensiveRating()
	{
		return offensiveRating;
	}
	
	public int getDefensiveRating()
	{
		return defensiveRating;
	}

	public int getArmorRating()
	{
		return armorRating;
	}
	
	
	public void incLife(int amount)
	{
		life += amount;
		if (life > 100) life = 100; //max is 100
	}
	
	public void decLife(int amount)
	{
		life -= amount / (level + primaryStats.hardiness);
		if (life < 0) life = 0;
	}
	
	public void decMana(int amount)
	{
		mana -= amount;
		if (mana < 0) mana = 0;
	}
	
	public void incMana(int amount) {
		mana += amount;
		if (mana > 100) mana = 100;
	}
	
	
	//Ratings are passed an integer "effectiveness" which is a property of 
	//each weapon/armor, which will be on a 1-20 scale
	//These methods will need to be called upon equipping & unequipping
	
	public void calculateOffensiveRating(int effectiveness)
	{
		offensiveRating = (primaryStats.strength/2 + effectiveness) * level;
		if (offensiveRating > 100) offensiveRating = 110;
	}
	

	public void calculateDefensiveRating()
	{
		defensiveRating = primaryStats.agility * level + 10;
	}
	
	
	public void calculateArmorRating(int effectiveness)
	{
		armorRating = effectiveness + (int)(effectiveness * primaryStats.hardiness);
		//hardiness is a dec between 1 & 0
	}
	
	
	public void setMovement(int movement)
	{
		this.movement = movement;
	}
	
	public int getMovement()
	{
		return movement;
	}
	
	public Object clone() throws CloneNotSupportedException 
	{
        return super.clone();
	}
	
	public PrimaryStats getPrimary() {
		return primaryStats;
	}
	
	
}

