package model.skill;



public class BindWounds extends Skill implements UsableSkill {

	
	public BindWounds() {
		super("Bind Wounds", "Heals damage - based on your skill level");
	}
	
	public void use() {
		// TODO Auto-generated method stub

	}

	public void accept(Visitor v) {
		v.visit(this);
		
	}

}
