package rpgeeze.model.entity.monster;

import rpgeeze.model.ai.*;

public class Soldier extends Monster {
	
	public Soldier()
	{
		super();
		this.setAI(new AimlessAI());
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
