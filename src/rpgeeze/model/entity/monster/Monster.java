package rpgeeze.model.entity.monster;

import rpgeeze.model.Visitor;
import rpgeeze.model.entity.NPC;
import rpgeeze.model.entity.StatsModifiable;
import rpgeeze.model.ai.*;

public abstract class Monster extends NPC implements StatsModifiable{
	
	public Monster(){
		this.setAI(new NoAI());
	}

	@Override
	public void update() {
		this.getAI().compute(this);
	}

	public boolean hasEnoughHP(int value)
	{
		return value >= stats.life;
	}
	
	public boolean hasEnoughMP(int value)
	{
		return value >= stats.mana;
	}
	
	public void addHealth(int value)
	{
		stats.addHealth(value);
	}
	
	public void addMana(int value)
	{
		stats.addMana(value);
	}
	
	public void addMovement(int value)
 	{
		stats.addMovement(value);
	}
	
	public void addLevel(int value)
	{
		stats.addLevel(value);
	}
	
	public int maxHealth()
	{
		return stats.MAX_LIFE;
	}
	
	public int maxMana()
	{
		return stats.MAX_MP;
	}
	
	public int getLevel()
	{
		return stats.getLevel();
	}
	
	public boolean isAlive() {
		return stats.getLife() > 0;
	}

	public int hasCode()
	{
		return toString().hashCode();
	}
	
	public void accept(Visitor visitor) {
		visitor.visitEntity(this);
	}
	
	public boolean equals(Object o)
	{
		boolean ret = false;
		if(o instanceof Monster)
		{
			ret = o.toString().equals(toString());
		}
		return ret;
	}
	public String toString()
	{
		return "Monster";
	}
	
	public Monster clone()
	{
		return (Monster)super.clone();
		
	}
}

