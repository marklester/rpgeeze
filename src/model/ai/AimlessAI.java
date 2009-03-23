package model.ai;

import model.entity.NPC;
import model.*;

public class AimlessAI implements ArtificialIntelligence{

	@Override
	public void compute(NPC entity) {
		Tile t = entity.getTile();
		t = t.getRelativeTile(randLocation());
		t.accept(entity);
	}
	
	private Location randLocation()
	{
		java.util.Random r = new java.util.Random();
		int i = r.nextInt(4);
		
		switch(i)
		{
		case 0:
			return new Location(0, -1);
		case 1:
			return  new Location(0, 1);
		case 2:
			return  new Location(1, 0);
		case 3:
			return  new Location(-1, 0);
		default: break;
		}
		return null;		
	}

}
