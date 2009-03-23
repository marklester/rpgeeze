package rpgeeze.model.skill;

import rpgeeze.log.LogManager;
import rpgeeze.model.entity.Entity;
import rpgeeze.model.entity.PC;
import rpgeeze.model.entity.Stats;
import rpgeeze.model.item.TakeableItem;

import java.util.Random;

public class PickPocket extends Skill implements UsableSkill {

	
	public PickPocket() {
		super("Pick Pocket", "Pick pocket a MF");
	}

	@Override
	public void use(PC pc) {
		if(pc.getStats().getMana() >=5){
			pc.addMana(-5);
			label:
				for(int x = -1; x <= 1; x++){
					for(int y = -1; y <= 1; y++){
						Entity npc = pc.getTile().getRelativeTile(x, y).getEntity();
						if(npc != null && !npc.getInventory().isEmpty() && (new Random(getMaxSkillPoints()).nextInt() >= getPoints() )){
							pc.addItem((TakeableItem) npc.getInventory().removeItemAt(npc.getInventory().itemCount() * new Random(npc.getInventory().itemCount()).nextInt()));
							break label;
						}
					}
				}
		}
		else{
			LogManager.getInstance().log("Not enough mana", "Pick Pocket");
		}
	}
}
