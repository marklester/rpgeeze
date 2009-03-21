package model.skill;

import model.skill.*;

public interface Visitor {
	
	
	public void visit(BindWounds skill);
	//public void visit(RangedWeapon skill);
	
	/*
	--
	visit( s: EnchantmentSkill )
	visit( s: BindWoundSkill )
	visit( s: BargainSkill )
	...for each usable skill...
	*/
}
