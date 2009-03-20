package model.skill;

import model.Entity;
import model.skill.visitor.Visitor;

public interface UsableSkill {

	public void use();
	
	public void accept(Visitor v);
	
}
