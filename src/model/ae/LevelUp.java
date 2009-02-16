package model.ae;
import model.Entity;
import model.Stats;

import view.Console;

public class LevelUp extends AreaEffect {

	public LevelUp() {
		super("Level Up");
	}

	public LevelUp(int rate) {
		super(rate, "Level Up");
	}

	public void applyEffect(Entity e) {
		if (!f_msg_was_sent) {
			e.getStats().getPrimary().setExperience(e.getStats().getLevel() * e.getStats().getLevel() * 5);
			e.getStats().calculateLevel();
		
			if(e.getStats().getLevel() < 5 )
			{
			 Console.getInstance().writeLine("Woohoo! You leveled up!");
			 f_msg_was_sent = true;
			}
		}
	}

}
