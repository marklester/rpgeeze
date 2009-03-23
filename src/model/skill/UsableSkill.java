package model.skill;

import model.entity2.Entity;

public interface UsableSkill {

	public void use();
	
	public void accept(Visitor v);
	
}
