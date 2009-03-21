package model.skill;

import model.skill.Visitor;

public class RangedWeapon extends Skill implements UsableSkill {

		
		public RangedWeapon() {
			super("Ranged Weapon", "Does damage - based on your skill level");
		}
		
		public void use() {
			// TODO Auto-generated method stub

		}

		public void accept(Visitor v) {
			v.visit(this);
			
		}
}
