package model.entity2;

import model.ai.ContainedAI;

public class Rat extends Monster{
	public Rat()
	{
		super();
		this.setAI(new ContainedAI(3));
	}
	
	public String toString()
	{
		return "Rat";
	}
	
}
