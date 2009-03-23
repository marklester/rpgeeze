package rpgeeze.model.ai;

import rpgeeze.model.*;
import rpgeeze.model.entity.NPC;
import rpgeeze.util.*;


public class AimlessAI implements ArtificialIntelligence{

	@Override
	public void compute(NPC entity) {
//		Tile t = entity.getTile();
//		t.setEntity(null);
//		t = t.getRelativeTile(randLocation());
//		try {
//			t.setEntity(entity);
//			//entity.setTile(t);
//		}catch(rpgeeze.model.entity.IllegalMoveException ime) { }
		entity.move(randDirection());
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
	private Direction randDirection()
	{
		java.util.Random r = new java.util.Random();
		int i = r.nextInt(4);
		
		switch(i)
		{
		case 0:
			return Direction.SOUTH;
		case 1:
			return  Direction.EAST;
		case 2:
			return Direction.NORTH;
		case 3:
			return  Direction.WEST;
		default: break;
		}
		return null;		
	}

}
