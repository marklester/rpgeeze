package model.entity2;

import view.Drawer;

public abstract class Monster extends NPC implements StatsModifiable{
	
	protected Stats stats = new Stats();
	
	public Monster()
	{
	}

	@Override
	public void update() {
		this.getAI().compute(this);
	}

	@Override
	public void draw(Drawer d) {
		// TODO Auto-generated method stub
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
		return stats.getLife() <= 0;
	}

	@Override
	public Stats getStats() {
		return this.stats;
	}
	public int hasCode()
	{
		return toString().hashCode();
	}
	
	public boolean equals(Object o)
	{
		boolean ret = false;
		if(o instanceof Monster)
		{
			ret = o.toString().equals(toString());
			//System.out.println(o);
		}
		return ret;
	}
	public String toString()
	{
		return "monster";
	}
}
