package model.skill;

import java.awt.Color;

import util.ResourceLoader;
import view.Console;
import model.skill.*;
import model.Entity;
import model.Location;


public class UseSkillVisitor implements Visitor {
	
	Entity e;
	
	public UseSkillVisitor(Entity e) {
		this.e = e;
	}
	
	
	public void visit(BindWounds sk) {
		if (e.getStats().getMana() >= 10) {
			ResourceLoader.getInstance().playAudioClip(sk.toString());
			Console.getInstance().writeLine("Life is invigorating", Color.WHITE);
			int life = e.getStats().getLife();
			double perc = 0;
			
			int points = sk.getPoints();
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
	
	//public void visit(RangedWeapon sk) {}

}
