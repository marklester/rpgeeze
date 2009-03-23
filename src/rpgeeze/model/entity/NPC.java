package rpgeeze.model.entity;

import rpgeeze.model.ai.AIContext;
import rpgeeze.model.ai.ArtificialIntelligence;
import rpgeeze.model.ai.NoAI;
import rpgeeze.model.entity.Entity;

public abstract class NPC extends Entity {
	private AIContext ai;
	
	public NPC(){
		ai = new AIContext(new NoAI());
	}
	protected AIContext getAI(){
		return ai;
	}	
	protected void setAI(ArtificialIntelligence ai){
		this.ai.setAI(ai);
	}
	protected void compute(){
		ai.compute(this);
	}
	
	public void update(){
		ai.compute(this);
	}
	
}
