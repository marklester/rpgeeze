package rpgeeze.model.skill;

import rpgeeze.util.AudioThread;
import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.entity.PC;
import rpgeeze.model.entity.Stats;

public class Staff extends Skill implements UsableSkill {

	
	public Staff() {
		super("Staff", "Staff is used to whack opponents");
	}
	
	@Override
	public void use(PC pc) {
		// TODO Auto-generated method stub

	}
}
