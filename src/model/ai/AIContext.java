package model.ai;

import model.entity2.*;

public class AIContext {
	private ArtificialIntelligence ai;
	
	public AIContext(ArtificialIntelligence ai)
	{
		this.ai = ai;
	}
	
	public void setAI(ArtificialIntelligence ai)
	{
		this.ai = ai;
	}
	
	public void compute(NPC entity)
	{
		ai.compute(entity);
	}
	
}
