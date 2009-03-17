package model.skill;

import java.awt.Color;

import view.Console;
import model.Entity;
import model.Stats;
import model.PrimaryStats;
import util.ResourceLoader;


public class BindWounds extends Skill implements UsableSkill {

	
	public BindWounds() {
		super("Bind Wounds", "Heals damage - based on your skill level");
	}
	
	public void use() {
		// TODO Auto-generated method stub

	}
	
	public void use(Entity e) {
			if (e.getStats().getMana() >= 10) {
				ResourceLoader.getInstance().playAudioClip(this.name);
				Console.getInstance().writeLine("Life is invigorating", Color.WHITE);
				int life = e.getStats().getLife();
				double perc = 0;
				
				if (points > 20)
					perc = points * .01;
				else
					perc = (points * .01);
				life = (int) (perc * life);
				
				e.getStats().incLife(life);
				e.getStats().decMana(10);
			}
			else
				Console.getInstance().writeLine("You do not have enough mana...", Color.RED);
		
	}

}
