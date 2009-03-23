package rpgeeze.model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.entity.Entity;


public class PotionLife extends TakeableItem {

	public PotionLife() {
		super("Potion Life");
	}

	
	
	public void use(Entity entity){
		entity.addHealth(200);
		entity.getInventory().removeItem(this);
		LogManager.getInstance().log("Used Health Potion.", "", Message.Type.GAME);
	}

}
