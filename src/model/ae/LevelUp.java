package model.ae;
import model.Tile;
import model.entity2.Stats;
import model.entity2.StatsModifiable;

import view.Console;

public class LevelUp extends AreaEffect {

	private boolean used = false;
	
	public LevelUp() {
		super("Level Up");
	}

	public LevelUp(int rate) {
		super(rate, "Level Up");
	}

	public void applyEffect(StatsModifiable sm) {
		if(!messageSent) {
			//e.getStats().incLevel();
			if(!used)
			{
				sm.addLevel(1);
				used = true;
			}
			//e.getStats().getPrimary().setExperience(e.getStats().getLevel() * e.getStats().getLevel() * 5);
			//e.getStats().calculateLevel();			
			
			if(sm.getLevel() < Stats.MAX_LEVEL) {
				Console.getInstance().writeLine("Woohoo! You leveled up!");
				messageSent = true;
				//e.getTile().setAreaEffect(null);
				//e.getTile().setDecal(null);
			}
		}
	}

}
