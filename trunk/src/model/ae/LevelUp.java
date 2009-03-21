package model.ae;
import model.Tile;
import model.entity.Entity;
import model.entity.Stats;

import view.Console;

public class LevelUp extends AreaEffect {

	public LevelUp() {
		super("Level Up");
	}

	public LevelUp(int rate) {
		super(rate, "Level Up");
	}

	public void applyEffect(Entity e) {
		if(!messageSent) {
			e.getStats().incLevel();
			//e.getStats().getPrimary().setExperience(e.getStats().getLevel() * e.getStats().getLevel() * 5);
			//e.getStats().calculateLevel();
		
			if(e.getStats().getLevel() < Stats.MAX_LEVEL) {
				Console.getInstance().writeLine("Woohoo! You leveled up!");
				messageSent = true;
				e.getTile().setAreaEffect(null);
				e.getTile().setDecal(null);
			}
		}
	}

}
