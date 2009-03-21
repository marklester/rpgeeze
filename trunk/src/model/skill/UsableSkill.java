package model.skill;

import model.entity.Entity;

public interface UsableSkill {

	public void use();
	
	public void accept(Visitor v);
	
}
