package model.skill;

import util.ResourceLoader;
import view.Console;
import model.skill.*;
import model.entity.*;
import model.entity.Entity;
import model.Location;
import util.AudioThread;


public class UseSkillVisitor implements Visitor {
	
	StatsModifiable sm;
	
	public UseSkillVisitor(StatsModifiable sm) {
		this.sm = sm;
	}

	public void visit(BindWounds sk) {

		if (sm.hasEnoughMP(10)) {
			AudioThread.getInstance().playAudioClip(sk.toString());
			//Console.getInstance().writeLine("Life is invigorating", Color.WHITE);
			Console.getInstance().writeLifeEvent("Life is invigorating");
			//int life = e.getStats().getLife();
			//double perc = 0;
			
			//int points = sk.getPoints();
//			if (points > 20)
//				perc = points * .01;
//			else
//				perc = (points * .01);
//			life = (int) (perc * life);
			
			sm.addHealth(sk.getSkillLevel() * 20);
			sm.addMana(-10);
		}
		else
			Console.getInstance().writeHarmfulEvent("You do not have enough mana...");			
	}
	
	//public void visit(RangedWeapon sk) {}

}
