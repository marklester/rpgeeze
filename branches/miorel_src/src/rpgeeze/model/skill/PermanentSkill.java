package rpgeeze.model.skill;

import rpgeeze.model.entity.Stats;

public interface PermanentSkill {

	//Combat stats need to be added to Stats class
	public void modifyStats(Stats s);
}
