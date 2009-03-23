package rpgeeze.model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;
import rpgeeze.model.entity.PC;

public class PotionLife extends TakeableItem {

	public PotionLife() {
		super("Potion Life");
	}

	public void use(PC pc) {
		pc.addHealth(200);
		pc.getInventory().removeItem(this);
		LogManager.getInstance().log("Used Heath Potion.", "", Message.Type.GAME);
	}

	@Override
	public void activate(Entity entity, Tile tile) {
		// TODO Auto-generated method stub
		
	}
}
