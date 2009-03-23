package rpgeeze.model.skill;

import rpgeeze.util.AudioThread;
import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.entity.PC;

public class BindWounds extends Skill implements UsableSkill {

	
	public BindWounds() {
		super("Bind Wounds", "Heals damage - based on your skill level");
	}
	
	public void use(PC pc) {
		if (pc.hasEnoughMP(10)) {
			AudioThread.getInstance().playAudioClip(this.toString());
			LogManager.getInstance().log("Life is invigorating", "", Message.Type.GAME);
			pc.addHealth(getSkillLevel() * 20);
			pc.addMana(-10);
		}
		else
			LogManager.getInstance().log("You do not have enough mana...", "", Message.Type.GAME);
	}

	/*
	public void accept(Visitor v) {
		v.visit(this);
	}
	*/
}
