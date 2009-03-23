package model.entity2;

import model.ai.ContainedAI;

public class Golem extends Monster{

	public Golem()
	{
		super();
		this.setAI(new ContainedAI(3));
	}
	
	@Override
	public boolean equals(Object o)
	{
		return (o instanceof Golem);
	}
}
