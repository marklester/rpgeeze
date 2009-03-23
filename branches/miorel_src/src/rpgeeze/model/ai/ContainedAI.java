package rpgeeze.model.ai;

<<<<<<< .mine
import rpgeeze.model.*;
=======
import rpgeeze.model.Tile;
>>>>>>> .r1116
import rpgeeze.model.entity.NPC;

public class ContainedAI implements ArtificialIntelligence {
	private int bound;
	private Tile tile;
	private int layer;
	
	public ContainedAI(int bound)
	{
		this.bound = bound;
		
	}

	@Override
	public void compute(NPC entity) {
		if (tile == null)
		{
			tile = entity.getTile();
			layer = 0;
		}
		
		if(layer < bound)
		{
			entity.move(randLocation());
		}else
		{
			Location ref = tile.getLocation();
			Location loc = entity.getTile().getLocation();
			int xlayer = Math.abs(loc.getX() - ref.getX());
			int ylayer = Math.abs(loc.getY() - ref.getY());
			if(xlayer > ylayer)
			{
				java.util.Random r = new java.util.Random();
				int i = r.nextInt(4);
				
				switch(i)
				{
					case 0:
						entity.move(new Location(0, -1));
						break;
					case 1:
						entity.move((xlayer == (loc.getX() - ref.getX()))? new Location(-1, 0): new Location(1, 0) );
						break;
					case 2:
						entity.move(new Location (0, 1));
						break;
					case 3:
						entity.move((xlayer == (loc.getX() - ref.getX()))? new Location(-1, 0): new Location(1, 0) );
						break;
					default: break;
				}
			}
			else if(xlayer < ylayer)
			{
				java.util.Random r = new java.util.Random();
				int i = r.nextInt(4);
				
				switch(i)
				{
					case 0:
						entity.move((ylayer == (loc.getY() - ref.getY()))? new Location(0, -1): new Location(0, 1));
						break;
					case 1:
						entity.move(new Location(-1, 0) );
						break;
					case 2:
						entity.move((ylayer == (loc.getY() - ref.getY()))? new Location(0, -1): new Location(0, 1));
						break;
					case 3:
						entity.move(new Location(1, 0) );
						break;
					default: break;
				}
			}
			else
			{
				java.util.Random r = new java.util.Random();
				int i = r.nextInt(4);
				
				switch(i)
				{
					case 0:
						entity.move((ylayer == (loc.getY() - ref.getY()))? new Location(0, -1): new Location(0, 1));
						break;
					case 1:
						entity.move((xlayer == (loc.getX() - ref.getX()))? new Location(-1, 0): new Location(1, 0) );
						break;
					case 2:
						entity.move((ylayer == (loc.getY() - ref.getY()))? new Location(0, -1): new Location(0, 1));
						break;
					case 3:
						entity.move((xlayer == (loc.getX() - ref.getX()))? new Location(-1, 0): new Location(1, 0) );
						break;
					default: break;
				}
			}
		}
		// TODO Auto-generated method stub
		
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
