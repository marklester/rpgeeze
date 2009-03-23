package rpgeeze.model.skill;

import rpgeeze.log.LogManager;
import rpgeeze.model.decal.Decal;
import rpgeeze.model.entity.PC;
import rpgeeze.model.entity.Stats;
import java.util.Random;

public class DetectTrap extends Skill implements UsableSkill {

	
	public DetectTrap() {
		super("Detect Trap", "Detect a trap - possibly!");
	}

	@Override
	public void use(PC pc) {
		// TODO Auto-generated method stub
		if(pc.hasEnoughMP(5)){
			pc.addMana(-5);
			for(int x = -2; x <= 2; x++){
				for(int y = -2; y <= 2; y++){
					String i = pc.getTile().getRelativeTile(x, y).getItem().toString();
					if (i != null && i == "Trap" && (new Random(getMaxSkillPoints()).nextInt() >= getPoints() )){
						pc.getTile().getRelativeTile(x, y).setDecal(Decal.getDecal("Trap Decal"));
					}
				}
			}
		}
		else{
			LogManager.getInstance().log("Not enough mana", "Detect Trap");
		}
		
	}

}