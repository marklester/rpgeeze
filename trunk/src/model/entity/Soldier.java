package model.entity;

import model.ai.*;
import view.*;

public class Soldier extends Monster {
	
	public Soldier()
	{
		super();
		this.setAI(new AimlessAI());
	}
	
	@Override
	public void draw(Drawer d) {
		d.drawEntity(this);
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
