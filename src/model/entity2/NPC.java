package model.entity2;

import model.ai.*;

public abstract class NPC extends Entity {
	private AIContext ai;
	
	public NPC()
	{
		ai = new AIContext(new NoAI());
	}
	protected AIContext getAI()
	{
		return ai;
	}	
	protected void setAI(ArtificialIntelligence ai)
	{
		this.ai.setAI(ai);
	}
	protected void compute()
	{
		ai.compute(this);
	}
	
	public void update()
	{
		ai.compute(this);
	}
	
}
