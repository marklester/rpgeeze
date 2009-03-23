package rpgeeze.model.ai;

import rpgeeze.model.Tile;
import rpgeeze.model.entity.NPC;

public class ContainedAI implements ArtificialIntelligence {
	private int bound;
	private Tile tile;
	
	public ContainedAI(int bound)
	{
		this.bound = bound;
	}

	@Override
	public void compute(NPC entity) {
		if (tile == null)
		{
			
		}
		// TODO Auto-generated method stub
		
	}
	
}
