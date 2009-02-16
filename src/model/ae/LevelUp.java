package model.ae;
import model.Entity;

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
			e.getStats().getPrimary().setExperience(e.getStats().getLevel() * e.getStats().getLevel() * 5);
			e.getStats().calculateLevel();
		
			// shouldn't hard code maximum level...
			if(e.getStats().getLevel() < 5) {
				Console.getInstance().writeLine("Woohoo! You leveled up!");
				messageSent = true;
			}
		}
	}

}
