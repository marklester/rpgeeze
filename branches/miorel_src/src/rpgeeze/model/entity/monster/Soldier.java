package rpgeeze.model.entity.monster;

import rpgeeze.model.ai.*;
import rpgeeze.model.entity.*;

public class Soldier extends Monster {
	
	public Soldier()
	{
		super();		
		this.setAI(new AimlessAI());
		this.inventory = new Inventory(0);
		this.stats = new Stats();
		setStats(0);
	}
	public Soldier(int level)
	{
		this();
		setStats(level);
	}
	
	private void setStats(int i)
	{
		stats.level = i;
		stats.life = 100 + i * 20;
		stats.defensiveRating = 20 + 12 * i;
		stats.offensiveRating = 20 + 12 * i;
		stats.movement = 10;
	}
	public String toString()
	{
		return "Soldier";
	}
	
	public void update()
	{
		super.update();
	}
}
