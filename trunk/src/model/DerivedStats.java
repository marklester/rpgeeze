package model;

public class DerivedStats {
//measures how good the entity is at her occupation;based on experience
int level;
//how close the entity is to death; based upon hardiness and level
int life;
//how much energy the entity has to fuel her spells; based on intellect and level
int mana;
//damage dealt when attacking;based on the equipped weapon,strength and level
int offensiveRating;
//how difficult it is to successfully attack this entity;based on agility and level
int defensiveRating;
//armor absorbs a fixed amount of damage;based on equipped armor and hardiness
int armorRating;

	public DerivedStats()
	{
		level = 0;
		life = 0;
		mana = 0;
		offensiveRating = 0;
		defensiveRating = 0;
		armorRating = 0;
	}
	
	public DerivedStats(int level, int life, int mana, int offensiveRating, int defensiveRating, int armorRating)
	{
		this.level = level;
		this.life = life;
		this.mana = mana;
		this.offensiveRating = offensiveRating;
		this.defensiveRating = defensiveRating;
		this.armorRating = armorRating;
	}
	
	public void calculateLevel(int experience)
	{
		if(experience >= level * level * 100)
		{
			level += 1;
		}
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void calculateLife(int hardiness)
	{
		life = level * hardiness;
	}
	
	public int getLife()
	{
		return life;
	}
	
	public void calculateMana(int intellect)
	{
		mana = level * intellect;
	}
	
	public int getMana()
	{
		return mana;
	}
	
	public void calculateOffensiveRating(int strength)
	{
		
	}
	
	public int getOffensiveRating()
	{
		return offensiveRating;
	}
	
	public void calculateDefensiveRating(int agility)
	{
		defensiveRating = agility * level;
	}
	
	public int getDefensiveRating()
	{
		return defensiveRating;
	}
	
	public void calculateArmorRating(int armorRating)
	{
		
	}
	
	public int getArmorRating()
	{
		return armorRating;
	}
}

