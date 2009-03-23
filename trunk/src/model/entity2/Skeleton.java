package model.entity2;

import model.ai.ContainedAI;

public class Skeleton extends Monster{
	public Skeleton()
	{
		super();
		this.setAI(new ContainedAI(3));
	}
	
	public String toString()
	{
		return "Skeleton";
	}
}
